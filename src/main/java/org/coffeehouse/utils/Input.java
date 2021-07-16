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

    public int readInt() {

        try {
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("A numeric value is expected.");
            scanner.nextInt();
            return readInt();
        }
    }
}
