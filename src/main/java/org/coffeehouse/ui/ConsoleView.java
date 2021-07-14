package org.coffeehouse.ui;

import org.coffeehouse.model.*;
import org.coffeehouse.utils.Utils;

public class ConsoleView implements Controller.IView {

    public void printCoffeeOptionList() {
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

    public void printIngredientsOptionList() {
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

    public void askForName() {
        System.out.println("\nProvide a name to your order, please:\n");
    }

    public void askWhereToDrink() {
        System.out.println(
                "\n" + "Choose where you would like to drink:\n" +
                        "1 - Pick-Up\n" +
                        "2 - To-Go\n" +
                        "----------------------\n" +
                        "X - Go back and order more coffee\n");
    }

    public void goodByeMessage() {
        System.out.println("\n" + Utils.SHOP_NAME + " wishes you an incredible day!");
    }

    public void errorOrderMessage() {
        System.out.println("\nPlease choose one of our amazing coffees first");
    }

    public void invalidOptionMessage() {
        System.out.println("\nInvalid option\n");
    }

    public void printCheck(Order lastOrder, Double profit) {      //ToDo some pretty format needed here

        System.out.println("===========================================================");
        System.out.println("\t\t\t\t" + Utils.SHOP_NAME);
        System.out.println("===========================================================");
        System.out.println("Coffee Type" + "\t\t\t\t\t\t\t" + "Price");
        lastOrder.getOrderedCoffeeList().forEach(coffee -> {
                    System.out.println(coffee.getCoffeeType().getName() + "\t\t\t\t\t\t" + coffee.getPrice());
                    System.out.println("\tExtra Ingredients:");
                    coffee.getExtraIngredientsList().forEach(ingredient -> {
                        System.out.print("\t+" + ingredient.getIngredientName());
                        System.out.println("\t\t" + ingredient.getIngredientSellingPrice());
                    });
                }
        );
        System.out.println("\t\t\t\t\t\t\tTotal: " + lastOrder.getTotalRevenue());
        System.out.println(Utils.SHOP_NAME + "'s profit for today," + lastOrder.getOrderDateTime() + " is " + profit); //ToDo: Ask what profit means
        System.out.println("===========================================================");
    }
}
