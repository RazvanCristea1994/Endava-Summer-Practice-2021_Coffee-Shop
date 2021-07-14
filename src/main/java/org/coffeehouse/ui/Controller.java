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
    private IView consoleView;
    private final Input input = new Input();

    public Controller() {
        this.repository = new InMemoryIRepository();
        this.orderService = new OrderService(repository);
        this.customerCoffeeOrder = new ArrayList<>();
        this.consoleView = new ConsoleView();
    }

    public void runApp() { //ToDo: each coffee to have it's owner name, not the order

        consoleView.printCoffeeOptionList();
        while (true) {
            String option = input.readline();
            if (option.equalsIgnoreCase("x")) {
                consoleView.goodByeMessage();
                break;
            }
            Coffee coffeeToBuild = new Coffee();
            switch (option) {
                case "1":
                    addEspresso(coffeeToBuild);
                    getExtraIngredients(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "2":
                    addMachiatto(coffeeToBuild);
                    getExtraIngredients(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "3":
                    addCaffeeLate(coffeeToBuild);
                    getExtraIngredients(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "4":
                    addCappuccino(coffeeToBuild);
                    getExtraIngredients(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "5":
                    addCaffeeMiel(coffeeToBuild);
                    getExtraIngredients(coffeeToBuild);
                    this.customerCoffeeOrder.add(coffeeToBuild);
                    break;
                case "6":
                    if (this.customerCoffeeOrder.isEmpty()) {
                        consoleView.errorOrderMessage();
                        break;
                    }
                    getCustomerName(coffeeToBuild);
                    chooseWhereToDrink(coffeeToBuild);
                    Order lastOrder = placeOrder(coffeeToBuild);
                    consoleView.printCheck(lastOrder);
                    this.customerCoffeeOrder = new ArrayList<>();
                    break;
                default:
                    consoleView.invalidOptionMessage();
            }
            consoleView.printCoffeeOptionList();
        }
    }

    private void addEspresso(Coffee coffeeToBuild) {
        coffeeToBuild.setCoffeeType(CoffeeType.ESPRESSO);
    }

    private void addMachiatto(Coffee coffeeToBuild) {
        coffeeToBuild.setCoffeeType(CoffeeType.MACHIATTO);
    }

    private void addCaffeeLate(Coffee coffeeToBuild) {
        coffeeToBuild.setCoffeeType(CoffeeType.CAFFEE_LATTE);
    }

    private void addCappuccino(Coffee coffeeToBuild) {
        coffeeToBuild.setCoffeeType(CoffeeType.CAPPUCCINO);
    }

    private void addCaffeeMiel(Coffee coffeeToBuild) {
        coffeeToBuild.setCoffeeType(CoffeeType.CAFFEE_MIEL);
    }

    private void getExtraIngredients(Coffee coffeeToBuild) {

        consoleView.printIngredientsOptionList();
        while (true) {
            String option = input.readline();;
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
                    System.out.println("Invalid option");
            }
            consoleView.printIngredientsOptionList();
        }
    }

    private void getCustomerName(Coffee coffeeToBuild) {

        consoleView.askForName();
        String customerName = input.readline();;
        coffeeToBuild.setCustomerName(customerName);
    }

    private void chooseWhereToDrink(Coffee coffeeToBuild) {

        consoleView.askWhereToDrink();
        while (true) {
            String option = input.readline();; //ToDo: add the option to go back
            switch (option) {
                case "1":
                    coffeeToBuild.setWhereToDrink(CoffeeBase.WhereToDrink.PICK_UP);
                    return;
                case "2":
                    coffeeToBuild.setWhereToDrink(CoffeeBase.WhereToDrink.TO_GO);
                    return;
                default:
                    consoleView.invalidOptionMessage();
            }
        }
    }

    private Order placeOrder(Coffee coffeeToBuild) {

        Order order = new Order(this.customerCoffeeOrder);
        return orderService.placeOrder(order);
    }

    public interface IView {

        void printCoffeeOptionList();

        void printIngredientsOptionList();

        void askForName();

        void askWhereToDrink();

        void goodByeMessage();

        void errorOrderMessage();

        void invalidOptionMessage();

        void printCheck(Order lastOrder);
    }
}
