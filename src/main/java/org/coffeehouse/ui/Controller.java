package org.coffeehouse.ui;


import org.coffeehouse.model.Coffee;
import org.coffeehouse.model.CoffeeType;
import org.coffeehouse.model.Ingredient;
import org.coffeehouse.model.Order;
import org.coffeehouse.repository.IRepository;
import org.coffeehouse.repository.InMemoryIRepository;
import org.coffeehouse.service.IOrder;
import org.coffeehouse.service.OrderService;
import org.coffeehouse.utils.Input;

public class Controller {

    private IOrder orderService;
    private IRepository<Long, Order> repository;
    private Order customerOrder = new Order();
    private String customerName;
    private IView consoleView;
    private final Input input;
    private Order placedOrder;

    public Controller() {
        this.repository = new InMemoryIRepository<>();
        this.orderService = new OrderService(repository);
        this.consoleView = new ConsoleView();
        this.input = new Input();
    }

    //ToDo: Erase any coffee from the orderList while building it
    public void runApp() {  //ToDo: Rethink this method

        while (true) {
            Coffee coffeeToBuild = new Coffee();
            if (this.customerName == null) {
                getCustomerName();
            }

            consoleView.printCoffeeOptionList();
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1":
                    this.customerOrder.addCoffeeToOrder(buildEspresso(coffeeToBuild));
                    this.consoleView.printOrderListToBuild(this.customerOrder, this.customerName);
                    break;
                case "2":
                    this.customerOrder.addCoffeeToOrder(buildMachiatto(coffeeToBuild));
                    this.consoleView.printOrderListToBuild(this.customerOrder, this.customerName);
                    break;
                case "3":
                    this.customerOrder.addCoffeeToOrder(buildCaffeeLate(coffeeToBuild));
                    this.consoleView.printOrderListToBuild(this.customerOrder, this.customerName);
                    break;
                case "4":
                    this.customerOrder.addCoffeeToOrder(buildCappuccino(coffeeToBuild));
                    this.consoleView.printOrderListToBuild(this.customerOrder, this.customerName);
                    break;
                case "5":
                    this.customerOrder.addCoffeeToOrder(buildCaffeeMiel(coffeeToBuild));
                    this.consoleView.printOrderListToBuild(this.customerOrder, this.customerName);
                    break;
                case "6":
                    if (!removeCoffeeFromCustomerOrder()) {
                        this.consoleView.printInvalidId();
                    }
                    this.consoleView.printOrderListToBuild(this.customerOrder, this.customerName);
                    break;
                case "7":
                    if (this.customerOrder.getOrderedCoffeeList().isEmpty()) {
                        consoleView.printErrorOrderMessage();
                        break;
                    }
                    setWhereToDrink(this.customerOrder);
                    if (this.customerOrder.getWhereToDrink() == null) {
                        break;
                    }
                    this.placedOrder = orderService.placeOrder(this.customerOrder);
                    if (this.placedOrder == null) {
                        this.consoleView.printUnknownError();
                    }
                    break;
                case "X":
                    consoleView.printGoodByeMessage();
                    return;
                default:
                    consoleView.printInvalidOptionMessage();
            }

            if (placedOrder != null) {
                consoleView.printCheck(placedOrder, orderService.getTotalProfitForToday(), this.customerName);
                givePossibilityToCancelOrder(placedOrder);

                prepareForNewOrder();
            }
        }
    }

    private Coffee buildEspresso(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.ESPRESSO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private Coffee buildMachiatto(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.MACHIATTO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private Coffee buildCaffeeLate(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAFFEE_LATTE);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private Coffee buildCappuccino(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAPPUCCINO);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private Coffee buildCaffeeMiel(Coffee coffeeToBuild) {

        coffeeToBuild.setCoffeeType(CoffeeType.CAFFEE_MIEL);
        setExtraIngredients(coffeeToBuild);
        coffeeToBuild.setCustomerName(this.customerName);

        return coffeeToBuild;
    }

    private void setExtraIngredients(Coffee coffeeToBuild) {

        consoleView.printIngredientsOptionList();
        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
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
                case "X":
                    return;
                default:
                    consoleView.printInvalidOptionMessage();
            }
            consoleView.printIngredientsOptionList();
        }
    }

    private void getCustomerName() {

        consoleView.printAskName();
        String ourCustomerName = input.readline();

        this.customerName = ourCustomerName;
    }

    private boolean removeCoffeeFromCustomerOrder() {

        consoleView.printAskForCoffeeId();
        Long coffeeId = input.readLong();
        try {
            Coffee coffee = this.customerOrder.getOrderedCoffeeList().get(coffeeId.intValue());
            return this.customerOrder.getOrderedCoffeeList().remove(coffee);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private void setWhereToDrink(Order currentOrder) {

        consoleView.printAskWhereToDrink();
        while (true) {
            String option = input.readline();

            switch (option.toUpperCase()) {
                case "1":
                    currentOrder.setWhereToDrink(Order.WhereToDrink.PICK_UP);
                    return;
                case "2":
                    currentOrder.setWhereToDrink(Order.WhereToDrink.TO_GO);
                    return;
                case "X":
                    return;
                default:
                    consoleView.printInvalidOptionMessage();
            }
        }
    }

    private void givePossibilityToCancelOrder(Order placedOrder) {

        if (cancelOrder(placedOrder) == null) {
            this.consoleView.printEnjoyCoffeeMessage();
        } else {
            this.consoleView.printOrderCanceledMessage();
        }
    }

    private Order cancelOrder(Order placedOrder) {

        this.consoleView.printAskToCancelMessage();
        while (true) {
            String option = input.readline();
            switch (option.toUpperCase()) {
                case "X":
                    this.orderService.cancelOrder(placedOrder.getId());
                    return placedOrder;
                case "C":
                    return null;
                default:
                    consoleView.printInvalidOptionMessage();
            }
        }
    }

    private void prepareForNewOrder() {

        this.customerOrder = new Order();
        this.customerName = null;
        this.placedOrder = null;
    }

    public interface IView {

        void printCoffeeOptionList();

        void printIngredientsOptionList();

        void printAskName();

        void printAskWhereToDrink();

        void printGoodByeMessage();

        void printErrorOrderMessage();

        void printInvalidOptionMessage();

        void printInvalidId();

        void printUnknownError();

        void printAskToCancelMessage();

        void printAskForCoffeeId();

        void printOrderCanceledMessage();

        void printEnjoyCoffeeMessage();

        void printCheck(Order placedOrder, Double profit, String customerName);

        void printOrderListToBuild(Order orderToBuild, String customerName);
    }
}
