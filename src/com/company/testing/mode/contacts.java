package com.company.testing.mode;

import java.util.Scanner;
import com.company.testing.business.*;

public class contacts {
    public contacts(){}//constructor
    public contacts(String newPhoneNumber){
        this.newPhoneNumber = newPhoneNumber;
    }//constructor with parameters with new address
    public contacts(String newPhoneNumber, String newBrand){
        this.newPhoneNumber = newPhoneNumber;
        this.newBrand = newBrand;
    }//constructor with parameters with new address and brand

    protected String newPhoneNumber;
    protected String newBrand;
    //bellow is an objects
    Shop1 a1 = new Shop1();
    Shop2 a2 = new Shop2();
    Shop3 a3 = new Shop3();
    Scanner sc = new Scanner(System.in);

    public void runner(){//run the optional menu
        System.out.println("What the shop you want?");
        System.out.println("1. " + a1.getBrand());
        System.out.println("2. " + a2.getBrand());
        System.out.println("3. " + a3.getBrand());
        String choice = sc.next();
        switch (choice) {
            case "1" -> System.out.println(a1.getContactNumber());

            case "2" -> System.out.println(a2.getContactNumber());

            case "3" -> System.out.println(a3.getContactNumber());
        }
    }
}