
abstract class Payment {
    protected double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    
    abstract void processPayment();

    // Concrete method
    void showPaymentDetails() {
        System.out.println("Payment of amount: $" + amount);
    }
}


interface Transaction {
    void validateTransaction() throws Exception;
    void generateReceipt();
}

// Concrete class implementing abstract class and interface
class CreditCardPayment extends Payment implements Transaction {
    private String cardNumber;

    // Constructor
    CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    // Implement abstract method
    
    void processPayment() {
        System.out.println("Processing credit card payment...");
    }

    // Implement interface methods
    
    public void validateTransaction() throws Exception {
        if (cardNumber.length() != 16) {
            throw new Exception("Invalid card number! Card number must be 16 digits.");
        }
        System.out.println("Transaction validated successfully.");
    }

    
    public void generateReceipt() {
        System.out.println("Generating receipt for credit card payment...");
        System.out.println("Payment successful! Amount: $" + amount);
    }
}


public class PaymentSystem {
    public static void main(String[] args) {
        
        System.out.println("---- Case 1: Valid Card Number ----");
        runPaymentTest(250.75, "1234567890123456");

        
        System.out.println("\n---- Case 2: Invalid Card Number ----");
        runPaymentTest(250.75, "12345");
    }

    private static void runPaymentTest(double amount, String cardNumber) {
        try {
            
            CreditCardPayment payment = new CreditCardPayment(amount, cardNumber);    
            payment.showPaymentDetails();

            payment.validateTransaction();
          
            payment.processPayment();
        
            payment.generateReceipt();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
