package org.coffeehouse.ui;

import org.coffeehouse.model.*;
import org.coffeehouse.repository.IRepository;
import org.coffeehouse.repository.InMemoryIRepository;
import org.coffeehouse.service.IOrder;
import org.coffeehouse.service.OrderService;
import org.coffeehouse.utils.Input;

import java.util.ArrayList;
import java.util.Collection;

public class Controller {

    private IOrder orderService;
    private IRepository repository;
    private Collection<Coffee> customerCoffeeOrder;
    private String customerName;
    private IView consoleView;
    private final Input input;

    public Controller() {
        this.repository = new InMemoryIRepository();
        this.orderService = new OrderService(repository);
        this.customerCoffeeOrder = new ArrayList<>();
        this.consoleView = new ConsoleView();
        this.input = new Input();
    }

                             //ToDo: Show the total price in real time and the order list
                            //ToDo: Erase any coffee from the orderList while building it
    public void runApp() {  //ToDo: Rethink this method

        while (true) {

            Coffee coffeeToBuild = new Coffee();
            if (this.customerName == null) {
                getCustomerName(coffeeToBuild);
            }

            consoleView.printCoffeeOptionList();
            String option = input.readline();
            if (option.equalsIgnoreCase("x")) {
                consoleView.goodByeMessage();
                break;
            }

            switch (option) {
                case "1":
                    buildEspresso(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "2":
                    buildMachiatto(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "3":
                    buildCaffeeLate(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "4":
                    buildCappuccino(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "5":
                    buildCaffeeMiel(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "6":
                    if (this.customerCoffeeOrder.isEmpty()) {
                        consoleView.errorOrderMessage();
                        break;
                    }
                    Order currentOrder = buildOrder();
                    Order placedOrder = placeOrder(currentOrder);
                    consoleView.printCheck(placedOrder, orderService.getTotalProfitForToday(), this.customerName);
                    prepareForNewOrder();
                    break;
                default:
                    consoleView.invalidOptionMessage();
            }
        }
    }

    private void buildEspresso(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.ESPRESSO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);
    }

    private void buildMachiatto(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.MACHIATTO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);
    }

    private void buildCaffeeLate(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAFFEE_LATTE);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);
    }

    private void buildCappuccino(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAPPUCCINO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);
    }

    private void buildCaffeeMiel(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAFFEE_MIEL);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);
    }

    private Order buildOrder() {

        Order currentOrder = new Order();
        setWhereToDrink(currentOrder);
        if (currentOrder.getWhereToDrink() == null) {
            return null;
        }
        currentOrder.setOrderedCoffeeList(this.customerCoffeeOrder);

        return currentOrder;
    }

    private void setExtraIngredients(Coffee coffeeToBuild) {

        consoleView.printIngredientsOptionList();
        while (true) {
            String option = input.readline();

            if (option.equalsIgnoreCase("x")) {
                return;
            }
            switch (option) {
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
                default:
                    consoleView.invalidOptionMessage();
            }
            consoleView.printIngredientsOptionList();
        }
    }

    private void getCustomerName(Coffee coffeeToBuild) {

        consoleView.askForName();
        String customerName = input.readline();

        this.customerName = customerName;
    }

    private void setWhereToDrink(Order currentOrder) {

        consoleView.askWhereToDrink();
        while (true) {
            String option = input.readline();
            //ToDo: add the option to go back
            switch (option) {
                case "1":
                    currentOrder.setWhereToDrink(Order.WhereToDrink.PICK_UP);
                    return;
                case "2":
                    currentOrder.setWhereToDrink(Order.WhereToDrink.TO_GO);
                    return;
                case "X":
                    return;
                default:
                    consoleView.invalidOptionMessage();
            }
        }
    }

    private Order placeOrder(Order order) {

        return orderService.placeOrder(order);
    }

    private void prepareForNewOrder() {

        this.customerCoffeeOrder = new ArrayList<>();
        this.customerName = null;
    }

    public interface IView {

        void printCoffeeOptionList();

        void printIngredientsOptionList();

        void askForName();

        void askWhereToDrink();

        void goodByeMessage();

        void errorOrderMessage();

        void invalidOptionMessage();

        void printCheck(Order lastOrder, Double profit, String customerName);
    }
}
