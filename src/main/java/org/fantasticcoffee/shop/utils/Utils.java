package org.fantasticcoffee.shop.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("input")
public class Utils {

    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public String readline() {
        try {
            return scanner.next();
        } catch (Exception e) {
            System.out.println("A string is expected.");
            scanner.next();
            return readline();
        }
    }

    public Integer readInt() {

        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("A numeric value is expected.");
            scanner.next();
            return readInt();
        }
    }
}