package com.company.testing.business;

import java.sql.*;
import java.util.*;

public class Shop2<T> extends Franchise implements BasketBuilder<T>{

    protected final static String url = "jdbc:postgresql://localhost:5432/postgres";
    protected final static String user = "postgres";
    protected final static String password = "astanakz";

    Scanner sc = new Scanner(System.in);

    public void Request(){//print whole type of product from this shop via DB tablev
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(Shop1.url, Shop1.user, Shop1.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT type_of_product, gender FROM shop2");
            while (rs.next()) {
                System.out.print("| "+rs.getString("type_of_product") + "_" +rs.getString("gender"));
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//
            try { // Close connection - clean up the system resources
                con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred!");
            }
        }
    }

    private LinkedList<T> basket = new LinkedList<>();

    @Override
    public void push(T element){//push element into basket
        basket.addFirst(element);
        System.out.println("Element has been successfully added");
    }
    @Override
    public void remove(T element){//remove element into basket
        basket.remove(element);
        System.out.println("Element has been successfully removed");
    }
    @Override
    public void printBasket(){//print whole element in the basket
        for (int i=0; i<basket.size(); i++){
            System.out.print(basket.get(i)+" ");
        }
        System.out.println(" ");
    }
    @Override
    public void makeEmpty(String choice){//make empty all list
        basket.clear();
        System.out.println("Basket has been successfully "+ choice);
    }

    public void size_of_clother(){//special method, in this case print size from given data
        String choice;
        System.out.println("Choose your option");
        System.out.println("1. Top");
        System.out.println("2. Bottom");
        System.out.println("3. Shoes");
        choice = sc.next();
        System.out.print("Enter your gender (Male/Female): ");
        String gender = sc.next();
        if(gender.equals("Male") && choice.equals("1")){
            System.out.print("Enter your height: ");
            int lenght = sc.nextInt();
            if(lenght < 176){
                System.out.println("Your size is S");
            }else if(lenght<182){
                System.out.println("Your size is M");
            }else if(lenght<188){
                System.out.println("Your size is L");
            }else if(lenght<100){
                System.out.println("Invalid data");
                return;
            }
            else{
                System.out.println("Your size is XL");
            }
        }else if(gender.equals("Male") && choice.equals("2")){
            System.out.print("Enter your waist: ");
            int lenght = sc.nextInt();
            if(lenght<71){
                System.out.println("Your size is S");
            }else if(lenght<76){
                System.out.println("Your size is M");
            }else if(lenght<80){
                System.out.println("Your size is L");
            }else if(lenght<49){
                System.out.println("Invalid data");
                return;
            }else{
                System.out.println("Your size is XL");
            }
        }else if(gender.equals("Male") && choice.equals("3")){
            System.out.print("Enter your foot size: ");
            double lenght = sc.nextInt();
            if(lenght<24.0){
                System.out.println("Your size is S");
            }else if(lenght<25.5){
                System.out.println("Your size is M");
            }else if(lenght<10){
                System.out.println("Invalid data");
                return;
            }else if(lenght<27.5){
                System.out.println("Your size is L");
            }else{
                System.out.println("Your size is XL");
            }
        }else if(gender.equals("Female") && choice.equals("1")){
            System.out.print("Enter your height: ");
            int lenght = sc.nextInt();
            if(lenght < 165){
                System.out.println("Your size is S");
            }else if(lenght<171){
                System.out.println("Your size is M");
            }else if(lenght<173){
                System.out.println("Your size is L");
            }else if(lenght<100){
                System.out.println("Invalid data");
                return;
            }
            else{
                System.out.println("Your size is XL");
            }
        }else if(gender.equals("Female") && choice.equals("2")){
            System.out.print("Enter your waist: ");
            int lenght = sc.nextInt();
            if(lenght<65){
                System.out.println("Your size is S");
            }else if(lenght<70){
                System.out.println("Your size is M");
            }else if(lenght<78){
                System.out.println("Your size is L");
            }else if(lenght<49){
                System.out.println("Invalid data");
                return;
            }else{
                System.out.println("Your size is XL");
            }
        }else if(gender.equals("Female") && choice.equals("3")){
            System.out.print("Enter your foot size: ");
            double lenght = sc.nextInt();
            if(lenght<24.0){
                System.out.println("Your size is S");
            }else if(lenght<25.5){
                System.out.println("Your size is M");
            }else if(lenght<10){
                System.out.println("Invalid data");
                return;
            }else if(lenght<27.5){
                System.out.println("Your size is L");
            }else{
                System.out.println("Your size is XL");
            }
        }
    }

    @Override
    public String getContactNumber() {
        return "+01972379134";
    }//return a contact info

    @Override
    public String getAddress() {//return a full info without phone number
        return getBrand()+"\n"+"Address: intersection of Clinton and Fidelity"+"\n"+"Web address: tripoloski.com";
    }

    @Override
    public String getBrand() {//return brand name
        return "Beldyazhki";
    }

    @Override
    public void Sell() {//return what shop sells
        System.out.println("Clother and Shoes, Sneakers");
    }

    @Override
    public void Services() {//return which services you can get
        System.out.println("Pickup and Order");
    }
}
