package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.WhereToDrink;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.CoffeeRecipe;
import org.fantasticcoffee.shop.model.CoffeeType;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.fantasticcoffee.shop.service.OrderService;
import org.fantasticcoffee.shop.utils.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("controller")
public class AppController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private View consoleView;
    @Autowired
    private Input input;

    private String customerName;

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
                    this.coffeeService.getCoffeeTypePrice(CoffeeType.CAFFEE_MIEL),
                    this.coffeeService.getCoffeeTypePrice(CoffeeType.DEFAULT)}
            );

            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.ESPRESSO, chooseExtraIngredients()));
                case "2" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.MACHIATTO, chooseExtraIngredients()));
                case "3" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.CAFFEE_LATTE, chooseExtraIngredients()));
                case "4" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.CAPPUCCINO, chooseExtraIngredients()));
                case "5" -> coffeeList.add(new Coffee(this.customerName, CoffeeType.CAFFEE_MIEL, chooseExtraIngredients()));
                case "6" -> {
                    List<BaseIngredientOnRecipe> baseIngredientOnRecipes = new ArrayList<>(chooseCoffeeShots().getBaseIngredients());
                    List<ExtraIngredientOnRecipe> extraIngredientOnRecipes = new ArrayList<>(chooseExtraIngredients().getExtraIngredients());

                    CoffeeType coffeeType = CoffeeType.DEFAULT;
                    coffeeType.getRecipe().setBaseIngredients(baseIngredientOnRecipes);
                    coffeeType.getRecipe().setExtraIngredients(extraIngredientOnRecipes);

                    coffeeList.add(new Coffee(this.customerName, coffeeType, new CoffeeRecipe()));
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

        WhereToDrink whereToDrink = setWhereToDrink();

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

    private Integer chooseIngredientQuantity() {

        this.consoleView.askForQuantity();
        Integer quantity = this.input.readInt();

        return quantity;
    }

    private CoffeeRecipe chooseCoffeeShots() {

        consoleView.printCoffeeShotsOptionListMessage();
        while (true) {
            int shotsNumber;
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> {
                    shotsNumber = chooseShotsNumber();
                    return new CoffeeRecipe.Builder(
                            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.ESPRESSO_SHOT, shotsNumber))).build();
                }
                case "2" -> {
                    shotsNumber = chooseShotsNumber();
                    return new CoffeeRecipe.Builder(
                            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.BLACK_COFFEE, shotsNumber))).build();
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
        Map<ExtraIngredient, Integer> extraIngredientDefinitionMap = new HashMap<>();

        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> extraIngredientDefinitionMap.put(ExtraIngredient.MILK, chooseIngredientQuantity());
                case "2" -> extraIngredientDefinitionMap.put(ExtraIngredient.HONEY, chooseIngredientQuantity());
                case "3" -> extraIngredientDefinitionMap.put(ExtraIngredient.SYRUP, chooseIngredientQuantity());
                case "4" -> extraIngredientDefinitionMap.put(ExtraIngredient.STEAMED_MILK, chooseIngredientQuantity());
                case "5" -> extraIngredientDefinitionMap.put(ExtraIngredient.MILK_FOAM, chooseIngredientQuantity());
                case "6" -> extraIngredientDefinitionMap.put(ExtraIngredient.SWEETENED_CONDENSED_MILK, chooseIngredientQuantity());
                case "7" -> extraIngredientDefinitionMap.put(ExtraIngredient.ICE_CREAM, chooseIngredientQuantity());
                case "8" -> extraIngredientDefinitionMap.put(ExtraIngredient.WHIPPED_CREAM, chooseIngredientQuantity());
                case "9" -> extraIngredientDefinitionMap.put(ExtraIngredient.CINNAMON, chooseIngredientQuantity());
                case "10" -> extraIngredientDefinitionMap.put(ExtraIngredient.HOT_WATER, chooseIngredientQuantity());
                case "11" -> extraIngredientDefinitionMap.put(ExtraIngredient.ICE_CUBES, chooseIngredientQuantity());
                case "X" -> {
                    CoffeeRecipe coffeeRecipe = new CoffeeRecipe();
                    extraIngredientDefinitionMap.entrySet().forEach(i ->
                            coffeeRecipe.addExtraIngredient(new ExtraIngredientOnRecipe(i.getKey(), i.getValue())));
                    return coffeeRecipe;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
            consoleView.printChosenIngredientsForCurrentCoffee(extraIngredientDefinitionMap);
            consoleView.printIngredientsOptionListMessage();
        }
    }

    private void getCustomerName() {

        consoleView.printAskNameMessage();
        String ourCustomerName = input.readline();

        this.customerName = ourCustomerName;
    }

    private WhereToDrink setWhereToDrink() {

        consoleView.printAskWhereToDrinkMessage();
        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> {
                    return WhereToDrink.PICK_UP;
                }
                case "2" -> {
                    return WhereToDrink.TO_GO;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    public interface View {

        void printMainMenu();

        void printCoffeeOptionListMessage(Double[] coffeeTypePrices);

        void printCoffeeShotsOptionListMessage();

        void printAskShotsNumber();

        void askForQuantity();

        void printIngredientsOptionListMessage();

        void printChosenIngredientsForCurrentCoffee(Map<ExtraIngredient, Integer> extraIngredientDefinitionMap);

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