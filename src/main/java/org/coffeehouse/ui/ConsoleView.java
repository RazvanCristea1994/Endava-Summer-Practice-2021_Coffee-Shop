package org.coffeehouse.ui;

import org.coffeehouse.model.CoffeeType;
import org.coffeehouse.model.Ingredient;
import org.coffeehouse.model.Order;
import org.coffeehouse.utils.Utils;

import java.util.stream.Collectors;

public class ConsoleView implements Controller.IView {

    public void printCoffeeOptionList() {
        System.out.println(
                "\n" + Utils.SHOP_NAME + "\n" +
                        "1 - " + CoffeeType.ESPRESSO.getName() + "\t" + CoffeeType.ESPRESSO.getPrice() + "$\t" + CoffeeType.ESPRESSO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "2 - " + CoffeeType.MACHIATTO.getName() + "\t" + CoffeeType.MACHIATTO.getPrice() + "$\t" + CoffeeType.MACHIATTO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "3 - " + CoffeeType.CAFFEE_LATTE.getName() + "\t" + CoffeeType.CAFFEE_LATTE.getPrice() + "$\t" + CoffeeType.CAFFEE_LATTE.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "4 - " + CoffeeType.CAPPUCCINO.getName() + "\t" + CoffeeType.CAPPUCCINO.getPrice() + "$\t" + CoffeeType.CAPPUCCINO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "5 - " + CoffeeType.CAFFEE_MIEL.getName() + "\t" + CoffeeType.CAFFEE_MIEL.getPrice() + "$\t" + CoffeeType.CAFFEE_MIEL.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "---------------------\n" +
                        "6 - Remove an added coffee\n" +
                        "---------------------\n" +
                        "7 - Place my order\n" +
                        "X - Exit\n"
        );
    }

    public void printIngredientsOptionList() {
        System.out.println(
                "\n" + Utils.SHOP_NAME + "\n\n" +
                        "Choose our awesome extra ingredients:\n" +
                        "1  - " + Ingredient.MILK.getIngredientName() + "\t" + Ingredient.MILK.getIngredientSellingPrice() + "$\n" +
                        "2  - " + Ingredient.HONEY.getIngredientName() + "\t" + Ingredient.HONEY.getIngredientSellingPrice() + "$\n" +
                        "3  - " + Ingredient.SYRUP.getIngredientName() + "\t" + Ingredient.SYRUP.getIngredientSellingPrice() + "$\n" +
                        "4  - " + Ingredient.STEAMED_MILK.getIngredientName() + "\t" + Ingredient.STEAMED_MILK.getIngredientSellingPrice() + "\n" +
                        "5  - " + Ingredient.MILK_FOAM.getIngredientName() + "\t" + Ingredient.MILK_FOAM.getIngredientSellingPrice() + "$\n" +
                        "6  - " + Ingredient.SWEETENED_CONDENSED_MILK.getIngredientName() + "\t" + Ingredient.SWEETENED_CONDENSED_MILK.getIngredientSellingPrice() + "$\n" +
                        "7  - " + Ingredient.ICE_CREAM.getIngredientName() + "\t" + Ingredient.ICE_CREAM.getIngredientSellingPrice() + "$\n" +
                        "8  - " + Ingredient.WHIPPED_CREAM.getIngredientName() + "\t" + Ingredient.WHIPPED_CREAM.getIngredientSellingPrice() + "$\n" +
                        "9  - " + Ingredient.CINNAMON.getIngredientName() + "\t" + Ingredient.CINNAMON.getIngredientSellingPrice() + "$\n" +
                        "10 - " + Ingredient.HOT_WATER.getIngredientName() + "\t" + Ingredient.HOT_WATER.getIngredientSellingPrice() + "$\n" +
                        "11 - " + Ingredient.ICE_CUBES.getIngredientName() + "\t" + Ingredient.ICE_CUBES.getIngredientSellingPrice() + "$\n" +
                        "12 - " + Ingredient.ESPRESSO_SHOT.getIngredientName() + "\t" + Ingredient.ESPRESSO_SHOT.getIngredientSellingPrice() + "$\n" +
                        "13 - " + Ingredient.BLACK_COFFEE.getIngredientName() + "\t" + Ingredient.BLACK_COFFEE.getIngredientSellingPrice() + "$\n" +
                        "---------------------------------------\n" +
                        "X  - No, thanks"
        );
    }

    public void printAskName() {
        System.out.println("\nProvide a name to your order, please:\n");
    }

    public void printAskWhereToDrink() {
        System.out.println(
                "\n" + "Choose where you would like to drink:\n" +
                        "1 - Pick-Up\n" +
                        "2 - To-Go\n" +
                        "----------------------\n" +
                        "X - Go back and order more coffee\n");
    }

    public void printGoodByeMessage() {
        System.out.println("\n" + Utils.SHOP_NAME + " wishes you an incredible day!");
    }

    public void printErrorOrderMessage() {
        System.out.println("\nPlease choose one of our amazing coffees first");
    }

    public void printInvalidOptionMessage() {
        System.out.println("\nInvalid option\n");
    }

    public void printInvalidId() {
        System.out.println("\nInvalid ID\n");
    }

    public void printUnknownError() {
        System.out.println("Unknown error. Please try again.");
    }

    ;

    public void printAskToCancelMessage() {
        System.out.println("\nThe order has been placed. You can still cancel it [X] or continue [C]\n");
    }

    public void printAskForCoffeeId() {
        System.out.println("\nID: ");
    }

    public void printOrderCanceledMessage() {
        System.out.println("\nYour order has been canceled\n");
    }

    public void printEnjoyCoffeeMessage() {
        System.out.println("\nEnjoy your coffee\n");
    }

    public void printCheck(Order lastOrder, Double profit, String customerName) {      //ToDo some pretty format needed here

        printCheckHeader(customerName);
        printOrdersAndPrices(lastOrder);
        printCheckFooter(lastOrder, profit);
    }

    public void printOrderListToBuild(Order orderToBuild, String customerName) {

        printCheckHeader(customerName);
        printOrdersAndPrices(orderToBuild);
    }

    private void printCheckHeader(String customerName) { //ToDo: Better format here

        System.out.println("===========================================================");
        System.out.println("\t\t\t\t\t" + Utils.SHOP_NAME);
        System.out.println("===========================================================");
        System.out.println(customerName.toUpperCase() + "'s Coffee Type" + "\t\t\t\t\t\t" + "Price");
    }

    private void printCheckFooter(Order order, Double profit) {
        System.out.println("===========================================================");
        System.out.println(order.getWhereToDrink().getName().toUpperCase());
        System.out.println("Today profit: " + profit + "$ \t\t\t" + order.getOrderDateTime().format(Utils.FORMATTER));
        System.out.println("===========================================================\n");
    }

    private void printOrdersAndPrices(Order order) {

        order.getOrderedCoffeeList().forEach(coffee -> {
                    System.out.println("#" + coffee.getId() + " - " + coffee.getCoffeeType().getName() + "\t\t\t\t\t\t" + coffee.getPrice() + "$");
                    coffee.getExtraIngredientsList().forEach(ingredient -> {
                        System.out.print("\t+" + ingredient.getIngredientName());
                        System.out.println("\t\t" + ingredient.getIngredientSellingPrice() + "$");
                    });
                }
        );
        System.out.println("\t\t\t\t\t\t\tTotal: " + order.getTotalRevenue() + "$");
    }
}
