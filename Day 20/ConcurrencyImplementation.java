class Bank {
    int balance=1000;
    void withdraw(int amount){
        if(balance>=amount){
            System.out.println(Thread.currentThread().getName()+" "+ "is withdrawing");
            balance-=amount;
            System.out.println("Remaining amount: "+balance);
        }else{
            System.out.println("insufficient balance");
        }

    }
}
public class ConcurrencyImplementation{
    public static void main(String[] args) {
       Bank b=new Bank();
       Runnable task=()->{
        b.withdraw(1500);
       };
       Thread t1 = new Thread(task,"User 1");
       Thread t2 = new Thread(task,"User 2");
       t1.start();
       try {
        t1.join();
       } catch (Exception e) {
        e.printStackTrace();
       }
       t2.start();
    }
}