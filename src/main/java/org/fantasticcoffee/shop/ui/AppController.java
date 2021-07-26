package org.fantasticcoffee.shop.ui;

import org.fantasticcoffee.shop.model.WhereToDrink;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.Recipe;
import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;
import org.fantasticcoffee.shop.model.stock.BaseIngredientInStock;
import org.fantasticcoffee.shop.model.stock.ExtraIngredientInStock;
import org.fantasticcoffee.shop.service.BaseIngredientService;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.fantasticcoffee.shop.service.ExtraIngredientService;
import org.fantasticcoffee.shop.service.OrderService;
import org.fantasticcoffee.shop.service.impl.factory.CoffeeFactory;
import org.fantasticcoffee.shop.utils.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("controller")
public class AppController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private BaseIngredientService baseIngredientService;
    @Autowired
    private ExtraIngredientService extraIngredientService;
    @Autowired
    private View consoleView;
    @Autowired
    private Input input;

    private String customerName;

    public void runApp() {

        this.baseIngredientService.seedStock();
        this.extraIngredientService.seedStock();

        Order.Builder order = new Order.Builder();
        while (true) {
            if (this.customerName == null) {
                getCustomerName();
            }

            consoleView.printMainMenu();
            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> selectCoffee(order);
                case "2" -> removeCoffeeFromCustomerList(order);
                case "3" -> order = placeOrder(order);
                case "4" -> printAllOrders();
                case "5" -> updateOrder();
                case "6" -> cancelOrder();
                case "7" -> printCurrentOrder(order);
                case "8" -> this.consoleView.printIngredientsRepository(
                        this.baseIngredientService.getAllBaseIngredientsInStock(),
                        this.extraIngredientService.getAllExtraIngredientsInStock());
                case "X" -> {
                    consoleView.printGoodByeMessage();
                    return;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private Order.Builder selectCoffee(Order.Builder order) {

        while (true) {
            consoleView.printCoffeeOptionListMessage(new Double[]{this.coffeeService.getStandardCoffeePrice(StandardCoffee.ESPRESSO),
                    this.coffeeService.getStandardCoffeePrice(StandardCoffee.MACHIATTO),
                    this.coffeeService.getStandardCoffeePrice(StandardCoffee.CAFFEE_LATTE),
                    this.coffeeService.getStandardCoffeePrice(StandardCoffee.CAPPUCCINO),
                    this.coffeeService.getStandardCoffeePrice(StandardCoffee.CAFFEE_MIEL)}
            );

            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> order.addCustomizableStandardCoffee(
                        CoffeeFactory.createStandardCoffee(StandardCoffee.ESPRESSO)
                                .create(this.customerName, chooseExtraIngredients().getExtraIngredients()));
                case "2" -> order.addCustomizableStandardCoffee(
                        CoffeeFactory.createStandardCoffee(StandardCoffee.MACHIATTO)
                                .create(this.customerName, chooseExtraIngredients().getExtraIngredients()));
                case "3" -> order.addCustomizableStandardCoffee(
                        CoffeeFactory.createStandardCoffee(StandardCoffee.CAFFEE_LATTE)
                                .create(this.customerName, chooseExtraIngredients().getExtraIngredients()));
                case "4" -> order.addCustomizableStandardCoffee(
                        CoffeeFactory.createStandardCoffee(StandardCoffee.CAPPUCCINO)
                                .create(this.customerName, chooseExtraIngredients().getExtraIngredients()));
                case "5" -> order.addCustomizableStandardCoffee(
                        CoffeeFactory.createStandardCoffee(StandardCoffee.CAFFEE_MIEL)
                                .create(this.customerName, chooseExtraIngredients().getExtraIngredients()));
                case "6" -> order.addCustomCoffee(new CustomCoffee(this.customerName, createCustomCoffee()));
                case "X" -> {
                    return order;
                }
                default -> consoleView.printInvalidOptionMessage();
            }

            if (order.getCustomCoffeeList().isEmpty() && order.getCustomizableStandardCoffee().isEmpty()) {
                this.consoleView.printEmptyList();
            }
            this.consoleView.printCoffeeListMessage(order);
        }
    }

    private Recipe createCustomCoffee() {

        List<BaseIngredientOnRecipe> baseIngredientOnRecipe = chooseCoffeeShots().getBaseIngredients();
        List<ExtraIngredientOnRecipe> extraIngredientOnRecipe = chooseExtraIngredients().getExtraIngredients();

        Recipe.Builder builder = new Recipe.Builder();
        builder.setBaseIngredientsConfig(baseIngredientOnRecipe);
        builder.setExtraIngredientConfig(extraIngredientOnRecipe);
        return builder.build();
    }

    private Order.Builder removeCoffeeFromCustomerList(Order.Builder order) {

        if (order.getCustomCoffeeList().isEmpty() && order.getCustomizableStandardCoffee().isEmpty()) {
            this.consoleView.printEmptyList();
            return order;
        }
        this.consoleView.printCoffeeListMessage(order);

        chooseWhatKindToRemove(order);

        if (order.getCustomCoffeeList().isEmpty() && order.getCustomizableStandardCoffee().isEmpty()) {
            this.consoleView.printEmptyList();
        }
        this.consoleView.printCoffeeListMessage(order);

        return order;
    }

    private Order.Builder chooseWhatKindToRemove(Order.Builder order) {

        while (true) {
            this.consoleView.chooseWhatKindToRemove();

            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> findAndRemoveCustomCoffee(order);
                case "2" -> findAndRemoveStandardCoffee(order);
                case "X" -> {
                    return order;
                }
                default -> consoleView.printInvalidOptionMessage();
            }

            if (order.getCustomCoffeeList().isEmpty() && order.getCustomizableStandardCoffee().isEmpty()) {
                this.consoleView.printEmptyList();
            }
            this.consoleView.printCoffeeListMessage(order);
        }
    }

    private void findAndRemoveStandardCoffee(Order.Builder order) {

        consoleView.printAskForIdMessage();
        int coffeeIndex = input.readInt();
        try {
            CustomizableStandardCoffee customizableStandardCoffee = order.getCustomizableStandardCoffee().get(coffeeIndex);
            order.getCustomizableStandardCoffee().remove(customizableStandardCoffee);
        } catch (IndexOutOfBoundsException e) {
            this.consoleView.printInvalidIdMessage();
        }
    }

    private void findAndRemoveCustomCoffee(Order.Builder order) {

        consoleView.printAskForIdMessage();
        int coffeeIndex = input.readInt();
        try {
            CustomCoffee customCoffee = order.getCustomCoffeeList().get(coffeeIndex);
            order.getCustomCoffeeList().remove(customCoffee);
        } catch (IndexOutOfBoundsException e) {
            this.consoleView.printInvalidIdMessage();
        }
    }

    private Order.Builder placeOrder(Order.Builder order) {

        if (order.getCustomCoffeeList().isEmpty() && order.getCustomizableStandardCoffee().isEmpty()) {
            consoleView.printOrderEmptyMessage();
            return order;
        }

        WhereToDrink whereToDrink = setWhereToDrink();

        order.setOrderDateTime(LocalDateTime.now());
        order.setWhereToDrink(whereToDrink);
        Order placedOrder = this.orderService.placeOrder(order.build());

        if (placedOrder == null) {
            this.consoleView.unknownError();
            return order;
        }
        this.consoleView.printCheckMessage(placedOrder, this.orderService.getTotalOrderPrice(placedOrder), this.orderService.getTotalProfitForToday());
        this.consoleView.printEnjoyCoffeeMessage();

        this.customerName = null;

        return new Order.Builder();
    }

    private void printAllOrders() {

        List<Order> orderList = this.orderService.findAll();
        if (orderList.isEmpty()) {
            this.consoleView.printEmptyList();
        }
        orderList.forEach(this.consoleView::printAllOrders);
    }

    private void updateOrder() {

        this.consoleView.printAskForIdMessage();
        Integer orderId = this.input.readInt();

        Order existingOrder = this.orderService.findOrder(orderId);
        if (existingOrder == null) {
            this.consoleView.printInvalidIdMessage();
            return;
        }

        existingOrder = this.orderService.update(updateMenu(existingOrder));

        this.consoleView.printCheckMessage(existingOrder, this.orderService.getTotalOrderPrice(existingOrder), this.orderService.getTotalProfitForToday());
    }

    private void cancelOrder() {

        this.consoleView.printAskForIdMessage();
        Integer orderId = this.input.readInt();

        this.orderService.deleteOrder(orderId);
        this.consoleView.printOrderCanceledMessage();
    }

    private void printCurrentOrder(Order.Builder order) {

        if (order.getCustomCoffeeList().isEmpty() && order.getCustomizableStandardCoffee().isEmpty()) {
            this.consoleView.printEmptyList();
        } else {
            this.consoleView.printCoffeeListMessage(order);
        }
    }

    private Order updateMenu(Order existingOrder) {

        Order.Builder updatedOrder = new Order.Builder();
        updatedOrder.setCustomCoffeeList(existingOrder.getCustomCoffeeList());
        updatedOrder.setCustomizableStandardCoffee(existingOrder.getCustomizableStandardCoffee());

        while (true) {
            consoleView.printUpdateOrderMessage();

            String option = input.readline();
            switch (option.toUpperCase()) {
                case "1" -> selectCoffee(updatedOrder);
                case "2" -> removeCoffeeFromCustomerList(updatedOrder);
                case "3" -> {
                    updatedOrder.setWhereToDrink(setWhereToDrink());
                    return updatedOrder.build();
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private Integer chooseIngredientQuantity() {

        this.consoleView.askForQuantity();
        Integer quantity = this.input.readInt();

        return quantity;
    }

    private Recipe chooseCoffeeShots() {

        consoleView.printCoffeeShotsOptionListMessage();
        while (true) {
            int shotsNumber;
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> {
                    shotsNumber = chooseShotsNumber();
                    return new Recipe.Builder(
                            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.ESPRESSO_SHOT, shotsNumber))).build();
                }
                case "2" -> {
                    shotsNumber = chooseShotsNumber();
                    return new Recipe.Builder(
                            Arrays.asList(new BaseIngredientOnRecipe(BaseIngredient.BLACK_COFFEE, shotsNumber))).build();
                }
                case "X" -> {
                    return new Recipe();
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    private int chooseShotsNumber() {

        this.consoleView.printAskShotsNumber();
        int shotsNumber = this.input.readInt();

        return shotsNumber;
    }

    private Recipe chooseExtraIngredients() {

        consoleView.printIngredientsOptionListMessage();
        Map<ExtraIngredient, Integer> extraIngredientDefinitionMap = new HashMap<>();

        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> extraIngredientDefinitionMap.put(ExtraIngredient.MILK, chooseIngredientQuantity());
                case "2" -> extraIngredientDefinitionMap.put(ExtraIngredient.HONEY, chooseIngredientQuantity());
                case "3" -> extraIngredientDefinitionMap.put(ExtraIngredient.SYRUP, chooseIngredientQuantity());
                case "4" -> extraIngredientDefinitionMap.put(ExtraIngredient.STEAMED_MILK, chooseIngredientQuantity());
                case "5" -> extraIngredientDefinitionMap.put(ExtraIngredient.MILK_FOAM, chooseIngredientQuantity());
                case "6" -> extraIngredientDefinitionMap.put(ExtraIngredient.SWEETENED_CONDENSED_MILK, chooseIngredientQuantity());
                case "7" -> extraIngredientDefinitionMap.put(ExtraIngredient.ICE_CREAM, chooseIngredientQuantity());
                case "8" -> extraIngredientDefinitionMap.put(ExtraIngredient.WHIPPED_CREAM, chooseIngredientQuantity());
                case "9" -> extraIngredientDefinitionMap.put(ExtraIngredient.CINNAMON, chooseIngredientQuantity());
                case "10" -> extraIngredientDefinitionMap.put(ExtraIngredient.HOT_WATER, chooseIngredientQuantity());
                case "11" -> extraIngredientDefinitionMap.put(ExtraIngredient.ICE_CUBES, chooseIngredientQuantity());
                case "X" -> {
                    Recipe recipe = new Recipe();
                    extraIngredientDefinitionMap.entrySet().forEach(i ->
                            recipe.addExtraIngredient(new ExtraIngredientOnRecipe(i.getKey(), i.getValue())));
                    return recipe;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
            consoleView.printChosenIngredientsForCurrentCoffee(extraIngredientDefinitionMap);
            consoleView.printIngredientsOptionListMessage();
        }
    }

    private void getCustomerName() {

        consoleView.printAskNameMessage();
        String ourCustomerName = input.readline();

        this.customerName = ourCustomerName;
    }

    private WhereToDrink setWhereToDrink() {

        consoleView.printAskWhereToDrinkMessage();
        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1" -> {
                    return WhereToDrink.PICK_UP;
                }
                case "2" -> {
                    return WhereToDrink.TO_GO;
                }
                default -> consoleView.printInvalidOptionMessage();
            }
        }
    }

    public interface View {

        void printMainMenu();

        void chooseWhatKindToRemove();

        void printCoffeeOptionListMessage(Double[] coffeeTypePrices);

        void printCoffeeShotsOptionListMessage();

        void printAskShotsNumber();

        void askForQuantity();

        void printIngredientsRepository(List<BaseIngredientInStock> baseIngredientsInStocks,
                                        List<ExtraIngredientInStock> extraIngredientInStocks);

        void printIngredientsOptionListMessage();

        void printChosenIngredientsForCurrentCoffee(Map<ExtraIngredient, Integer> extraIngredientDefinitionMap);

        void printAskNameMessage();

        void printAskWhereToDrinkMessage();

        void printGoodByeMessage();

        void printOrderEmptyMessage();

        void printEmptyList();

        void printInvalidOptionMessage();

        void printInvalidIdMessage();

        void printUpdateOrderMessage();

        void printAskForIdMessage();

        void printOrderCanceledMessage();

        void printEnjoyCoffeeMessage();

        void printCheckMessage(Order placedOrder, Double priceOrder, Double profitToday);

        void printCoffeeListMessage(Order.Builder order);

        void printAllOrders(Order order);

        void unknownError();
    }
}