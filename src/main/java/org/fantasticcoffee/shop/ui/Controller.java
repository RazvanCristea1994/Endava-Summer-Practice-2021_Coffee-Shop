package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.CoffeeType;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.repository.IRepository;
import org.fantasticcoffee.shop.repository.InMemoryIRepository;
import org.fantasticcoffee.shop.service.ICoffee;
import org.fantasticcoffee.shop.service.IOrder;
import org.fantasticcoffee.shop.service.impl.CoffeeService;
import org.fantasticcoffee.shop.service.impl.OrderService;
import org.fantasticcoffee.shop.utils.Input;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final IOrder orderService;
    private final IRepository<Order> repository;
    private final ICoffee coffeeService;
    private String customerName;
    private final IView consoleView;
    private final Input input;

    public Controller() {
        this.repository = new InMemoryIRepository<>();
        this.coffeeService = new CoffeeService();
        this.orderService = new OrderService(repository, coffeeService);
        this.consoleView = new ConsoleView();
        this.input = new Input();
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
                case "3" -> {
                    if (coffeeList.isEmpty()) {
                        consoleView.printOrderEmptyMessage();
                        break;
                    }
                    coffeeList = placeOrder(coffeeList);
                }
                case "4" -> printAllOrders();
                case "5" -> updateOrder();
                case "6" -> cancelOrder();
                case "X" -> {
                    consoleView.printGoodByeMessage();
                    return;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private void cancelOrder() {

        this.consoleView.printAskForIdMessage();
        Integer orderId = this.input.readInt();

        this.orderService.deleteOrder(orderId);
        this.consoleView.printOrderCanceledMessage();
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

    private Order updateMenu(Order order) {

        List<Coffee> coffeeList = order.getCoffeeList();
        while (true) {
            if (!order.getCoffeeList().isEmpty()) {
                this.consoleView.printCoffeeListMessage(order.getCoffeeList());
            }
            consoleView.printUpdateOrderMessage();
            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> selectCoffee(coffeeList);
                case "2" -> removeCoffeeFromCustomerList(coffeeList);
                case "3" -> {
                    order.setOrderCoffeeList(coffeeList);
                    return order;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private List<Coffee> selectCoffee(List<Coffee> coffeeList) {

        while (true) {
            consoleView.printCoffeeOptionListMessage(this.coffeeService.getCoffeePrice(CoffeeType.ESPRESSO),
                    this.coffeeService.getCoffeePrice(CoffeeType.MACHIATTO),
                    this.coffeeService.getCoffeePrice(CoffeeType.CAFFEE_LATTE),
                    this.coffeeService.getCoffeePrice(CoffeeType.CAPPUCCINO),
                    this.coffeeService.getCoffeePrice(CoffeeType.CAFFEE_MIEL)
            );

            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> {
                    coffeeList.add(new Coffee(this.customerName, CoffeeType.ESPRESSO, chooseExtraIngredients()));
                    this.consoleView.printCoffeeListMessage(coffeeList);
                }
                case "2" -> {
                    coffeeList.add(new Coffee(this.customerName, CoffeeType.MACHIATTO, chooseExtraIngredients()));
                    this.consoleView.printCoffeeListMessage(coffeeList);
                }
                case "3" -> {
                    coffeeList.add(new Coffee(this.customerName, CoffeeType.CAFFEE_LATTE, chooseExtraIngredients()));
                    this.consoleView.printCoffeeListMessage(coffeeList);
                }
                case "4" -> {
                    coffeeList.add(new Coffee(this.customerName, CoffeeType.CAPPUCCINO, chooseExtraIngredients()));
                    this.consoleView.printCoffeeListMessage(coffeeList);
                }
                case "5" -> {
                    coffeeList.add(new Coffee(this.customerName, CoffeeType.CAFFEE_MIEL, chooseExtraIngredients()));
                    this.consoleView.printCoffeeListMessage(coffeeList);
                }
                case "6" -> {
                    List<Ingredient> ingredientList = new ArrayList<>();
                    ingredientList.addAll(chooseCoffeeShots());
                    ingredientList.addAll(chooseExtraIngredients());
                    coffeeList.add(new Coffee(this.customerName, CoffeeType.DEFAULT, ingredientList));
                    this.consoleView.printCoffeeListMessage(coffeeList);
                }
                case "X" -> {
                    return coffeeList;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private List<Coffee> placeOrder(List<Coffee> coffeeList) {

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

    private List<Ingredient> chooseCoffeeShots() {

        List<Ingredient> ingredientList = new ArrayList<>();
        consoleView.printCoffeeShotsOptionListMessage();
        while (true) {
            int shotsNumber = 0;
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> {
                    shotsNumber = chooseShotsNumber();
                    for (int i = 0; i < shotsNumber; i++) {
                        ingredientList.add(Ingredient.ESPRESSO_SHOT);
                    }
                    return ingredientList;
                }
                case "2" -> {
                    shotsNumber = chooseShotsNumber();
                    for (int i = 0; i < shotsNumber; i++) {
                        ingredientList.add(Ingredient.BLACK_COFFEE);
                    }
                    return ingredientList;
                }
                case "X" -> {
                    return new ArrayList<>();
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

    private List<Ingredient> chooseExtraIngredients() {

        consoleView.printIngredientsOptionListMessage();
        List<Ingredient> ingredientList = new ArrayList<>();

        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> ingredientList.add(Ingredient.MILK);
                case "2" -> ingredientList.add(Ingredient.HONEY);
                case "3" -> ingredientList.add(Ingredient.SYRUP);
                case "4" -> ingredientList.add(Ingredient.STEAMED_MILK);
                case "5" -> ingredientList.add(Ingredient.MILK_FOAM);
                case "6" -> ingredientList.add(Ingredient.SWEETENED_CONDENSED_MILK);
                case "7" -> ingredientList.add(Ingredient.ICE_CREAM);
                case "8" -> ingredientList.add(Ingredient.WHIPPED_CREAM);
                case "9" -> ingredientList.add(Ingredient.CINNAMON);
                case "10" -> ingredientList.add(Ingredient.HOT_WATER);
                case "11" -> ingredientList.add(Ingredient.ICE_CUBES);
                case "X" -> {
                    return ingredientList;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
            consoleView.printChosenIngredientsForCurrentCoffee(ingredientList);
            consoleView.printIngredientsOptionListMessage();
        }
    }

    private void getCustomerName() {

        consoleView.printAskNameMessage();
        String ourCustomerName = input.readline();

        this.customerName = ourCustomerName;
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

    private void printAllOrders() {

        List<Order> orderList = this.orderService.findAll();
        if (orderList.isEmpty()) {
            this.consoleView.printEmptyList();
        }
        orderList.forEach(this.consoleView::printAllOrders);
    }


    public interface IView {

        void printMainMenu();

        void printCoffeeOptionListMessage(Double espressoPrice,
                                          Double machiattoPrice,
                                          Double caffeeLatePrice,
                                          Double cappuccinoPrice,
                                          Double caffeeMielPrice);

        void printCoffeeShotsOptionListMessage();

        void printAskShotsNumber();

        void printIngredientsOptionListMessage();

        void printChosenIngredientsForCurrentCoffee(List<Ingredient> ingredientList);

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
