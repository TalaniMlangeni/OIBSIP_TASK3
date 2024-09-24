/**
@author Talani Malwandla Mlangeni
@version Java Development task 3
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ATM_Interface {

    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner input = new Scanner(System.in);
        
        // Create a list to store user information
        List<UserInfo> userList = new ArrayList<>();

        // Adding users with initial information (username, PIN, and balance)
        userList.add(new UserInfo("Steve", "0195", 5000.89));
        userList.add(new UserInfo("Billie", "8765", 500.89));
        userList.add(new UserInfo("Hanna", "1235", 4597.89));
        // You can add more users here as needed

        // Display a welcome message to the user
        System.out.println("\t\t~~Hello, welcome to OASIS Bank~~");
        System.out.print("\nPlease enter your User name: ");
        
        // Take user input for the username
        String userName = input.nextLine();

        // Search for the user in the list of users
        UserInfo foundUser = findUserByName(userList, userName);

        // If the user is found, proceed to PIN validation
        if (foundUser != null) {
            System.out.print("\nPlease enter your PIN: ");
            String pin = input.nextLine();

            // Validate the user's PIN
            if (foundUser.validateUser(pin)) {
                // If PIN is correct, create a Transaction object for the current user
                Transaction transactions = new Transaction(foundUser); 
                boolean resume = true;
                
                // Keep showing the menu until the user chooses to quit
                while (resume) {
                    showMainMenu();
                    int choice = getUserChoice(input);

                    // Switch-case to handle user's menu selection
                    switch (choice) {
                        case 1:
                            // Display transaction history
                            transactions.displayTransactionHistory();
                            break;
                        case 2:
                            // Handle withdrawal
                            System.out.print("\nEnter the amount you'd like to withdraw: ");
                            double withdrawAmount = input.nextDouble();
                            transactions.withdraw(withdrawAmount);
                            break;
                        case 3:
                            // Handle deposit
                            System.out.print("\nEnter the amount you'd like to deposit: ");
                            double depositAmount = input.nextDouble();
                            transactions.deposit(depositAmount);
                            break;
                        case 4:
                            // Handle money transfer
                            input.nextLine(); // Consume the newline character after nextDouble()
                            System.out.print("Enter the account you'd like to transfer money to: ");
                            String accNum = input.nextLine();
                            try {
                                // Validate the account number before transferring money
                                if (validateAccountNumber(accNum)) {
                                    System.out.print("Enter the amount: ");
                                    double transferAmount = input.nextDouble();
                                    transactions.transfer(transferAmount, accNum);
                                }
                            }
                            catch (InvalidAccountNumberException e) {
                                // Handle invalid account number exception
                                System.out.println("Invalid account number.");
                            }
                            break;
                        case 5:
                            // Exit the program
                            System.out.println("Goodbye!!!");
                            resume = false;
                            break;
                        default:
                            // Handle invalid input
                            System.out.println("Invalid input. Try again.");
                    }
                }
            } else {
                // Display message if PIN is incorrect
                System.out.println("Incorrect PIN.");
            }
        } else {
            // Display message if the username is not found
            System.out.println("That user name does not exist in our database.");
        }
        
        // Close the scanner to avoid resource leaks
        input.close();
    }

    // Method to find a user in the userList based on the username
    private static UserInfo findUserByName(List<UserInfo> userList, String userName) {
        for (UserInfo u : userList) {
            if (u.getUserName().equals(userName)) { 
                return u; // Return user if username matches
            }
        }
        return null; // Return null if user is not found
    }

    // Method to validate account number for transfer
    private static boolean validateAccountNumber(String accNum) {
        int accNumLength = 10; // Assume valid account number should be 10 digits
        boolean valid = true;

        // Check if the account number has exactly 10 digits
        if (accNum.length() != accNumLength) {
            valid = false;
            throw new InvalidAccountNumberException("An account number must be " + accNumLength + " digits.");
        }

        // Check if all characters in the account number are digits
        for (int i = 0; i < accNumLength; i++) {
            if (!Character.isDigit(accNum.charAt(i))) {
                valid = false;
                throw new InvalidAccountNumberException("An account number must be all digits. No special characters/letters allowed.");    
            }
        }

        return valid; // Return true if the account number is valid
    }

    // Method to display the main menu of the ATM
    private static void showMainMenu() {
        System.out.println("\n\t\t~~MAIN MENU~~");
        System.out.println("\t1. Transaction History");
        System.out.println("\t2. Withdraw");
        System.out.println("\t3. Deposit");
        System.out.println("\t4. Transfer");
        System.out.println("\t5. Quit\n");
    }

    // Method to get the user's choice for the menu
    private static int getUserChoice(Scanner input) {
        System.out.print("Please enter the number corresponding to what you want to do: ");
        return input.nextInt(); // Read the user's choice
    }
}
