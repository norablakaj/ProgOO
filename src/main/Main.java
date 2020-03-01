package main;

import controller.Controller;

/**
 * Used for the main-method (so the controller can be changed easily).
 */
public class Main {

    public static void main(String[] args) {

        // Starting at the controller
        System.out.println("Main");
        new Controller();
    }
}
