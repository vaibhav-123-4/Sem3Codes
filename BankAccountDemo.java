
class Account {
    protected String accountNumber;
    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }  
    public String toString() {
        return "Account Number: " + accountNumber;
    }
}
class BalanceInfo {
    private double balance;
    private double interestRate;
    public BalanceInfo(double balance, double interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public String toString() {
        return "Balance: $" + balance + ", Interest Rate: " + interestRate + "%";
    }
}
class BankAccount extends Account {
    private BalanceInfo balanceInfo;
    public BankAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber);  
        this.balanceInfo = new BalanceInfo(balance, interestRate);
    }
    public BankAccount(BankAccount original) {
        super(original.accountNumber);
        this.balanceInfo = original.balanceInfo;  // Shallow copy 
    }
    // Deep copy 
    public BankAccount(BankAccount original, boolean deepCopy) {
        super(original.accountNumber);  // Copying accountNumber
        if (deepCopy) {
            this.balanceInfo = new BalanceInfo(original.balanceInfo.getBalance(), original.balanceInfo.getInterestRate());
        }
    }
    public BalanceInfo getBalanceInfo() {
        return balanceInfo;
    }
    public void deposit(double amount) {
        balanceInfo.setBalance(balanceInfo.getBalance() + amount);
    }

    public void deposit(double amount1, double amount2) {
        balanceInfo.setBalance(balanceInfo.getBalance() + amount1 + amount2);
    }
    public void withdraw(double amount) {
        if (balanceInfo.getBalance() >= amount) {
            balanceInfo.setBalance(balanceInfo.getBalance() - amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }
 public String toString() {
        return super.toString() + ", " + balanceInfo.toString();
    }
}
public class BankAccountDemo {
    public static void main(String[] args) {
        Account account = new Account("A123456");
        System.out.println(account);
        BankAccount bankAccount = new BankAccount("B987654", 1000.0, 2.5);
        System.out.println(bankAccount);
        bankAccount.deposit(200);
        System.out.println(bankAccount);
        bankAccount.deposit(50, 100);
        System.out.println(bankAccount);

        
         //System.out.println("this is it:" + bankAccount.accountNumber); 

        
        // System.out.println(bankAccount.balanceInfo.balance); 

        
        System.out.println("Balance: $" + bankAccount.getBalanceInfo().getBalance());
        System.out.println("Interest Rate: " + bankAccount.getBalanceInfo().getInterestRate() + "%");
        BankAccount shallowCopy = new BankAccount(bankAccount);
        System.out.println("Shallow Copy: " + shallowCopy);
        bankAccount.deposit(100);
        System.out.println("Original After Modification: " + bankAccount);
        System.out.println("Shallow Copy After Original Modification: " + shallowCopy);

        // Create a deep copy of the BankAccount object
        BankAccount deepCopy = new BankAccount(bankAccount, true);
        System.out.println("Deep Copy: " + deepCopy);

        bankAccount.withdraw(200);
        System.out.println("Original After Further Modification: " + bankAccount);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy After Original Modification: " + deepCopy);
    }
}
