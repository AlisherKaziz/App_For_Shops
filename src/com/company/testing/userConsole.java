package com.company.testing;

import java.sql.*;
import java.util.Scanner;

public class userConsole {
    protected final static String url = "jdbc:postgresql://localhost:5432/postgres";
    protected final static String user = "postgres";
    protected final static String password = "astanakz";

    Scanner sc = new Scanner(System.in);

    public Connection connect() {//connection to DB
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return conn;
    }

    public final boolean InsertEmail(String username, String passwordUser, String email){//Insert an email
        Connection con = null;
        ResultSet rs = null;
        String getter = null;
        int id;
        try {
            Statement stmt = null;
            String query = "SELECT id FROM logs WHERE username = '"+username+"'"+"AND passworduser = '"+passwordUser+"'";
            con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                getter = rs.getString(1);
            }
            id = Integer.parseInt(getter);
        } catch (Exception e) {
            return false;
        } finally {
//
            try { // Close connection - clean up the system resources
                con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred!");
            }
        }



        try {
            Statement stmt = null;
            String query = "UPDATE logs SET emailaddress ='"+email+"'"+" WHERE id ="+id;
            con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                getter = rs.getString(1);
            }
        } catch (Exception e) {
            return false;
        } finally {
//
            try { // Close connection - clean up the system resources
                con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred!");
            }
        }
        return true;
    }

    public final void Insert(String username, String passworduser){//create an account
        Connection con = null;
        ResultSet rs = null;
        String getter = null;
        try {//get last id
            Statement stmt = null;
            String query = "SELECT id FROM logs WHERE id = (SELECT MAX(id) FROM logs)";
            con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                getter = rs.getString(1);
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
        int id = Integer.parseInt(getter);
        id++;//create an id for new user
        ////////////////////////////////
        if(username == null){
            System.out.println("username cannot be null");
            return;
        }else if(passworduser == null){
            System.out.println("passworduser cannot be null");
            return;
        }else{
            try {
                con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
                PreparedStatement stmt = con.prepareStatement("INSERT INTO logs(id, username, passworduser) VALUES (?, ?, ?)");
                stmt.setInt(1, id);
                stmt.setString(2, username);
                stmt.setString(3, passworduser);

                stmt.executeUpdate();
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
    }

    public final void Delete(String userName, String passwordUser){//delete an  account
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
            stmt = con.createStatement();
            String query = "DELETE FROM logs WHERE username = '" + userName + "' AND passworduser = '" + passwordUser + "'";
            stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { // Close connection - clean up the system resources
                con.close();
            } catch (Exception e) {
                System.out.println("Exception occurred!");
            }
        }
    }

    public final boolean Request(String userName, String passwordUser){//print data for user
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try{
            con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
            stmt = con.createStatement();
            int counter = 0;
            rs = stmt.executeQuery("SELECT username FROM logs WHERE username = '" + userName + "'");
            while (rs.next()) {
                counter++;
            }
            if(counter == 0){//if account does not exist, exit
                System.out.println("Account does not exist");
                return false;
            }
            try {
                con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM logs WHERE username = '"+ userName + "' AND passworduser = '" + passwordUser+"'");
                while (rs.next()) {
                    System.out.println(rs.getString("username") + " " + rs.getString("passworduser") + " " + rs.getString("emailaddress"));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            } finally {
//
                try { // Close connection - clean up the system resources
                    con.close();
                } catch (Exception e) {
                    System.out.println("Exception occurred!");
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return true;
    }
}
