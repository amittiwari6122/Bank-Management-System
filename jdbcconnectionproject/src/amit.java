import services.Databasesamit;
import java.sql.Connection;
import models.bankManager;
import java.util.Scanner;
import java.sql.*;
import java.util.*;

public class amit {
    public static void main(String[] args) throws Exception {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/amit", "root", "amit2005");
             Scanner scanner = new Scanner(System.in)) {

            Databasesamit db = new Databasesamit(connection);
            int choice;

            do {
                System.out.println("\n BANK MANAGEMENT SYSTEM ");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Show Account");
                System.out.println("5. Show All Accounts");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        scanner.nextLine();
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter type (Savings/Current): ");
                        String type = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double bal = scanner.nextDouble();
                        db.createAccount(name, type, bal);
                        break;

                    case 2:
                        System.out.print("Enter accNo: ");
                        int dAcc = scanner.nextInt();
                        System.out.print("Enter deposit amount: ");
                        double damt = scanner.nextDouble();
                        db.deposit(dAcc, damt);
                        break;

                    case 3:
                        System.out.print("Enter accNo: ");
                        int wAcc = scanner.nextInt();
                        System.out.print("Enter withdraw amount: ");
                        double wamt = scanner.nextDouble();
                        db.withdraw(wAcc, wamt);
                        break;

                    case 4:
                        System.out.print("Enter accNo: ");
                        int sAcc = scanner.nextInt();
                        bankManager acc = db.getAccount(sAcc);
                        if (acc != null) acc.displayAccount();
                        else System.out.println("Account not found!");
                        break;

                    case 5:
                        for (bankManager a : db.getAllAccounts()) {
                            a.displayAccount();
                            System.out.println("------------------");
                        }
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 6);
        }
    }
}
