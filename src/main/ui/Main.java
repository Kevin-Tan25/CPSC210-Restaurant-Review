package ui;

// Based off Teller app UI package MAIN class

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new GraphicalInterface();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
