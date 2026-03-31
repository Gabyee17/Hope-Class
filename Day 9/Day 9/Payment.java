interface payments{
    void pay(int x);
}
class Card implements payments{
    public void pay(int x){
        System.out.println("Paid using card");
    }
}
class Upi implements payments{
    public void pay(int x){
        System.out.println("Paid using Upi");
    }
}
class Cash implements payments{
    public void pay(int x){
        System.out.println("Paid using cash");
    }
}
public class PayTask {
    public static void main(String[] args) {
        payments p1 = new Card();
        p1.pay(100);
        payments p2 = new Upi();
        p2.pay(300);
        payments p3 = new Card();
        p3.pay(20);
    }
}
