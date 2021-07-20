package org.fantasticcoffee.shop;

import org.fantasticcoffee.shop.config.AppConfig;
import org.fantasticcoffee.shop.ui.AppController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext appConfig = new AnnotationConfigApplicationContext(AppConfig.class);
        AppController appController = appConfig.getBean("controller", AppController.class);

        appController.runApp();
    }
}
