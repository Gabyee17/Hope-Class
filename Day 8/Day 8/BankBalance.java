class BankAccount{
    private double balance;

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
        }
        else{
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount){
        if(amount <= balance){
            balance -= amount;
        }
        else{
            System.out.println("Invalid withdraw ammunt!");
        }
    }
}

public class BankBalance {
    public static void main(String[] args) {
        
        BankAccount account = new BankAccount();

        account.deposit(15000);
        account.deposit(10000);

        System.out.println("Current Bank balance: " + account.getBalance());

        account.withdraw(7000);
        System.out.println("Current Bank balance: " + account.getBalance());
    }
}
