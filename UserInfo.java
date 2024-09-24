/**
@author Talani Malwandla Mlangeni
@version Java Development task 3
*/
import java.util.Scanner;

public class UserInfo {
    
    // Fields to store user's information
    private String userName; // Renamed from userId to userName for clarity
    private String pin;      // Stores the user's PIN
    private double balance;  // Stores the user's account balance

    // Default constructor
    public UserInfo() {
        this("", "", 0.0); // Calls the parameterized constructor with default values
    }

    // Constructor with parameters to initialize user info
    public UserInfo(String userName, String pin, double balance) {
        setUserName(userName);  // Calls the setter to set the username
        setPin(pin);            // Calls the setter to set the PIN
        setBalance(balance);    // Calls the setter to set the balance
    }

    // Setters to update the values of the fields
    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Getters to retrieve the values of the fields
    public double getBalance() {
        return balance;
    }

    public String getUserName() { 
        return userName;
    }

    public String getPin() {
        return pin;
    }

    // Method to validate the user based on the PIN
    public boolean validateUser(String pin) {
        int maxAttempts = 3;          // Maximum number of attempts allowed
        int remainingAttempts = maxAttempts - 1; // Remaining attempts, starts at 2
        Scanner input = new Scanner(System.in);  // Scanner to accept user input
        boolean correctPin = false;   // Flag to check if the PIN is correct
        
        // Loop to allow the user to try entering the correct PIN
        for (int i = 0; i < maxAttempts; i++) {
            // If the entered PIN matches the user's actual PIN
            if (pin.equals(this.pin)) {
                System.out.println("Welcome " + getUserName());
                correctPin = true;
                break;  // Exit the loop if the correct PIN is entered
            }

            // If the PIN is incorrect, inform the user of the remaining attempts
            System.out.println("That pin was incorrect. You have " + remainingAttempts + " attempt(s) left.");
            remainingAttempts--;  // Decrease the remaining attempts by 1
            
            // If no attempts are left, notify the user and break the loop
            if (remainingAttempts == 0) {
                System.out.println("\nYOU HAVE REACHED THE MAXIMUM ATTEMPTS!!!\nPLEASE VISIT A BRANCH TO GET A NEW BANK CARD.\n");
                break;
            }

            // Prompt the user to re-enter the PIN
            System.out.print("Please re-enter your pin: ");
            String enteredPin = input.nextLine(); 
            pin = enteredPin;  // Update the PIN for the next comparison
        }

        return correctPin;  // Return whether the PIN was correct or not
    }

    // Method to return a string representation of the user's info
    public String toString() {
        return String.format("Hello, user %s. Your balance is %.2f", getUserName(), getBalance());
    }
}
