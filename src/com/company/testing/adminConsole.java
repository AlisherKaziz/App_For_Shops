package com.company.testing;

import java.sql.*;
import java.util.Scanner;

public class adminConsole {
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

    public final void Insert(int id, String username, String passworduser, String emailaddress){//insert new account to DB
        Connection con = null;
        ResultSet rs = null;
        if(username == null){
            System.out.println("username cannot be null");
            return;
        }else if(passworduser == null){
            System.out.println("passworduser cannot be null");
            return;
        }else{
            try {
                con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
                PreparedStatement stmt = con.prepareStatement("INSERT INTO logs(id, username, passworduser, emailaddress) VALUES (?, ?, ?, ?)");

                stmt.setInt(1, id);
                stmt.setString(2, username);
                stmt.setString(3, passworduser);
                stmt.setString(4, emailaddress);

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

    public final void Delete(int id){//delete an account from DB
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
            stmt = con.createStatement();
            String query = "DELETE FROM logs WHERE id =" + id;
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

    public final void Request(){//print whole table with logs
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM logs");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "  " + rs.getString("username") + " " + rs.getString("passworduser") + " " + rs.getString("emailaddress"));
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

    public final void Update(int id, String newPassword){//update a password of given user
        Connection con = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(adminConsole.url, adminConsole.user, adminConsole.password);
            PreparedStatement stmt = con.prepareStatement("UPDATE logs " +
                    "  SET passworduser = ? " +
                    "WHERE id = ?");
            stmt.setString(1, newPassword);
            stmt.setInt(2, id);
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