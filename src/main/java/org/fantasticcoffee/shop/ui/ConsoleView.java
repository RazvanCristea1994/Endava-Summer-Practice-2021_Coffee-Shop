package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.*;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component("consoleView")
public class ConsoleView implements AppController.IView {

    private static final String SHOP_NAME = "Fantastic Coffee Shop";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    private static final String CLOSING_LINE = "=".repeat(65);
    private static final String MAIN_MENU[] = {
            "Select a coffee",
            "Remove a coffee",
            "Place Order",
            "Print all orders",
            "Update order",
            "Cancel order",
            "My list"};
    private static final String UPDATE_ORDER_MENU[] = {
            "Select a coffee",
            "Remove a coffee",
            "Place Order"};

    public void printMainMenu() {

        for (int i = 0; i < MAIN_MENU.length; i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", MAIN_MENU[i]);
        }
        System.out.printf("%-3s %-1s %s %n", "X", "-", "Exit");
    }

    public void printUpdateOrderMessage() {

        for (int i = 0; i < UPDATE_ORDER_MENU.length; i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", MAIN_MENU[i]);
        }
    }

    public void printCoffeeOptionListMessage(Double coffeeTypePrices[]) {

        System.out.println("\n" + SHOP_NAME);

        CoffeeType coffeeType[] = CoffeeType.values();
        for (int i = 0; i < coffeeType.length; i++) {
            if (coffeeType[i].getRecipe().getBaseIngredients() == null && coffeeType[i].getRecipe().getExtraIngredients() == null) {
                System.out.printf("%-3s %-1s %-30s %-12s %s %n",
                        i + 1, "-", coffeeType[i].getName(), "", "Choose any amazing ingredients");
            } else {
                System.out.printf("%-3s %-1s %-30s %s%-8s %s %n",
                        i + 1, "-", coffeeType[i].getName(), coffeeTypePrices[i], "$",
                        coffeeType[i].getRecipe());
            }
        }

        System.out.printf("%-3s %-1s %s %n", "X", "-", "Exit");
    }

    public void printCoffeeShotsOptionListMessage() {

        System.out.println("\n" + SHOP_NAME + "\n");
        BaseIngredient baseIngredients[] = BaseIngredient.values();
        for (int i = 0; i < baseIngredients.length; i++) {
            System.out.printf("%-3s %-1s %s", i + 1, "-", baseIngredients[i]);
        }
    }

    public void printIngredientsOptionListMessage() {

        System.out.println("\n" + SHOP_NAME + "\n");
        System.out.println("Choose our awesome extra ingredients:");

        ExtraIngredient extraIngredients[] = ExtraIngredient.values();
        for (int i = 0; i < extraIngredients.length; i++) {
            System.out.printf("%-3s %-1s %s", i + 1, "-", extraIngredients[i]);
        }

        System.out.println("X   - No, thanks");
    }

    public void printAskWhereToDrinkMessage() {

        System.out.println("\n" + "Choose where you would like to drink:\n");

        Order.WhereToDrink whereToDrink[] = Order.WhereToDrink.values();
        for (int i = 0; i < whereToDrink.length; i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", whereToDrink[i].getName());
        }
    }

    public void printChosenIngredientsForCurrentCoffee(List<ExtraIngredient> extraIngredientList) {

        System.out.println(CLOSING_LINE);
        extraIngredientList.forEach(ingredient -> System.out.printf("%10s %s", "+", ingredient));
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
        System.out.println("\n" + SHOP_NAME + " wishes you an incredible day!");
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
            if (coffee.getCoffeeType().getRecipe().getBaseIngredients() != null) {
                coffee.getCoffeeType().getRecipe().getBaseIngredients().forEach(baseCoffeeTypeIngredient -> System.out.printf("%10s %s", "<>", baseCoffeeTypeIngredient));
            }
            if (coffee.getCoffeeType().getRecipe().getExtraIngredients() != null) {
                coffee.getCoffeeType().getRecipe().getExtraIngredients().forEach(extraCoffeeTypeIngredient -> System.out.printf("%10s %s", "<>", extraCoffeeTypeIngredient));
            }

            if (coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients() != null) {
                coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients().forEach(additionalBaseIngredient -> System.out.printf("%10s %s", "+", additionalBaseIngredient));
            }
            if (coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients() != null) {
                coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients().forEach(additionalExtraIngredient -> System.out.printf("%10s %s", "+", additionalExtraIngredient));
            }
        });
    }

    private void printHeaderWithoutWhereToDrink(String customerName) {

        System.out.println(CLOSING_LINE);
        System.out.printf("%40s %n", SHOP_NAME);
        System.out.println(CLOSING_LINE);
        System.out.println(customerName.toUpperCase() + "'s Coffee Type");
    }

    private void printHeaderWithWhereToDrink(Order order) {

        System.out.println(CLOSING_LINE);
        System.out.println(order.getWhereToDrink().getName().toUpperCase());
        System.out.printf("%42s %n%n", SHOP_NAME);
        System.out.println(CLOSING_LINE);
        System.out.println(order.getCoffeeList().get(0).getCustomerName().toUpperCase() + "'s Coffee Type");
    }

    private void printFooterOnCheck(Order order, Double profit) {
        System.out.println(CLOSING_LINE);
        System.out.printf("%-41s %s %5.2f %s  %n", order.getOrderDateTime().format(FORMATTER), "Today profit:", profit, "$");
        System.out.println(CLOSING_LINE);
    }

    private void printTotalPriceOnCheck(Double profit) {
        System.out.printf("%n%55s %5.2f$%n", "Total:", profit);
    }

    private void printIdOnCheck(Integer id) {
        System.out.println("Order ID: " + id);
    }
}