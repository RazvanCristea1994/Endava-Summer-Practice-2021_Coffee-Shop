package org.coffeehouse.ui;

import org.coffeehouse.model.*;
import org.coffeehouse.repository.IRepository;
import org.coffeehouse.repository.InMemoryIRepository;
import org.coffeehouse.service.IOrder;
import org.coffeehouse.service.OrderService;
import org.coffeehouse.utils.Utils;

import java.util.*;

public class Console {

    private IOrder orderService;
    private IRepository repository;
    private Scanner scanner;
    private Collection<Coffee> customerCoffeeOrder;

    public Console() {
        this.repository = new InMemoryIRepository();
        this.orderService = new OrderService(repository);
        this.scanner = new Scanner(System.in);
        this.customerCoffeeOrder = new ArrayList<>();
    }

    public void runMenu() { //ToDo: each coffee to have it's owner name, not the order

        printCoffeeOptionList();
        while (true) {
            String option = scanner.next();
            if (option.equalsIgnoreCase("x")) {
                goodByeMessage();
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
                        errorOrderMessage();
                        break;
                    }
                    getCustomerName(coffeeToBuild);
                    chooseWhereToDrink(coffeeToBuild);
                    Order lastOrder = placeOrder(coffeeToBuild);
                    printCheck(lastOrder);
                    break;
                default:
                    invalidOptionMessage();
            }
            printCoffeeOptionList();
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

        printIngredientsOptionList();
        while (true) {
            String option = scanner.next();
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
            printIngredientsOptionList();
        }
    }

    private void getCustomerName(Coffee coffeeToBuild) {

        askForName();

        Scanner scanner = new Scanner(System.in);
        String customerName = scanner.next();

        coffeeToBuild.setCustomerName(customerName);
    }

    private void chooseWhereToDrink(Coffee coffeeToBuild) {

        askWhereToDrink();
        while (true) {
            String option = scanner.next(); //ToDo: add the option to go back
            switch (option) {
                case "1":
                    coffeeToBuild.setWhereToDrink(CoffeeBase.WhereToDrink.PICK_UP);
                    return;
                case "2":
                    coffeeToBuild.setWhereToDrink(CoffeeBase.WhereToDrink.TO_GO);
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private Order placeOrder(Coffee coffeeToBuild) {

        Order order = new Order(this.customerCoffeeOrder);
        return orderService.placeOrder(order);
    }

    private void printCoffeeOptionList() {
        System.out.println(
                "\n" + Utils.SHOP_NAME + "\n" +
                        "1 - " + CoffeeType.ESPRESSO.getName() + "\n" +
                        "2 - " + CoffeeType.MACHIATTO.getName() + "\n" +
                        "3 - " + CoffeeType.CAFFEE_LATTE.getName() + "\n" +
                        "4 - " + CoffeeType.CAPPUCCINO.getName() + "\n" +
                        "5 - " + CoffeeType.CAFFEE_MIEL.getName() + "\n" +
                        "---------------------\n" +
                        "6 - Place my order\n" +
                        "X - Exit\n"
        );
    }

    private void printIngredientsOptionList() {
        System.out.println(
                "\n" + Utils.SHOP_NAME + "\n\n" +
                        "Choose our awesome extra ingredients:\n" +
                        "1  - " + Ingredient.MILK.getIngredientName() + "\n" +
                        "2  - " + Ingredient.HONEY.getIngredientName() + "\n" +
                        "3  - " + Ingredient.SYRUP.getIngredientName() + "\n" +
                        "4  - " + Ingredient.STEAMED_MILK.getIngredientName() + "\n" +
                        "5  - " + Ingredient.MILK_FOAM.getIngredientName() + "\n" +
                        "6  - " + Ingredient.SWEETENED_CONDENSED_MILK.getIngredientName() + "\n" +
                        "7  - " + Ingredient.ICE_CREAM.getIngredientName() + "\n" +
                        "8  - " + Ingredient.WHIPPED_CREAM.getIngredientName() + "\n" +
                        "9  - " + Ingredient.CINNAMON.getIngredientName() + "\n" +
                        "10 - " + Ingredient.HOT_WATER.getIngredientName() + "\n" +
                        "11 - " + Ingredient.ICE_CUBES.getIngredientName() + "\n" +
                        "12 - " + Ingredient.ESPRESSO_SHOT.getIngredientName() + "\n" +
                        "13 - " + Ingredient.BLACK_COFFEE.getIngredientName() + "\n" +
                        "---------------------------------------\n" +
                        "X  - No, thanks"
        );
    }

    private void askForName() {
        System.out.println("\nProvide a name to your order, please:\n");
    }

    private void askWhereToDrink() {
        System.out.println(
                "\n" + "Choose where you would like to drink:\n" +
                        "1 - Pick-Up\n" +
                        "2 - To-Go\n" +
                        "----------------------\n" +
                        "X - Go back and order more coffee\n");
    }

    private void goodByeMessage() {
        System.out.println("\n" + Utils.SHOP_NAME + " wishes you an incredible day!");
    }

    private void errorOrderMessage() {
        System.out.println("\nPlease choose one of our amazing coffees first");
    }

    private void invalidOptionMessage() {
        System.out.println("\nInvalid option\n");
    }

    private void printCheck(Order lastOrder) {      //ToDo some pretty format needed here

        System.out.println("===========================================================");
        System.out.println("\t\t\t\t" + Utils.SHOP_NAME);
        System.out.println("===========================================================");
        System.out.println("Coffee Type" + "\t\t\t\t\t\t\t" + "Price");
        lastOrder.getOrderedCoffeeList().forEach(coffee -> {
                    System.out.println(coffee.getCoffeeType().getName() + "\t\t\t\t\t\t" + coffee.getPrice());
                    System.out.println("\tExtra Ingredients:");
                    coffee.getExtraIngredientsList().forEach(ingredient -> {
                        System.out.print("\t+" + ingredient.getIngredientName());
                        System.out.println("\t\t" + ingredient.getIngredientPrice());
                    });
                }
        );
        System.out.println("\t\t\t\t\t\t\tTotal: " + lastOrder.getTotalCost());
        System.out.println(Utils.SHOP_NAME + "'s profit for today," + lastOrder.getOrderDateTime() + " is "); //ToDo: Ask what profit means
        System.out.println("===========================================================");
    }
}
