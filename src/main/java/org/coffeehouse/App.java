package org.coffeehouse;

import org.coffeehouse.repository.InMemoryIRepository;
import org.coffeehouse.repository.IRepository;
import org.coffeehouse.service.OrderService;
import org.coffeehouse.ui.Console;

public class App {
    public static void main(String[] args) {
        Console console = new Console();
        console.runMenu();
    }
}
