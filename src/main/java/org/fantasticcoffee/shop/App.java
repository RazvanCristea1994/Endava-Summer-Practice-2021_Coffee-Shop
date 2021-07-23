package org.fantasticcoffee.shop;

import org.fantasticcoffee.shop.ui.AppController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext appConfig = new AnnotationConfigApplicationContext("org.fantasticcoffee.shop");
        AppController appController = appConfig.getBean(AppController.class);

        appController.runApp();
    }
}