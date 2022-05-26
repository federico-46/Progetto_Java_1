package com.app.Controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.app.Models.Sale;
import com.app.Models.User;

public class Controller {

    private static FileManager fileData;

    public Controller() {

        fileData = new FileManager();
        fileData.importFiles();
    }

    public void start() {

        // user input choice
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;

        do {

            System.out.println("Enter 1: Show all products");
            System.out.println("Enter 2: Book a product");
            System.out.println("Enter 3: Delete purchased products");
            System.out.println("Enter 4: Add a new user");
            System.out.println("Enter 5: Export a file with available products");
            System.out.println("Enter 0: EXIT\n");

            userInput = Integer.parseInt(scanner.nextLine());

            // case "not a valid number"
            if ((userInput) < 0 || (userInput) > 5) {

                System.out.println("Please enter a valid number!\n");
                continue;
            }

            // displaying all functions
            switch (userInput) {
                case 1:

                    printAllProducts();
                    break;

                case 2:

                    bookAProduct(scanner);
                    break;

                case 3:

                    deleteSale(scanner);
                    ;
                    break;

                case 4:

                    addUser(scanner);
                    break;

                case 5:

                    writeCSVFile();
                    break;

                default:

                    break;
            }
        } while (userInput != 0);

        scanner.close();
    }

    private static void printAllProducts() {

        System.out.println("\n\nID | Name | Date of Input | Price | Brand | Available\n");

        fileData.getProductsCollection().stream().forEach(t -> System.out.println(t.toString()));
    }

    private void bookAProduct(Scanner scanner) {

        System.out.println("\nEnter product ID:");
        int productId = Integer.parseInt(scanner.nextLine());
        System.out.println("\nEnter your user ID:");
        int userId = Integer.parseInt(scanner.nextLine());

        if ((fileData.getProductsMap().containsKey(productId))
                && (fileData.getProductsMap().get(productId).getAvailable())
                && (fileData.containsUser(userId))) {

            Sale sale = new Sale(fileData.getSalesCollection().size() + 1,
                    fileData.getUsersMap().get(userId), fileData.getProductsMap().get(productId));
            fileData.addSale(sale);
            fileData.getProductsMap().get(productId).setAvailable(false);

            System.out.println("\nNEW SALE ADDED: your sale ID is "
                    + fileData.getSalesCollection().size() + "\n");
        } else {

            System.out.println("\nCANNOT BOOK: CHECK USER ID/ PRODUCT ID/ AVAILABILITY\n");
        }
    }

    private void deleteSale(Scanner scanner) {

        System.out.println("\nEnter the sale ID you want to remove:");
        int saleID = Integer.parseInt(scanner.nextLine());

        if (fileData.getSalesMap().containsKey(saleID)) {

            fileData.removeSale(saleID);
            System.out.println("\nSALE(ID:" + saleID + ") REMOVED\n");
        } else {

            System.out.println("\nID NOT FOUND\n");
        }
    }

    private static void addUser(Scanner scanner) {

        String[] newUserData = new String[6];
        boolean condition = false;

        // collecting user data
        do {
            System.out.println("\nEnter new user ID:");
            newUserData[0] = scanner.nextLine();

            // checking if the user is already in the system
            if (fileData.containsUser(Integer.parseInt(newUserData[0]))) {

                System.out.println("This user ID already exists.\n");
                condition = false;
            } else {
                condition = true;
            }

        } while (condition == false);

        System.out.println("\nEnter your first name:");
        newUserData[1] = scanner.nextLine();
        System.out.println("\nEnter your last name:");
        newUserData[2] = scanner.nextLine();
        System.out.println("\nEnter your birth date:");
        newUserData[3] = scanner.nextLine();
        System.out.println("\nEnter your address:");
        newUserData[4] = scanner.nextLine();
        System.out.println("\nEnter your document ID:");
        newUserData[5] = scanner.nextLine();

        fileData.addUser(new User(Integer.parseInt(newUserData[0]), newUserData[1], newUserData[2], newUserData[3],
                newUserData[4], newUserData[5]));
        System.out.println("\nNEW USER ADDED: " + fileData.getUsersMap().get(Integer.parseInt(newUserData[0])) + "\n");

    }

    public void writeCSVFile() {

        List<String> productsFiltered = fileData.getProductsCollection().stream().filter(t -> t.getAvailable())
                .map(t -> t.toStringComplete()).collect(Collectors.toList());

        FileWriter csvWriter;
        try {

            String fileName = new SimpleDateFormat("'products_'dd_MM_yyyy'.csv'").format(new Date());
            csvWriter = new FileWriter(fileName);

            csvWriter.append("ID");
            csvWriter.append(";");
            csvWriter.append("Date");
            csvWriter.append(";");
            csvWriter.append("Name");
            csvWriter.append(";");
            csvWriter.append("Brand");
            csvWriter.append(";");
            csvWriter.append("Price");
            csvWriter.append("\n");

            for (String row : productsFiltered) {

                csvWriter.append(row);
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            System.out.println("\nNew file: " + fileName + " created\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
