package com.company.testing.mode;

import java.sql.*;
import java.util.Scanner;
import com.company.testing.adminConsole;
import com.company.testing.userConsole;
import com.company.testing.business.*;

public class pAccount {
    protected final static String url = "jdbc:postgresql://localhost:5432/postgres";
    protected final static String user = "postgres";
    protected final static String password = "astanakz";

    protected int id;
    protected String userName;
    protected String passwordUser;
    protected String email;
    protected int switcher;

    public pAccount() {
        switcher = 1;//create account
    }

    public pAccount(String userName, String password, String email) {
        Connection con;
        ResultSet rs;
        Statement stmt;
        switcher = 2;//consumer account
        this.userName = userName;
        this.passwordUser = password;
        this.email = email;
    }

    public pAccount(int id, String userName, String password) {
        switcher = 3;//admin
        this.id = 0;
        this.userName = userName;
        this.passwordUser = password;
    }

    //constructors with String in generic type
    Shop1<String> a1 = new Shop1<>();
    Shop2<String> a2 = new Shop2<>();
    Shop3<String> a3 = new Shop3<>();
    Scanner sc = new Scanner(System.in);
    adminConsole command = new adminConsole();
    userConsole command0 = new userConsole();

    public Connection connect() {//connection with DB
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return conn;
    }

    public void runner() {//run the optional menu
        if (switcher == 1) {
            System.out.println("Please, write your username and password bellow:");//create an account
            String userName = sc.next();
            String passwordUser = sc.next();
            command0.Insert(userName, passwordUser);
        } else if (switcher == 2) {//optional menu for primitive user
            while (true) {
                System.out.println("""
                        What are you want to do?
                        1. Print your data
                        2. Delete an account""" + "\n" + "3. Update an email address" + "\n4. Choice shop" + "\n5. Exit");//optional menu
                String turn = sc.next();
                boolean contact_breaker;
                if (turn.equals("1")) {//if account is exist, print whole data, else break;
                    contact_breaker = command0.Request(userName, passwordUser);
                    if (!contact_breaker) {
                        return;
                    }
                } else if (turn.equals("2")) {//delete an account
                    command0.Delete(userName, passwordUser);
                    return;
                } else if (turn.equals("3")) {//update the email
                    System.out.print("Please, write your email: ");
                    String email = sc.next();
                    contact_breaker = command0.InsertEmail(userName, passwordUser, email);
                    if (!contact_breaker) {
                        return;
                    }
                } else if (turn.equals("4")) {//optional menu for actions with shops
                    System.out.println("What the shop you want?");
                    System.out.println("1. " + a1.getBrand());
                    System.out.println("2. " + a2.getBrand());
                    System.out.println("3. " + a3.getBrand());
                    String choice = sc.next();
                    String choice2;
                    switch (choice) {
                        case "1" -> {
                            while (true) {//optional menu for shop1
                                String element;
                                System.out.println("What you want?");
                                System.out.println("1. Print all possible products");
                                System.out.println("2. Add new product in basket");
                                System.out.println("3. Get the basket");
                                System.out.println("4. Remove element from basket");
                                System.out.println("5. Order your basket");
                                System.out.println("6. Order on another address");
                                System.out.println("7. Get all possible type of products and services");
                                System.out.println("8. Exit");
                                choice2 = sc.next();
                                switch (choice2) {
                                    case "1" -> a1.Request();
                                    case "2" -> {
                                        System.out.print("Please, enter name of this element: ");
                                        element = sc.next();
                                        a1.push(element);

                                    }
                                    case "3" -> a1.printBasket();
                                    case "4" -> {
                                        System.out.print("Please, enter name of this element: ");
                                        element = sc.next();
                                        a1.remove(element);
                                    }
                                    case "5" -> {
                                        a1.makeEmpty("ordered");
                                        System.out.println("Your basket on the way");
                                    }
                                    case "6" -> {
                                        System.out.println("Write address below");
                                        String newAddress = sc.next();
                                        a1.order_on_another_address(newAddress);
                                        a1.makeEmpty("ordered");
                                        System.out.println("Your basket on the way");
                                    }
                                    case "7" -> {
                                        a1.Sell();
                                        a1.Services();
                                    }
                                    case "8" -> {
                                        a1.makeEmpty("cleared");
                                        return;
                                    }

                                }
                            }
                        }

                        case "2" -> {//optional menu for shop2
                            while (true) {
                                String element;
                                System.out.println("What you want?");
                                System.out.println("1. Print all possible products");
                                System.out.println("2. Add new product in basket");
                                System.out.println("3. Get the basket");
                                System.out.println("4. Remove element from basket");
                                System.out.println("5. Order your basket");
                                System.out.println("6. Get a size");
                                System.out.println("7. Get all possible type of products and services");
                                System.out.println("8. Exit");
                                choice2 = sc.next();
                                switch (choice2) {
                                    case "1" -> a2.Request();
                                    case "2" -> {
                                        System.out.print("Please, enter name of this element: ");
                                        element = sc.next();
                                        a2.push(element);
                                    }
                                    case "3" -> a2.printBasket();
                                    case "4" -> {
                                        System.out.print("Please, enter name of this element: ");
                                        element = sc.next();
                                        a2.remove(element);

                                    }
                                    case "5" -> {
                                        a2.makeEmpty("ordered");
                                        System.out.println("Your basket on the way");
                                    }
                                    case "6" -> {
                                        a2.size_of_clother();
                                    }
                                    case "7" -> {
                                        a2.Sell();
                                        a2.Services();
                                    }
                                    case "8" -> {
                                        a2.makeEmpty("cleared");
                                        return;
                                    }
                                }
                            }
                        }
                        case "3" -> {//optional menu for shop3
                            while (true) {
                                String element;
                                System.out.println("What you want?");
                                System.out.println("1. Print all possible products");
                                System.out.println("2. Add new product in basket");
                                System.out.println("3. Get the basket");
                                System.out.println("4. Remove element from basket");
                                System.out.println("5. Order your basket");
                                System.out.println("6. Order a special detail");
                                System.out.println("7. Get all possible type of products and services");
                                System.out.println("8. Exit");
                                choice2 = sc.next();
                                switch (choice2) {
                                    case "1" -> a3.Request();
                                    case "2" -> {
                                        System.out.print("Please, enter name of this element: ");
                                        element = sc.next();
                                        a3.push(element);
                                    }
                                    case "3" -> a3.printBasket();
                                    case "4" -> {
                                        System.out.print("Please, enter name of this element: ");
                                        element = sc.next();
                                        a3.remove(element);
                                    }
                                    case "5" -> {
                                        a3.makeEmpty("ordered");
                                        System.out.println("Your basket on the way");
                                    }
                                    case "6" -> {
                                        System.out.println("Write name of special detail below");
                                        String special = sc.next();
                                        a3.order_special_detail(special);

                                    }
                                    case "7" -> {
                                        a3.Sell();
                                        a3.Services();
                                    }
                                    case "8" -> {
                                        a3.makeEmpty("cleared");
                                        return;
                                    }

                                }
                            }
                        }
                    }
                } else if (turn.equals("5")) {//exit from whole optional menu
                    return;
                } else {//Unexpected value
                    System.out.println("Unexpected value: " + turn);
                }
            }
        } else if (switcher == 3) {//optional menu for admin
            while (true) {
                System.out.println("""
                        What are you want to do?
                        1. Delete account from database
                        2. Change password for user
                        3. Create new data
                        4. Print table""");
                String turn = sc.next();

                switch (turn) {
                    case "1" -> {//delete account from DB
                        id = sc.nextInt();
                        command.Delete(id);
                    }
                    case "2" -> {//change password for user
                        id = sc.nextInt();
                        String newPassword = sc.next();
                        command.Update(id, newPassword);
                    }
                    case "3" -> {//create new user
                        id = sc.nextInt();
                        String username = sc.next(),
                                passworduser = sc.next(),
                                emailaddress = sc.next();
                        command.Insert(id, username, passworduser, emailaddress);
                    }
                    case "4" -> {//print whole table
                        System.out.println("###############################################################");
                        command.Request();
                        System.out.println("###############################################################");
                    }
                    default -> {//Unexpected value:
                        System.out.println("Unexpected value: " + turn);

                    }
                }
                System.out.println("Exit or continue? 1/2");
                String choice = sc.next();
                if (choice.equals("1")) {
                    return;
                } else if (choice.equals("2")) {
                    choice = "2";
                } else {
                    System.out.println("Invalid number, this session is over");
                    return;
                }
            }
        }
    }


}