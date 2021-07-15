package org.coffeehouse.utils;

import java.util.Scanner;

public class Input {

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

    public Long readLong() {

        try {
            return scanner.nextLong();
        } catch (Exception e) {
            System.out.println("A numeric value is expected.");
            scanner.next();
            return readLong();
        }
    }
}
