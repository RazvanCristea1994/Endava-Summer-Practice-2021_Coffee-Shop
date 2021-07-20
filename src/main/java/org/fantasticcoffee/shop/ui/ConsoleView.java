package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.*;
import org.fantasticcoffee.shop.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("consoleView")
public class ConsoleView implements AppController.IView {

    private final static String CLOSING_LINE = "=".repeat(65);
    private final static String MAIN_MENU[] = {
            "Select a coffee",
            "Remove a coffee",
            "Place Order",
            "Print all orders",
            "Update order",
            "Cancel order"};
    private final static String UPDATE_ORDER_MENU[] = {
            "Select a coffee",
            "Remove a coffee",
            "Place Order"};

    public void printMainMenu() {

        for (int i = 0; i < MAIN_MENU.length; i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", MAIN_MENU[i]);
        }
    }

    public void printUpdateOrderMessage() {

        for (int i = 0; i < UPDATE_ORDER_MENU.length; i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", MAIN_MENU[i]);
        }
    }

    public void printCoffeeOptionListMessage(Double espressoPrice,
                                             Double machiattoPrice,
                                             Double caffeeLatePrice,
                                             Double cappuccinoPrice,
                                             Double caffeeMielPrice) {

        System.out.println("\n" + Utils.SHOP_NAME);

        CoffeeType coffeeType[] = CoffeeType.values();
        for (int i = 0; i < coffeeType.length; i++) {
            System.out.printf("%-3s %-1s %-30s %s%-8s %s %n",
                    i + 1, "-", coffeeType[i].getName(), espressoPrice, "$", coffeeType[i].getRecipe().stream()
                            .map(Ingredient::getIngredientName)
                            .collect((Collectors.toList())));
        }

        System.out.printf("%-3s %-1s %s %n", "X", "-", "Exit");
    }

    public void printCoffeeShotsOptionListMessage() { //ToDo: Replace the actual implementation with the commented one

        System.out.println("\n" + Utils.SHOP_NAME + "\n" +
                "1 - " + Ingredient.ESPRESSO_SHOT +
                "2 - " + Ingredient.BLACK_COFFEE);

        /*System.out.println("\n" + Utils.SHOP_NAME + "\n");
        BaseIngredient baseIngredients[] = BaseIngredient.values();
        for (int i = 0; i < baseIngredients.length; i++) {
            System.out.printf("%-3s %-1s %s", i + 1, "-", baseIngredients[i]);
        }*/
    }

    public void printIngredientsOptionListMessage() { //ToDo: Replace the actual implementation with the commented one

        System.out.println(Utils.SHOP_NAME + "\n\n" +
                "Choose our awesome extra ingredients:\n" +
                "1  - " + Ingredient.MILK +
                "2  - " + Ingredient.HONEY +
                "3  - " + Ingredient.SYRUP +
                "4  - " + Ingredient.STEAMED_MILK +
                "5  - " + Ingredient.MILK_FOAM +
                "6  - " + Ingredient.SWEETENED_CONDENSED_MILK +
                "7  - " + Ingredient.ICE_CREAM +
                "8  - " + Ingredient.WHIPPED_CREAM +
                "9  - " + Ingredient.CINNAMON +
                "10 - " + Ingredient.HOT_WATER +
                "11 - " + Ingredient.ICE_CUBES +
                "X  - No, thanks"
        );

        /*System.out.println("\n" + Utils.SHOP_NAME + "\n");
        System.out.println("Choose our awesome extra ingredients:");

        ExtraIngredient extraIngredients[] = ExtraIngredient.values();
        for (int i = 0; i < extraIngredients.length; i++) {
            System.out.printf("%-3s %-1s %s", i + 1, "-", extraIngredients[i]);
        }

        System.out.println("X   - No, thanks");*/
    }

    public void printAskWhereToDrinkMessage() {

        System.out.println("\n" + "Choose where you would like to drink:\n");

        Order.WhereToDrink whereToDrink[] = Order.WhereToDrink.values();
        for (int i = 0; i < whereToDrink.length; i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", whereToDrink[i].getName());
        }
    }

    public void printChosenIngredientsForCurrentCoffee(List<Ingredient> ingredientList) {

        System.out.println(CLOSING_LINE);
        ingredientList.forEach(ingredient -> System.out.printf("%10s %s", "+", ingredient));
        System.out.println(CLOSING_LINE);
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

        printHeaderWithWhereToDrink(order);
        printIdOnCheck(order.getId());
        printItemsOnCheck(order.getCoffeeList());
        printTotalPriceOnCheck(priceOrder);
        printFooterOnCheck(order, profitToday);
    }

    public void printCoffeeListMessage(List<Coffee> coffeeList) {

        printHeaderWithoutWhereToDrink(coffeeList.get(0).getCustomerName());
        printItemsOnCheck(coffeeList);
        System.out.println(CLOSING_LINE);
    }

    public void printAllOrders(Order order) {

        printHeaderWithoutWhereToDrink(order.getCoffeeList().get(0).getCustomerName());
        printIdOnCheck(order.getId());
        printItemsOnCheck(order.getCoffeeList());
        System.out.println(CLOSING_LINE);
    }

    private void printItemsOnCheck(List<Coffee> coffeeList) {

        coffeeList.forEach(coffee -> {
            System.out.printf("%s %-3s %-1s %s %n", "#", coffeeList.indexOf(coffee), "-", coffee.getCoffeeType().getName());
            coffee.getCoffeeType().getRecipe().forEach(ingredient -> System.out.printf("%10s %s", "<>", ingredient));

            coffee.getIngredientsList().forEach(ingredient -> System.out.printf("%10s %s", "+", ingredient));
        });
    }

    private void printHeaderWithoutWhereToDrink(String customerName) {

        System.out.println(CLOSING_LINE);
        System.out.printf("%40s %n", Utils.SHOP_NAME);
        System.out.println(CLOSING_LINE);
        System.out.println(customerName.toUpperCase() + "'s Coffee Type");
    }

    private void printHeaderWithWhereToDrink(Order order) {

        System.out.println(CLOSING_LINE);
        System.out.println(order.getWhereToDrink().getName().toUpperCase());
        System.out.printf("%42s %n%n", Utils.SHOP_NAME);
        System.out.println(CLOSING_LINE);
        System.out.println(order.getCoffeeList().get(0).getCustomerName().toUpperCase() + "'s Coffee Type");
    }

    private void printFooterOnCheck(Order order, Double profit) {
        System.out.println(CLOSING_LINE);
        System.out.printf("%-41s %s %5.2f %s  %n", order.getOrderDateTime().format(Utils.FORMATTER), "Today profit:", profit, "$");
        System.out.println(CLOSING_LINE);
    }

    private void printTotalPriceOnCheck(Double profit) {
        System.out.printf("%n%55s %5.2f$%n", "Total:", profit);
    }

    private void printIdOnCheck(Integer id) {
        System.out.println("Order ID: " + id);
    }
}