package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.WhereToDrink;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.model.stock.BaseIngredientInStock;
import org.fantasticcoffee.shop.model.stock.ExtraIngredientInStock;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("consoleView")
public class ConsoleView implements AppController.View {

    private static final String SHOP_NAME = "Fantastic Coffee Shop";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    private static final String CLOSING_LINE = "=".repeat(65);
    private List<String> mainMenu = Arrays.asList(
            "Select a coffee",
            "Remove a coffee",
            "Place Order",
            "Print all orders",
            "Update order",
            "Cancel order",
            "My list",
            "Print ingredients stock");
    private static final List<String> updateOrderMenu = Arrays.asList(
            "Select a coffee",
            "Remove a coffee",
            "Place Order");

    public void printMainMenu() {

        for (int i = 0; i < mainMenu.size(); i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", mainMenu.get(i));
        }
        System.out.printf("%-3s %-1s %s %n", "X", "-", "Exit");
    }

    public void printUpdateOrderMessage() {

        for (int i = 0; i < updateOrderMenu.size(); i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", mainMenu.get(i));
        }
    }

    public void printCoffeeOptionListMessage(Double[] coffeeTypePrices) {

        System.out.println("\n" + SHOP_NAME);

        StandardCoffee[] standardCoffee = StandardCoffee.values();
        for (int i = 0; i < standardCoffee.length; i++) {
            System.out.printf("%-3s %-1s %-30s %-12s %s %n",
                    i + 1, "-", standardCoffee[i].getName(), "", "Choose any amazing ingredients");
        }

        System.out.printf("%-3s %-1s %-30s %n",
                6, "-", "Create your own Coffee");
        System.out.printf("%-3s %-1s %s %n", "X", "-", "Exit");
    }

    public void printCoffeeShotsOptionListMessage() {

        System.out.println("\n" + SHOP_NAME + "\n");
        BaseIngredient[] baseIngredients = BaseIngredient.values();
        for (int i = 0; i < baseIngredients.length; i++) {
            System.out.printf("%-3s %-1s %s", i + 1, "-", baseIngredients[i]);
        }
    }


    public void printIngredientsRepository(List<BaseIngredientInStock> baseIngredientsInStocks,
                                           List<ExtraIngredientInStock> extraIngredientInStocks) {

        baseIngredientsInStocks.forEach(baseIngredientInStock -> {
            System.out.printf("%-3s %-3s %s %n", baseIngredientInStock.getBaseIngredient().getIngredientName(), "-", baseIngredientInStock.getQuantity());
            if (baseIngredientInStock.getQuantity() <= 3) {
                System.out.println("Warning! Your supplies are running out fast.\n");
            }
        });

        extraIngredientInStocks.forEach(extraIngredientInStock -> {
            System.out.printf("%-3s %-3s %s %n", extraIngredientInStock.getExtraIngredient().getIngredientName(), " - ", extraIngredientInStock.getQuantity());
            if (extraIngredientInStock.getQuantity() <= 3) {
                System.out.println("Warning! Your supplies are running out fast.\n");
            }
        });
    }

    public void printIngredientsOptionListMessage() {

        System.out.println("\n" + SHOP_NAME + "\n");
        System.out.println("Choose our awesome extra ingredients:");

        ExtraIngredient[] extraIngredients = ExtraIngredient.values();
        for (int i = 0; i < extraIngredients.length; i++) {
            System.out.printf("%-3s %-1s %s", i + 1, "-", extraIngredients[i]);
        }

        System.out.println("X   - No, thanks");
    }

    public void printAskWhereToDrinkMessage() {

        System.out.println("\n" + "Choose where you would like to drink:\n");

        WhereToDrink[] whereToDrink = WhereToDrink.values();
        for (int i = 0; i < whereToDrink.length; i++) {
            System.out.printf("%-3s %-1s %s %n", i + 1, "-", whereToDrink[i].getName());
        }
    }

    public void printChosenIngredientsForCurrentCoffee(Map<ExtraIngredient, Integer> extraIngredientDefinitionMap) {

        System.out.println(CLOSING_LINE);
        extraIngredientDefinitionMap.forEach((ingredient, quantity) -> System.out.printf("%10s %-1s %s %s", "+", quantity, "x", ingredient));
        System.out.println(CLOSING_LINE);
    }

    public void chooseWhatKindToRemove() {
        System.out.println("1 -  Remove a Custom Coffee\n" +
                "2 - Remove a Standard Coffee\n" +
                "X - Go Back");
    }

    public void printAskNameMessage() {
        System.out.println("\nWelcome! Provide a name to your order, please:\n");
    }

    public void printAskShotsNumber() {
        System.out.println("\nHow many shots would you like?\n");
    }

    public void askForQuantity() {
        System.out.println("\nQuantity: ");
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

        printCustomizableStandardCoffeeOnCheck(order);
        printCustomCoffeeOnCheck(order);

        printTotalPriceOnCheck(priceOrder);
        printFooterOnCheck(order, profitToday);
    }

    public void printCoffeeListMessage(Order.Builder order) {

        if (!order.getCustomCoffeeList().isEmpty()) {
            printHeaderWithoutWhereToDrink(order.getCustomCoffeeList().get(0).getCustomerName());
            printCustomCoffeeOnCheck(order);
        }

        if (!order.getCustomizableStandardCoffee().isEmpty()) {
            printHeaderWithoutWhereToDrink(order.getCustomizableStandardCoffee().get(0).getCustomerName());
            printCustomizableStandardCoffeeOnCheck(order);
        }

        System.out.println(CLOSING_LINE);
    }

    public void printAllOrders(Order order) {

        if (!order.getCustomCoffeeList().isEmpty()) {
            printHeaderWithoutWhereToDrink(order.getCustomCoffeeList().get(0).getCustomerName());
        }

        if (!order.getCustomizableStandardCoffee().isEmpty()) {
            printHeaderWithoutWhereToDrink(order.getCustomizableStandardCoffee().get(0).getCustomerName());
        }
        printIdOnCheck(order.getId());

        printCustomizableStandardCoffeeOnCheck(order);
        printCustomCoffeeOnCheck(order);

        System.out.println(CLOSING_LINE);
    }

    private void printCustomizableStandardCoffeeOnCheck(Order order) {

        order.getCustomizableStandardCoffee().forEach(coffee -> {
            System.out.printf("%s %-3s %-1s %s %n", "#", order.getCustomizableStandardCoffee().indexOf(coffee), "-", coffee.getStandardCoffee().getName());

            if (coffee.getStandardCoffee().getRecipe().getBaseIngredients() != null) {
                coffee.getStandardCoffee().getRecipe().getBaseIngredients().forEach(baseCoffeeTypeIngredient -> System.out.printf("%10s %-1s %-1s %s", "<>", baseCoffeeTypeIngredient.getQuantity(), "x", baseCoffeeTypeIngredient.getBaseIngredient()));
            }

            if (coffee.getStandardCoffee().getRecipe().getExtraIngredients() != null) {
                coffee.getStandardCoffee().getRecipe().getExtraIngredients().forEach(extraCoffeeTypeIngredient -> System.out.printf("%10s %-1s %-1s %s", "<>", extraCoffeeTypeIngredient.getQuantity(), "x", extraCoffeeTypeIngredient.getExtraIngredient()));
            }

            if (coffee.getExtraIngredients() != null) {
                coffee.getExtraIngredients().forEach(additionalExtraIngredient -> System.out.printf("%10s %-1s %-1s %s", "+", additionalExtraIngredient.getQuantity(), "x", additionalExtraIngredient.getExtraIngredient()));
            }
        });
    }

    private void printCustomizableStandardCoffeeOnCheck(Order.Builder order) {

        order.getCustomizableStandardCoffee().forEach(coffee -> {
            System.out.printf("%s %-3s %-1s %s %n", "#", order.getCustomizableStandardCoffee().indexOf(coffee), "-", coffee.getStandardCoffee().getName());

            if (coffee.getStandardCoffee().getRecipe().getBaseIngredients() != null) {
                coffee.getStandardCoffee().getRecipe().getBaseIngredients().forEach(baseCoffeeTypeIngredient -> System.out.printf("%10s %-1s %-1s %s", "<>", baseCoffeeTypeIngredient.getQuantity(), "x", baseCoffeeTypeIngredient.getBaseIngredient()));
            }

            if (coffee.getStandardCoffee().getRecipe().getExtraIngredients() != null) {
                coffee.getStandardCoffee().getRecipe().getExtraIngredients().forEach(extraCoffeeTypeIngredient -> System.out.printf("%10s %-1s %-1s %s", "<>", extraCoffeeTypeIngredient.getQuantity(), "x", extraCoffeeTypeIngredient.getExtraIngredient()));
            }

            if (coffee.getExtraIngredients() != null) {
                coffee.getExtraIngredients().forEach(additionalExtraIngredient -> System.out.printf("%10s %-1s %-1s %s", "+", additionalExtraIngredient.getQuantity(), "x", additionalExtraIngredient.getExtraIngredient()));
            }
        });
    }

    private void printCustomCoffeeOnCheck(Order order) {

        order.getCustomCoffeeList().forEach(coffee -> {
            System.out.printf("%s %-3s %-1s %s %n", "#", order.getCustomCoffeeList().indexOf(coffee), "-", "Your creation");

            if (coffee.getCustomerMadeRecipe().getBaseIngredients() != null) {
                coffee.getCustomerMadeRecipe().getBaseIngredients().forEach(baseCoffeeTypeIngredient -> System.out.printf("%10s %-1s %-1s %s", "<>", baseCoffeeTypeIngredient.getQuantity(), "x", baseCoffeeTypeIngredient.getBaseIngredient()));
            }

            if (coffee.getCustomerMadeRecipe().getExtraIngredients() != null) {
                coffee.getCustomerMadeRecipe().getExtraIngredients().forEach(extraCoffeeTypeIngredient -> System.out.printf("%10s %-1s %-1s %s", "<>", extraCoffeeTypeIngredient.getQuantity(), "x", extraCoffeeTypeIngredient.getExtraIngredient()));
            }
        });
    }

    private void printCustomCoffeeOnCheck(Order.Builder order) {

        order.getCustomCoffeeList().forEach(coffee -> {
            System.out.printf("%s %-3s %-1s %s %n", "#", order.getCustomCoffeeList().indexOf(coffee), "-", "Your creation");

            if (coffee.getCustomerMadeRecipe().getBaseIngredients() != null) {
                coffee.getCustomerMadeRecipe().getBaseIngredients().forEach(baseCoffeeTypeIngredient -> System.out.printf("%10s %-1s %-1s %s", "<>", baseCoffeeTypeIngredient.getQuantity(), "x", baseCoffeeTypeIngredient.getBaseIngredient()));
            }

            if (coffee.getCustomerMadeRecipe().getExtraIngredients() != null) {
                coffee.getCustomerMadeRecipe().getExtraIngredients().forEach(extraCoffeeTypeIngredient -> System.out.printf("%10s %-1s %-1s %s", "<>", extraCoffeeTypeIngredient.getQuantity(), "x", extraCoffeeTypeIngredient.getExtraIngredient()));
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

        if (!order.getCustomCoffeeList().isEmpty()) {
            System.out.println(order.getCustomCoffeeList().get(0).getCustomerName().toUpperCase() + "'s Coffee Type");
        }
        if (!order.getCustomizableStandardCoffee().isEmpty()) {
            System.out.println(order.getCustomizableStandardCoffee().get(0).getCustomerName().toUpperCase() + "'s Coffee Type");
        }
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