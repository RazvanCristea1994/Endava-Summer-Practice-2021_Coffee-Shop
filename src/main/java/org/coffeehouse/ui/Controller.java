package org.coffeehouse.ui;

import org.coffeehouse.model.Coffee;
import org.coffeehouse.model.CoffeeType;
import org.coffeehouse.model.Ingredient;
import org.coffeehouse.model.Order;
import org.coffeehouse.repository.IRepository;
import org.coffeehouse.repository.InMemoryIRepository;
import org.coffeehouse.service.IOrder;
import org.coffeehouse.service.OrderService;
import org.coffeehouse.utils.Input;

import java.util.List;

public class Controller {

    private IOrder orderService;
    private IRepository<Long, Order> repository;
    private Order orderToBuild = new Order();
    private String customerName;
    private IView consoleView;
    private final Input input;
    private Order placedOrder;

    public Controller() {
        this.repository = new InMemoryIRepository<>();
        this.orderService = new OrderService(repository);
        this.consoleView = new ConsoleView();
        this.input = new Input();
    }

    //ToDo: Erase any coffee from the orderList while building it
    public void runApp() {  //ToDo: Rethink this method

        while (true) {
            Coffee coffeeToBuild = new Coffee();
            if (this.customerName == null) {
                getCustomerName();
            }

            consoleView.printCoffeeOptionListMessage();
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1":
                    this.orderToBuild.addCoffeeToOrder(buildEspresso(coffeeToBuild));
                    this.consoleView.printOrderListToBuildMessage(this.orderToBuild);
                    break;
                case "2":
                    this.orderToBuild.addCoffeeToOrder(buildMachiatto(coffeeToBuild));
                    this.consoleView.printOrderListToBuildMessage(this.orderToBuild);
                    break;
                case "3":
                    this.orderToBuild.addCoffeeToOrder(buildCaffeeLate(coffeeToBuild));
                    this.consoleView.printOrderListToBuildMessage(this.orderToBuild);
                    break;
                case "4":
                    this.orderToBuild.addCoffeeToOrder(buildCappuccino(coffeeToBuild));
                    this.consoleView.printOrderListToBuildMessage(this.orderToBuild);
                    break;
                case "5":
                    this.orderToBuild.addCoffeeToOrder(buildCaffeeMiel(coffeeToBuild));
                    this.consoleView.printOrderListToBuildMessage(this.orderToBuild);
                    break;
                case "6":
                    removeCoffeeFromCustomerOrder();
                    this.consoleView.printOrderListToBuildMessage(this.orderToBuild);
                    break;
                case "7":
                    if (this.orderToBuild.getOrderCoffeeList().isEmpty()) {
                        consoleView.printOrderEmptyMessage();
                        break;
                    }
                    placeOrderOrUpdate();
                    break;
                case "8":
                    printAllOrders();
                    break;
                case "X":
                    consoleView.printGoodByeMessage();
                    return;
                default:
                    consoleView.printInvalidOptionMessage();
            }

            manageOrderIfPlaced();
        }
    }

    private void placeOrderOrUpdate() {

        setWhereToDrink(this.orderToBuild);
        if (this.orderToBuild.getWhereToDrink() == null) {
            return;
        }

        if (this.orderToBuild.getId() == null) {
            this.placedOrder = this.orderService.placeOrder(this.orderToBuild);
        } else {
            this.placedOrder = this.orderService.update(this.orderToBuild);
        }
        this.orderToBuild = new Order(placedOrder);
    }

    private Coffee buildEspresso(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.ESPRESSO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private Coffee buildMachiatto(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.MACHIATTO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private Coffee buildCaffeeLate(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAFFEE_LATTE);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private Coffee buildCappuccino(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAPPUCCINO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private Coffee buildCaffeeMiel(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAFFEE_MIEL);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private void setExtraIngredients(Coffee coffeeToBuild) {

        consoleView.printIngredientsOptionListMessage();
        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1":
                    coffeeToBuild.addExtraIngredient(Ingredient.MILK);
                    break;
                case "2":
                    coffeeToBuild.addExtraIngredient(Ingredient.HONEY);
                    break;
                case "3":
                    coffeeToBuild.addExtraIngredient(Ingredient.SYRUP);
                    break;
                case "4":
                    coffeeToBuild.addExtraIngredient(Ingredient.STEAMED_MILK);
                    break;
                case "5":
                    coffeeToBuild.addExtraIngredient(Ingredient.MILK_FOAM);
                    break;
                case "6":
                    coffeeToBuild.addExtraIngredient(Ingredient.SWEETENED_CONDENSED_MILK);
                    break;
                case "7":
                    coffeeToBuild.addExtraIngredient(Ingredient.ICE_CREAM);
                    break;
                case "8":
                    coffeeToBuild.addExtraIngredient(Ingredient.WHIPPED_CREAM);
                    break;
                case "9":
                    coffeeToBuild.addExtraIngredient(Ingredient.CINNAMON);
                    break;
                case "10":
                    coffeeToBuild.addExtraIngredient(Ingredient.HOT_WATER);
                    break;
                case "11":
                    coffeeToBuild.addExtraIngredient(Ingredient.ICE_CUBES);
                    break;
                case "12":
                    coffeeToBuild.addExtraIngredient(Ingredient.ESPRESSO_SHOT);
                    break;
                case "13":
                    coffeeToBuild.addExtraIngredient(Ingredient.BLACK_COFFEE);
                    break;
                case "X":
                    return;
                default:
                    consoleView.printInvalidOptionMessage();
            }
            consoleView.printIngredientsOptionListMessage();
        }
    }

    private void getCustomerName() {

        consoleView.printAskNameMessage();
        String ourCustomerName = input.readline();

        this.customerName = ourCustomerName;
    }

    private void removeCoffeeFromCustomerOrder() {

        consoleView.printAskForCoffeeIdMessage();
        int coffeeIndex = input.readInt();
        try {
            Coffee coffee = this.orderToBuild.getOrderCoffeeList().get(coffeeIndex);
            this.orderToBuild.getOrderCoffeeList().remove(coffee);
        } catch (IndexOutOfBoundsException e) {
            this.consoleView.printInvalidIdMessage();
        }
    }

    private void setWhereToDrink(Order currentOrder) {

        consoleView.printAskWhereToDrinkMessage();
        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1":
                    currentOrder.setWhereToDrink(Order.WhereToDrink.PICK_UP);
                    return;
                case "2":
                    currentOrder.setWhereToDrink(Order.WhereToDrink.TO_GO);
                    return;
                case "X":
                    return;
                default:
                    consoleView.printInvalidOptionMessage();
            }
        }
    }

    private String continueCancelOrEditOrder(Order placedOrder) {

        this.consoleView.printMenuAfterOrderPlacedMessage();
        while (true) {
            String option = input.readline();
            switch (option.toUpperCase()) {
                case "C":
                    this.consoleView.printEnjoyCoffeeMessage();
                    return "C";
                case "E":
                    this.consoleView.printUpdateOrderMessage();
                    return "E";
                case "X":
                    this.orderService.deleteOrder(placedOrder.getId());
                    this.consoleView.printOrderCanceledMessage();
                    return "X";
                default:
                    consoleView.printInvalidOptionMessage();
            }
        }
    }

    private void manageOrderIfPlaced() {

        if (placedOrder != null) {
            consoleView.printCheckMessage(placedOrder, orderService.getTotalProfitForToday());

            String option = continueCancelOrEditOrder(placedOrder);
            if (option.equalsIgnoreCase("C") || option.equalsIgnoreCase("X")) {
                prepareForNewOrder();
                this.orderToBuild = new Order();
            } else {
                orderToBuild = orderService.findOrder(orderToBuild.getId());
                placedOrder = null;
            }
        }
    }

    private void printAllOrders() {
        List<Order> orderList = this.orderService.findAll();
        orderList.forEach(o -> this.consoleView.printOrderListToBuildMessage(o));
    }

    private void prepareForNewOrder() {

        this.orderToBuild = new Order();
        this.customerName = null;
        this.placedOrder = null;
    }

    public interface IView {

        void printCoffeeOptionListMessage();

        void printIngredientsOptionListMessage();

        void printAskNameMessage();

        void printAskWhereToDrinkMessage();

        void printGoodByeMessage();

        void printOrderEmptyMessage();

        void printInvalidOptionMessage();

        void printInvalidIdMessage();

        void printMenuAfterOrderPlacedMessage();

        void printUpdateOrderMessage();

        void printAskForCoffeeIdMessage();

        void printOrderCanceledMessage();

        void printEnjoyCoffeeMessage();

        void printCheckMessage(Order placedOrder, Double profit);

        void printOrderListToBuildMessage(Order orderToBuild);
    }
}
