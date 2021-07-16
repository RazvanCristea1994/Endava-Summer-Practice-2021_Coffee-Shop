package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.CoffeeType;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.utils.Utils;

import java.util.stream.Collectors;

public class ConsoleView implements Controller.IView {
    //ToDo pretty format needed in this class - printf stuff
    public void printCoffeeOptionListMessage() {
        System.out.println(
                "\n" + Utils.SHOP_NAME + "\n" +
                        "1 - " + CoffeeType.ESPRESSO.getName() + "\t\t" + CoffeeType.ESPRESSO.getPrice() + "$\t" + CoffeeType.ESPRESSO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "2 - " + CoffeeType.MACHIATTO.getName() + "\t\t" + CoffeeType.MACHIATTO.getPrice() + "$\t" + CoffeeType.MACHIATTO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "3 - " + CoffeeType.CAFFEE_LATTE.getName() + "\t" + CoffeeType.CAFFEE_LATTE.getPrice() + "$\t" + CoffeeType.CAFFEE_LATTE.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "4 - " + CoffeeType.CAPPUCCINO.getName() + "\t\t" + CoffeeType.CAPPUCCINO.getPrice() + "$\t" + CoffeeType.CAPPUCCINO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "5 - " + CoffeeType.CAFFEE_MIEL.getName() + "\t\t" + CoffeeType.CAFFEE_MIEL.getPrice() + "$\t" + CoffeeType.CAFFEE_MIEL.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                        "6 - Be creative. Build your own Fantastic Coffee\n" +
                        "---------------------\n" +
                        "7 - Remove an added coffee\n" +
                        "---------------------\n" +
                        "8 - Place my order\n" +
                        "---------------------\n" +
                        "9 - Print all orders\n" +
                        "X - Exit\n"
        );
    }

    public void printCoffeeShotsOptionListMessage() {

        System.out.println(
                "\n" + Utils.SHOP_NAME + "\n" +
                        "1 - " + Ingredient.ESPRESSO_SHOT.getIngredientName() + "\t" + Ingredient.ESPRESSO_SHOT.getIngredientSellingPrice() + "$\n" +
                        "2 - " + Ingredient.BLACK_COFFEE.getIngredientName() + "\t" + Ingredient.BLACK_COFFEE.getIngredientSellingPrice() + "$\n"
        );
    }

    public void printAskShotsNumber() {
        System.out.println("\nHow many shots would you like?\n");
    }

    public void printEmptyList() {
        System.out.println("\nYour order list is empty now.\n");
    }

    public void printIngredientsOptionListMessage() {
        System.out.println(
                Utils.SHOP_NAME + "\n\n" +
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
                        "---------------------------------------\n" +
                        "X  - No, thanks"
        );
    }

    public void printChosenIngredientsForCurrentCoffee(Coffee coffeeToBuild) {

        printOrderToBuildClosingLine();
        System.out.println(coffeeToBuild.getCoffeeType().getName() + " " + coffeeToBuild.getCoffeeType().getPrice());
        coffeeToBuild.getExtraIngredientsList().forEach(ingredient ->
                System.out.print("\t+" + ingredient.getIngredientName() + "\t" + ingredient.getIngredientSellingPrice() + "$\n"));
        System.out.println("Total: " + coffeeToBuild.getPrice() + "$");
        printOrderToBuildClosingLine();
    }

    public void printAskNameMessage() {
        System.out.println("\nWelcome! Provide a name to your order, please:\n");
    }

    public void printAskWhereToDrinkMessage() {
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

    public void printOrderEmptyMessage() {
        System.out.println("\nPlease choose one of our amazing coffees first");
    }

    public void printInvalidOptionMessage() {
        System.out.println("\nInvalid option\n");
    }

    public void printInvalidIdMessage() {
        System.out.println("\nInvalid ID\n");
    }

    public void printMenuAfterOrderPlacedMessage() {
        System.out.println("\nThe order has been placed.\n" +
                "\t[C] Continue\n" +
                "\t[E] Edit\n" +
                "\t[X] Cancel\n");
    }

    public void printUpdateOrderMessage() {
        System.out.println("Edit your order:");
    }

    public void printAskForCoffeeIdMessage() {
        System.out.println("\nID: ");
    }

    public void printOrderCanceledMessage() {
        System.out.println("\nYour order has been canceled\n");
    }

    public void printEnjoyCoffeeMessage() {
        System.out.println("\nEnjoy your coffee\n");
    }

    public void printCheckMessage(Order lastOrder, Double profit) {

        printCheckHeader(lastOrder.getOrderCoffeeList().get(0).getCustomerName());
        printOrdersAndPrices(lastOrder);
        printCheckFooter(lastOrder, profit);
    }


    public void printOrderListToBuildMessage(Order orderToBuild) {

        printCheckHeader(orderToBuild.getOrderCoffeeList().get(0).getCustomerName());
        printOrdersAndPrices(orderToBuild);
        printOrderToBuildClosingLine();
    }

    private void printCheckHeader(String customerName) {

        printOrderToBuildClosingLine();
        System.out.println("\t\t\t\t\t" + Utils.SHOP_NAME);
        printOrderToBuildClosingLine();
        System.out.println(customerName.toUpperCase() + "'s Coffee Type" + "\t\t\t\t\t\t" + "Price");
    }

    private void printCheckFooter(Order order, Double profit) {
        printOrderToBuildClosingLine();
        System.out.println(order.getWhereToDrink().getName().toUpperCase());
        System.out.println("Today profit: " + profit + "$ \t\t\t" + order.getOrderDateTime().format(Utils.FORMATTER));
        printOrderToBuildClosingLine();
    }

    private void printOrderToBuildClosingLine() {
        System.out.println("===========================================================\n");
    }

    private void printOrdersAndPrices(Order order) {

        order.getOrderCoffeeList().forEach(coffee -> {
                    System.out.println("#" + order.getOrderCoffeeList().indexOf(coffee) + " - " + coffee.getCoffeeType().getName() + "\t\t\t\t\t\t" + coffee.getCoffeeType().getPrice() + "$");
                    coffee.getExtraIngredientsList().forEach(ingredient -> {
                        System.out.print("\t+" + ingredient.getIngredientName());
                        System.out.println("\t\t" + ingredient.getIngredientSellingPrice() + "$");
                    });
                }
        );
        System.out.printf("%50s%5.2f$%n", "Total:", order.getTotalRevenue());
    }
}
