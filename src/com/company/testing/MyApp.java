package com.company.testing;
import com.company.testing.mode.*;

import java.util.Scanner;
import java.sql.*;
public class MyApp {

    protected final static String url = "jdbc:postgresql://localhost:5432/postgres";
    protected final static String user = "postgres";
    protected final static String password = "astanakz";

    public Connection connect() {//Connection the DB
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return conn;
    }

    public void Run(){//main menu
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome!");
        while (true) {
            System.out.println("Choice mode:");//print option
            System.out.println("1. Shop address");
            System.out.println("2. Contacts");
            System.out.println("3. Personal account");
            System.out.println("0. Exit");
            String choice = sc.next();//take option
            if (choice.equals("1")) {//print option which address user want
                address tool = new address();
                tool.runner();
            }else if(choice.equals("2")){//print option which contact user want
                contacts tool = new contacts();
                tool.runner();
            }else if(choice.equals("3")) {//user sign in
                System.out.println("Have you an account? YES/NO");
                choice = sc.next();
                if(choice.equals("YES")){//if user has an account
                    String userName, passwordUser, email = null;
                    System.out.print("Enter user name: ");userName = sc.next();
                    System.out.print("Enter password: ");passwordUser = sc.next();
                    if(userName.equals("admin") && passwordUser.equals("admin")){
                        pAccount admin = new pAccount(0, userName, passwordUser);
                        admin.runner();
                    }else{
                        pAccount consumer = new pAccount(userName, passwordUser, email);
                        consumer.runner();
                    }
                }else if(choice.equals("NO")){//if user want register
                    pAccount newAcc = new pAccount();
                    newAcc.runner();
                }else{//if user input invalid option
                    System.out.println("Error 400");
                }
            }else if(choice.equals("0")){//exit the app
                System.exit(0);
            }else{//if user input invalid option
                System.out.println("Error 400");
                System.exit(0);
            }
        }
    }
}