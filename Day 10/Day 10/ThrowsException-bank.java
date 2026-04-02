class InsuffiecientBalanceException extends Exception{
    InsufffiecientBalanceException(String message){
        super(message);
    }
}
class Bank{
    private double balance;
    void deposit(int amount)throws InsufficientBalanceException{
        if(amount > 0){
            balance += amout;
        }else if(amount <= 0){
            throw new InsufficientBalanceException("Amount cant be 0");
        }
    }
    coid withdraw(int amount) throws InsufficientBalanceException{
        if(balance < 0){
            throw new InsufficientBalanceException("Balance Insuffiecient");
        } else if(amount > balance){
            throw new InsufficientBalanceException("Amout is higher");
        }else{
            balance -= amount;
            System.out.print("Amount withdrawn");
        }
    }
    void getbalance(){
        System.out.print("Account Balance is:"+ balance);
    }
}
public class throwsExceptionExample{
    public static void main(String[] args)throws InsufficientBalanceException{
        Bank b = new Bank();
        try{
            b.deposit(2-);
            b.getbalance():
            b.deposit(-20);
        }catch (Exception e ){
            System.out.print(e.getMessage());
        }
        try{
            b.withdraw(10);
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        b.getBalance;

    }
}