package com.app.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.TreeMap;

import com.app.Models.User;
import com.app.Models.Sale;
import com.app.Models.Product;

public class FileManager {

    private TreeMap<Integer, User> users;
    private TreeMap<Integer, Product> products;
    private TreeMap<Integer, Sale> sales;

    public FileManager() {
        users = new TreeMap<>();
        products = new TreeMap<>();
        sales = new TreeMap<>();
    }

    // import of "utenti.csv", "prodotti.csv", "vendite.csv"

    public void importFiles() {

        try {

            // reading "utenti.csv"
            InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream("utenti.csv");
            if (ioStream == null) {
                throw new IllegalArgumentException("utenti.csv" + " is not found");
            }
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(ioStream));

            String row = csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");

                // creates a new user from data and saves it in a map
                User user = new User(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5]);
                users.put(Integer.parseInt(data[0]), user);
            }

            csvReader.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

        try {

            // reading "prodotti.csv"
            InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream("prodotti.csv");
            if (ioStream == null) {
                throw new IllegalArgumentException("prodotti.csv" + " is not found");
            }
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(ioStream));

            String row = csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");

                // creates a new product from data and saves it in a map
                Product product = new Product(Integer.parseInt(data[0]), data[1], data[2], data[3],
                        data[4],
                        data[5]);
                products.put(Integer.parseInt(data[0]), product);
            }

            csvReader.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {

            // reading "vendite.csv"
            InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream("vendite.csv");
            if (ioStream == null) {
                throw new IllegalArgumentException("vendite.csv" + " is not found");
            }
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(ioStream));

            String row = csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");

                // creates a new sale from data and saves it in a map
                Sale sale = new Sale(Integer.parseInt(data[0]),
                        users.get(Integer.parseInt(data[2])),
                        products.get(Integer.parseInt(data[1])));
                sales.put(Integer.parseInt(data[0]), sale);
            }

            csvReader.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // getters
    public Collection<User> getUsersCollection() {
        return this.users.values();
    }

    public Collection<Product> getProductsCollection() {
        return this.products.values();
    }

    public Collection<Sale> getSalesCollection() {
        return this.sales.values();
    }

    public boolean containsUser(Integer u) {

        if (users.containsKey(u)) {
            return true;
        } else {
            return false;
        }
    }

    public void addUser(User u) {

        users.put(u.getUserID(), u);
    }

    public TreeMap<Integer, User> getUsersMap() {
        return this.users;
    }

    public TreeMap<Integer, Product> getProductsMap() {
        return this.products;
    }

    public TreeMap<Integer, Sale> getSalesMap() {
        return this.sales;
    }

    public void addSale(Sale r) {

        sales.put(sales.size() + 1, r);
    }

    public void removeSale(int id) {
        sales.remove(id);
    }

}
