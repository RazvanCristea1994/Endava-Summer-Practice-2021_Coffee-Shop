package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.CoffeeType;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("consoleView")
public class ConsoleView implements AppController.IView {
    //ToDo pretty format needed in this class - printf stuff

    public void printMainMenu() {
        System.out.println("\n" + Utils.SHOP_NAME + "\n" +
                "1 - Select a coffee\n" +
                "2 - Remove a coffee\n" +
                "3 - Place Order\n" +
                "4 - Print all orders\n" +
                "5 - Update order\n" +
                "6 - Cancel order\n");
    }

    public void printUpdateOrderMessage() {
        System.out.println("Edit your order:\n" +
                "1 - Select a coffee\n" +
                "2 - Remove a coffee\n" +
                "3 - Place Order\n");
    }

    public void printCoffeeOptionListMessage(Double espressoPrice,
                                             Double machiattoPrice,
                                             Double caffeeLatePrice,
                                             Double cappuccinoPrice,
                                             Double caffeeMielPrice) {
        System.out.println("\n" + Utils.SHOP_NAME + "\n" +
                "1 - " + CoffeeType.ESPRESSO.getName() + "\t\t" + espressoPrice + "$\t" + CoffeeType.ESPRESSO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                "2 - " + CoffeeType.MACHIATTO.getName() + "\t\t" + machiattoPrice + "$\t" + CoffeeType.MACHIATTO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                "3 - " + CoffeeType.CAFFEE_LATTE.getName() + "\t" + caffeeLatePrice + "$\t" + CoffeeType.CAFFEE_LATTE.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                "4 - " + CoffeeType.CAPPUCCINO.getName() + "\t\t" + cappuccinoPrice + "$\t" + CoffeeType.CAPPUCCINO.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                "5 - " + CoffeeType.CAFFEE_MIEL.getName() + "\t\t" + caffeeMielPrice + "$\t" + CoffeeType.CAFFEE_MIEL.getRecipe().stream().map(Ingredient::getIngredientName).collect((Collectors.toList())) + "\n" +
                "6 - Be creative. Build your own Fantastic Coffee\n" +
                "---------------------\n" +
                "X - Exit\n"
        );
    }

    public void printCoffeeShotsOptionListMessage() {

        System.out.println("\n" + Utils.SHOP_NAME + "\n" +
                "1 - " + Ingredient.ESPRESSO_SHOT.getIngredientName() + "\t" + Ingredient.ESPRESSO_SHOT.getIngredientSellingPrice() + "$\n" +
                "2 - " + Ingredient.BLACK_COFFEE.getIngredientName() + "\t" + Ingredient.BLACK_COFFEE.getIngredientSellingPrice() + "$\n"
        );
    }

    public void printIngredientsOptionListMessage() {
        System.out.println(Utils.SHOP_NAME + "\n\n" +
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

    public void printAskWhereToDrinkMessage() {
        System.out.println("\n" + "Choose where you would like to drink:\n" +
                "1 - Pick-Up\n" +
                "2 - To-Go\n"
        );
    }

    public void printChosenIngredientsForCurrentCoffee(List<Ingredient> ingredientList) {

        printClosingLine();
        ingredientList.forEach(ingredient ->
                System.out.print("\t+" + ingredient.getIngredientName() + "\t" + ingredient.getIngredientSellingPrice() + "$\n"));
        printClosingLine();
    }

    public void printAskNameMessage() {
        System.out.println("\nWelcome! Provide a name to your order, please:\n");
    }

    public void printAskShotsNumber() {
        System.out.println("\nHow many shots would you like?\n");
    }

    public void printAskForIdMessage() {
        System.out.println("\nID: ");
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

    public void unknownError() {
        System.out.println("Unknown Error");
    }

    public void printEmptyList() {
        System.out.println("\nYour order list is empty now.\n");
    }

    public void printOrderCanceledMessage() {
        System.out.println("\nIf present, your order has been canceled\n");
    }

    public void printEnjoyCoffeeMessage() {
        System.out.println("\nEnjoy your coffee\n");
    }

    public void printCheckMessage(Order order, Double priceOrder, Double profitToday) {

        printHeader(order.getCoffeeList().get(0).getCustomerName());
        printIdOnCheck(order.getId());
        printItemsOnCheck(order.getCoffeeList());
        printTotalPriceOnCheck(priceOrder);
        printFooterOnCheck(order, profitToday);
    }

    public void printCoffeeListMessage(List<Coffee> coffeeList) {

        printHeader(coffeeList.get(0).getCustomerName());
        printItemsOnCheck(coffeeList);
        printClosingLine();
    }

    public void printAllOrders(Order order) {

        printHeader(order.getCoffeeList().get(0).getCustomerName());
        printIdOnCheck(order.getId());
        printItemsOnCheck(order.getCoffeeList());
        printClosingLine();
    }

    public void printItemsOnCheck(List<Coffee> coffeeList) {

        coffeeList.forEach(coffee -> {
                    System.out.println("#" + coffeeList.indexOf(coffee) + " - " + coffee.getCoffeeType().getName());
                    coffee.getCoffeeType().getRecipe().forEach(ingredient -> {
                        System.out.print("\t*" + ingredient.getIngredientName());
                        System.out.println("\t\t" + ingredient.getIngredientSellingPrice() + "$");
                    });
                    coffee.getExtraIngredientsList().forEach(ingredient -> {
                        System.out.print("\t+" + ingredient.getIngredientName());
                        System.out.println("\t\t" + ingredient.getIngredientSellingPrice() + "$");
                    });
                }
        );
    }

    private void printHeader(String customerName) {

        printClosingLine();
        System.out.println("\t\t\t\t\t" + Utils.SHOP_NAME);
        printClosingLine();
        System.out.println(customerName.toUpperCase() + "'s Coffee Type");
    }

    private void printFooterOnCheck(Order order, Double profit) {
        printClosingLine();
        System.out.println(order.getWhereToDrink().getName().toUpperCase());
        System.out.println("Today profit: " + profit + "$ \t\t\t" + order.getOrderDateTime().format(Utils.FORMATTER));
        printClosingLine();
    }

    private void printTotalPriceOnCheck(Double profit) {
        System.out.printf("%50s%5.2f$%n", "Total:", profit);
    }

    private void printIdOnCheck(Integer id) {
        System.out.println("Order ID: " + id);
    }

    private void printClosingLine() {
        System.out.println("===========================================================\n");
    }
}