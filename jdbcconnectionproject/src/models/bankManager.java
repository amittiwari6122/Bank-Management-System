package models;

public class bankManager {
     private int accNo;
    private String name;
    private String accType;
    private double balance;

    public bankManager(int accNo, String name, String accType, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.accType = accType;
        this.balance = balance;
    }

    public int getAccNo() { return accNo; }
    public void setAccNo(int accNo) { this.accNo = accNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAccType() { return accType; }
    public void setAccType(String accType) { this.accType = accType; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw
    public void withdraw(double amount) {
        double minBalance = accType.equalsIgnoreCase("Savings") ? 500 : 1000;
        if (amount > 0 && balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or minimum balance requirement not met.");
        }
    }

    // Display account
    public void displayAccount() {
        System.out.println("\nAccount No: " + accNo);
        System.out.println("Name: " + name);
        System.out.println("Type: " + accType);
        System.out.println("Balance: ₹" + balance);
    }
}

