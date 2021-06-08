package com.company.testing.business;

import java.sql.*;
import java.util.LinkedList;

public class Shop3<T> extends Franchise implements BasketBuilder<T>{

    protected final static String url = "jdbc:postgresql://localhost:5432/postgres";
    protected final static String user = "postgres";
    protected final static String password = "astanakz";

    public void Request(){//print whole type of product from this shop via DB table
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(Shop1.url, Shop1.user, Shop1.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT type_of_product FROM shop3");
            while (rs.next()) {
                System.out.print("| "+rs.getString("type_of_product"));
            }

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
    public void makeEmpty(String choice){//make empty all list
        basket.clear();
        System.out.println("Basket has been successfully "+ choice);
    }
    @Override
    public void printBasket(){//print whole element in the basket
        for (int i=0; i<basket.size(); i++){
            System.out.print(basket.get(i)+" ");
        }
        System.out.println(" ");
    }

    public void order_special_detail(String special){
        System.out.println(special+ " detail in processing");
    }//special method, in this case order special detail

    @Override
    public String getContactNumber() {
        return "88005553535";
    }//return a contact info

    @Override
    public String getAddress() {//return a full info without phone number
        return getBrand()+"\n"+"Address: Pushkino av. Dom Kalotushkino"+"\n"+"Web address: lpchu.kz";
    }

    @Override
    public String getBrand() {//return brand name
        return "Freno";
    }

    @Override
    public void Sell() {//return what shop sells
        System.out.println("Car body and main parts");
    }

    @Override
    public void Services() {//return which services you can get
        System.out.println("Pickup and Order");
    }
}
