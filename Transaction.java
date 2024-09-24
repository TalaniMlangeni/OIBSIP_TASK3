/**
@author Talani Malwandla Mlangeni
@version Java Development task 3
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transaction {
    // List to store the user's transaction history
    private List<String> transactionHistory = new ArrayList<>();
    
    // User object to keep track of the user's information
    private UserInfo user;

    // Constructor to initialize the transaction with the user's info
    public Transaction(UserInfo user) {
        this.user = user;
    }

    // Helper method to round a value to two decimal places
    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // Method to handle withdrawal transactions
    public void withdraw(double amount) {
        // Check if the amount is valid and if the user has sufficient balance
        if (amount > 0 && amount <= user.getBalance()) {
            double newBalance = user.getBalance() - amount;
            user.setBalance(roundToTwoDecimals(newBalance));
            addTransaction("Withdrawal: " + roundToTwoDecimals(user.getBalance()) + " - " + amount + " = " + roundToTwoDecimals(newBalance));
            System.out.println("Transaction successful. New balance: " + user.getBalance());
        } 
        // If the withdrawal amount exceeds the user's balance
        else if (amount > user.getBalance()) {
            System.out.println("Insufficient balance for this transaction.");
        } 
        // If the amount entered is invalid (e.g. negative value)
        else {
            System.out.println("That was an invalid amount. Try again.");
        }
    }

    // Method to handle money transfer transactions
    public void transfer(double amount, String accNum) {
        // Confirm if the entered account number is correct
        Scanner input = new Scanner(System.in);
        System.out.print("You entered " + accNum + ", are you sure this is the correct account number (y/n): ");
        String confirm = input.nextLine();
        System.out.println("\n");
        
        // If the user confirms the account number is correct
        if (confirm.equalsIgnoreCase("y")) {
            // Check if the transfer amount is valid and if the user has enough balance
            if (amount > 0 && amount <= user.getBalance()) {
                double newBalance = user.getBalance() - amount;
                user.setBalance(roundToTwoDecimals(newBalance));
                addTransaction("Transfer:\t" + amount + " sent to Account: " + accNum);
                System.out.println("Transfer successful.");
            } 
            // If the transfer amount exceeds the user's balance
            else if (amount > user.getBalance()) {
                System.out.println("Insufficient balance for the transfer.");
            } 
            // If the amount entered is invalid
            else {
                System.out.println("That was an invalid amount. Try again.");
            }
        } 
        // If the user enters 'n' (incorrect account number)
        else if (confirm.equalsIgnoreCase("n")) {
            System.out.println("Please enter the account number again.");
        } 
        // If the input is invalid (anything other than 'y' or 'n')
        else {
            System.out.println("Invalid input.");
        }
    }

    // Method to handle deposit transactions
    public void deposit(double amount) {
        // Check if the amount is valid (positive value)
        if (amount > 0) {
            double newBalance = user.getBalance() + amount;
            user.setBalance(roundToTwoDecimals(newBalance));
            addTransaction("Deposit:\t " + roundToTwoDecimals(user.getBalance()) + " - " + amount + " = " + roundToTwoDecimals(newBalance));
            System.out.println("Deposit successful. New balance: " + newBalance);
        } 
        // If the amount entered is invalid
        else {
            System.out.println("That was an invalid amount. Try again.");
        }
    }

    // Method to record a transaction in the history
    private void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    // Method to display the user's transaction history
    public void displayTransactionHistory() {
        // If the user has not made any transactions
        if (transactionHistory.isEmpty()) {
            System.out.println("You have not made any transactions yet.");
        } 
        // If there are transactions, display them
        else {
            System.out.println("\t~Transaction History~\n");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}
