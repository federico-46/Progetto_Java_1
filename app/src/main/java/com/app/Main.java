package com.app;

import com.app.Controllers.Controller;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n-- WELCOME --\n");
        Controller controller = new Controller();

        controller.start();

    }
}
