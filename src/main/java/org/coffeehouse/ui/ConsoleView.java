package org.coffeehouse.ui;

import org.coffeehouse.model.*;
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
                        "6 - Place my order\n" +
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

    public void printCheck(Order lastOrder, Double profit, String customerName) {      //ToDo some pretty format needed here
        //ToDo: Cancel order
        System.out.println("===========================================================");
        System.out.println(lastOrder.getWhereToDrink().getName().toUpperCase() + "\t\t\t\t" + Utils.SHOP_NAME);
        System.out.println("===========================================================");
        System.out.println(customerName.toUpperCase() + "'s Coffee Type" + "\t\t\t\t\t\t" + "Price");
        lastOrder.getOrderedCoffeeList().forEach(coffee -> {
                    System.out.println(coffee.getCoffeeType().getName() + "\t\t\t\t\t\t" + coffee.getPrice() + "$");
                    coffee.getExtraIngredientsList().forEach(ingredient -> {
                        System.out.print("\t+" + ingredient.getIngredientName());
                        System.out.println("\t\t" + ingredient.getIngredientSellingPrice() + "$");
                    });
                }
        );
        System.out.println("\t\t\t\t\t\t\tTotal: " + lastOrder.getTotalRevenue() + "$");
        System.out.println("===========================================================");
        System.out.println("Today profit: " + profit + "$ \t\t\t" + lastOrder.getOrderDateTime().format(Utils.FORMATTER));
        System.out.println("===========================================================\n");
    }
}
