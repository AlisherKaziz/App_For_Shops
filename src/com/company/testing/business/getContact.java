package com.company.testing.business;

public interface getContact {//interface cannot be instantiated
    default String getContactNumber(){//default body for getContactNumber
        return "No active Contact Number";
    }
    default String getAddress() {//default body for getAddress
        return "No active Shop";
    }
    default String getBrand() {//default body for getBrand
        return "No declared Brand";
    }
    static String getCEO(){//static body for getCEO
        return "Sundar Pichai";
    }
}
