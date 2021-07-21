package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.*;
import org.fantasticcoffee.shop.service.ICoffee;
import org.fantasticcoffee.shop.service.IOrder;
import org.fantasticcoffee.shop.utils.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller("controller")
public class AppController {

    @Autowired
    private IOrder orderService;
    @Autowired
    private ICoffee coffeeService;
    @Autowired
    private IView consoleView;
    @Autowired
    private Input input;

    private String customerName;

    public AppController() {
    }

    public void runApp() {

        List<Coffee> coffeeList = new ArrayList<>();
        while (true) {
            if (this.customerName == null) {
                getCustomerName();
            }

            consoleView.printMainMenu();
            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> selectCoffee(coffeeList);
                case "2" -> removeCoffeeFromCustomerList(coffeeList);
                case "3" -> coffeeList = placeOrder(coffeeList);
                case "4" -> printAllOrders();
                case "5" -> updateOrder();
                case "6" -> cancelOrder();
                case "7" -> printCurrentOrder(coffeeList);
                case "X" -> {
                    consoleView.printGoodByeMessage();
                    return;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private List<Coffee> selectCoffee(List<Coffee> coffeeList) {

        while (true) {
            consoleView.printCoffeeOptionListMessage(new Double[]{this.coffeeService.getCoffeeTypePrice(CoffeeType.ESPRESSO),
                    this.coffeeService.getCoffeeTypePrice(CoffeeType.MACHIATTO),
                    this.coffeeService.getCoffeeTypePrice(CoffeeType.CAFFEE_LATTE),
                    this.coffeeService.getCoffeeTypePrice(CoffeeType.CAPPUCCINO),
                    this.coffeeService.getCoffeeTypePrice(CoffeeType.CAFFEE_MIEL)}
            );

            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.ESPRESSO, chooseExtraIngredients()));
                case "2" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.MACHIATTO, chooseExtraIngredients()));
                case "3" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.CAFFEE_LATTE, chooseExtraIngredients()));
                case "4" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.CAPPUCCINO, chooseExtraIngredients()));
                case "5" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.CAFFEE_MIEL, chooseExtraIngredients()));
                case "6" -> {
                    List<BaseIngredient> baseIngredients = new ArrayList<>();
                    baseIngredients.addAll(chooseCoffeeShots().getBaseIngredients());

                    List<ExtraIngredient> extraIngredients = new ArrayList<>();
                    extraIngredients.addAll(chooseExtraIngredients().getExtraIngredients());

                    coffeeList.add(new Coffee(this.customerName, CoffeeType.DEFAULT, new CoffeeRecipe(baseIngredients, extraIngredients)));
                }
                case "X" -> {
                    return coffeeList;
                }
                default -> consoleView.printInvalidOptionMessage();
            }

            if (!coffeeList.isEmpty()) {
                this.consoleView.printCoffeeListMessage(coffeeList);
            }
        }
    }

    private List<Coffee> removeCoffeeFromCustomerList(List<Coffee> coffeeList) {

        if (!coffeeList.isEmpty()) {
            this.consoleView.printCoffeeListMessage(coffeeList);
        } else {
            this.consoleView.printEmptyList();
            return coffeeList;
        }

        consoleView.printAskForIdMessage();
        int coffeeIndex = input.readInt();
        try {
            Coffee coffee = coffeeList.get(coffeeIndex);
            coffeeList.remove(coffee);
        } catch (IndexOutOfBoundsException e) {
            this.consoleView.printInvalidIdMessage();
        }

        if (!coffeeList.isEmpty()) {
            this.consoleView.printCoffeeListMessage(coffeeList);
        } else {
            this.consoleView.printEmptyList();
        }

        return coffeeList;
    }

    private List<Coffee> placeOrder(List<Coffee> coffeeList) {

        if (coffeeList.isEmpty()) {
            consoleView.printOrderEmptyMessage();
            return coffeeList;
        }

        Order.WhereToDrink whereToDrink = setWhereToDrink();

        Order order = this.orderService.placeOrder(new Order(LocalDateTime.now(), coffeeList, whereToDrink));
        if (order == null) {
            this.consoleView.unknownError();
            return coffeeList;
        }
        this.consoleView.printCheckMessage(order, this.orderService.getTotalOrderPrice(order), this.orderService.getTotalProfitForToday());
        this.consoleView.printEnjoyCoffeeMessage();

        this.customerName = null;

        return new ArrayList<>();
    }

    private void printAllOrders() {

        List<Order> orderList = this.orderService.findAll();
        if (orderList.isEmpty()) {
            this.consoleView.printEmptyList();
        }
        orderList.forEach(this.consoleView::printAllOrders);
    }

    private void updateOrder() {

        this.consoleView.printAskForIdMessage();
        Integer orderId = this.input.readInt();

        Order order = this.orderService.findOrder(orderId);
        if (order == null) {
            this.consoleView.printInvalidIdMessage();
            return;
        }
        order = this.orderService.update(updateMenu(order));

        this.consoleView.printCheckMessage(order, this.orderService.getTotalOrderPrice(order), this.orderService.getTotalProfitForToday());
    }

    private void cancelOrder() {

        this.consoleView.printAskForIdMessage();
        Integer orderId = this.input.readInt();

        this.orderService.deleteOrder(orderId);
        this.consoleView.printOrderCanceledMessage();
    }

    private void printCurrentOrder(List<Coffee> coffeeList) {

        if (!coffeeList.isEmpty()) {
            this.consoleView.printCoffeeListMessage(coffeeList);
        } else {
            this.consoleView.printEmptyList();
        }
    }

    private Order updateMenu(Order order) {

        List<Coffee> coffeeList = order.getCoffeeList();
        while (true) {
            consoleView.printUpdateOrderMessage();

            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> selectCoffee(coffeeList);
                case "2" -> removeCoffeeFromCustomerList(coffeeList);
                case "3" -> {
                    order.setCoffeeList(coffeeList);
                    return order;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private CoffeeRecipe chooseCoffeeShots() {

        List<BaseIngredient> baseIngredients = new ArrayList<>();
        consoleView.printCoffeeShotsOptionListMessage();
        while (true) {
            int shotsNumber;
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> {
                    shotsNumber = chooseShotsNumber();
                    baseIngredients.addAll(Collections.nCopies(shotsNumber, BaseIngredient.ESPRESSO_SHOT));
                    return CoffeeRecipe.withBaseIngredients(baseIngredients);
                }
                case "2" -> {
                    shotsNumber = chooseShotsNumber();
                    baseIngredients.addAll(Collections.nCopies(shotsNumber, BaseIngredient.BLACK_COFFEE));
                    return CoffeeRecipe.withBaseIngredients(baseIngredients);
                }
                case "X" -> {
                    return new CoffeeRecipe();
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private int chooseShotsNumber() {

        this.consoleView.printAskShotsNumber();
        int shotsNumber = this.input.readInt();

        return shotsNumber;
    }

    private CoffeeRecipe chooseExtraIngredients() {

        consoleView.printIngredientsOptionListMessage();
        List<ExtraIngredient> extraIngredientList = new ArrayList<>();

        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> extraIngredientList.add(ExtraIngredient.MILK);
                case "2" -> extraIngredientList.add(ExtraIngredient.HONEY);
                case "3" -> extraIngredientList.add(ExtraIngredient.SYRUP);
                case "4" -> extraIngredientList.add(ExtraIngredient.STEAMED_MILK);
                case "5" -> extraIngredientList.add(ExtraIngredient.MILK_FOAM);
                case "6" -> extraIngredientList.add(ExtraIngredient.SWEETENED_CONDENSED_MILK);
                case "7" -> extraIngredientList.add(ExtraIngredient.ICE_CREAM);
                case "8" -> extraIngredientList.add(ExtraIngredient.WHIPPED_CREAM);
                case "9" -> extraIngredientList.add(ExtraIngredient.CINNAMON);
                case "10" -> extraIngredientList.add(ExtraIngredient.HOT_WATER);
                case "11" -> extraIngredientList.add(ExtraIngredient.ICE_CUBES);
                case "X" -> {
                    return CoffeeRecipe.withExtraIngredients(extraIngredientList);
                }
                default -> consoleView.printInvalidOptionMessage();
            }
            consoleView.printChosenIngredientsForCurrentCoffee(extraIngredientList);
            consoleView.printIngredientsOptionListMessage();
        }
    }

    private void getCustomerName() {

        consoleView.printAskNameMessage();
        String ourCustomerName = input.readline();

        this.customerName = ourCustomerName;
    }

    private Order.WhereToDrink setWhereToDrink() {

        consoleView.printAskWhereToDrinkMessage();
        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> {
                    return Order.WhereToDrink.PICK_UP;
                }
                case "2" -> {
                    return Order.WhereToDrink.TO_GO;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    public interface IView {

        void printMainMenu();

        void printCoffeeOptionListMessage(Double coffeeTypePrices[]);

        void printCoffeeShotsOptionListMessage();

        void printAskShotsNumber();

        void printIngredientsOptionListMessage();

        void printChosenIngredientsForCurrentCoffee(List<ExtraIngredient> extraIngredientList);

        void printAskNameMessage();

        void printAskWhereToDrinkMessage();

        void printGoodByeMessage();

        void printOrderEmptyMessage();

        void printEmptyList();

        void printInvalidOptionMessage();

        void printInvalidIdMessage();

        void printUpdateOrderMessage();

        void printAskForIdMessage();

        void printOrderCanceledMessage();

        void printEnjoyCoffeeMessage();

        void printCheckMessage(Order placedOrder, Double priceOrder, Double profitToday);

        void printCoffeeListMessage(List<Coffee> coffeeList);

        void printAllOrders(Order order);

        void unknownError();
    }
}
