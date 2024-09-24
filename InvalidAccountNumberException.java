/**
@author Talani Malwandla Mlangeni
@version Java Development task 3
*/
// Custom exception class to handle invalid account number errors
public class InvalidAccountNumberException extends RuntimeException {

    // Default constructor with a generic error message
    public InvalidAccountNumberException() {
        this("Invalid Account number");  
    }

    // Constructor that accepts a custom error message
    public InvalidAccountNumberException(String m) {
        super(m);  // Call the superclass (RuntimeException) constructor with the message
    }
}
