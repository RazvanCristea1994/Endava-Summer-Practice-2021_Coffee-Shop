package org.coffeehouse.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Input {

    private Scanner scanner = new Scanner(System.in);

    public String readline() {
        try{
            return scanner.next();
        } catch (Exception e) {
            System.out.println("A string is expected.");
            return readline();
        }
    }
}
