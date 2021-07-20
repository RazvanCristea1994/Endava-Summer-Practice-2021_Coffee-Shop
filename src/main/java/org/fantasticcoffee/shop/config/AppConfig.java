package org.fantasticcoffee.shop.config;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.repository.InMemoryRepository;
import org.fantasticcoffee.shop.service.impl.CoffeeService;
import org.fantasticcoffee.shop.service.impl.OrderService;
import org.fantasticcoffee.shop.ui.ConsoleView;
import org.fantasticcoffee.shop.ui.AppController;
import org.fantasticcoffee.shop.utils.Input;
import org.springframework.context.annotation.Bean;

public class AppConfig {

    @Bean(name = "controller")
    public AppController getController() {
        return new AppController();
    }

    @Bean(name = "orderService")
    public OrderService getOrderService() {
        return new OrderService();
    }

    @Bean(name = "inMemoryRepository")
    public InMemoryRepository<Order> getInMemoryRepository() {
        return new InMemoryRepository<>();
    }

    @Bean(name = "coffeeService")
    public CoffeeService getCoffeeService() {
        return new CoffeeService();
    }

    @Bean(name = "consoleView")
    public ConsoleView getConsoleView() {
        return new ConsoleView();
    }

    @Bean(name = "input")
    public Input getInput() {
        return new Input();
    }
}
