package services;

import models.bankManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Databasesamit {
    private Connection connection;

    public Databasesamit(Connection connection) {
        this.connection = connection;
    }

    public void createAccount(String name, String type, double balance) throws SQLException {
        String sql = "INSERT INTO bank_accounts (name, type, balance) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setDouble(3, balance);
            stmt.executeUpdate();
            System.out.println("Account created successfully!");
        }
    }

    
    public void deposit(int accNo, double amount) throws SQLException {
        String sql = "UPDATE bank_accounts SET balance = balance + ? WHERE accNo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accNo);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Deposited successfully!");
            else System.out.println("Account not found!");
        }
    }

    public void withdraw(int accNo, double amount) throws SQLException {
        String sql = "UPDATE bank_accounts SET balance = balance - ? WHERE accNo = ? AND balance >= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accNo);
            stmt.setDouble(3, amount);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Withdrawn successfully!");
            else System.out.println("Insufficient balance or account not found!");
        }
    }

    public bankManager getAccount(int accNo) throws SQLException {
        String sql = "SELECT * FROM bank_accounts WHERE accNo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new bankManager(
                        rs.getInt("accNo"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDouble("balance")
                );
            }
        }
        return null;
    }


    public List<bankManager> getAllAccounts() throws SQLException {
        List<bankManager> list = new ArrayList<>();
        String sql = "SELECT * FROM bank_accounts";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new bankManager(
                        rs.getInt("accNo"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDouble("balance")
                ));
            }
        }
        return list;
    }
}










/*
 *  public void createAccount(String name, String type, double balance) throws SQLException {
        String sql = "INSERT INTO accounts (name, accType, balance) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setDouble(3, balance);
            stmt.executeUpdate();
            System.out.println("Account created successfully!");
        }
    }
 */