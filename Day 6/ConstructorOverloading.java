import java.util.*;
public class ConstructorOverloading {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your name:");
        String name=sc.nextLine();
        System.out.println("Enter your age:");
        int age=sc.nextInt();
        Person p1=new Person(name,age);
        System.out.println("Name: "+p1.getName());
        System.out.println("Age: "+p1.getAge());
        System.out.println("Enter your name:");
        sc.nextLine(); // Consume the newline
        String name2=sc.nextLine();
        System.out.println("Enter your age:");
        int age2=sc.nextInt();
        System.out.println("Enter your city:");
        sc.nextLine(); // Consume the newline
        String city=sc.nextLine();
        Person p2=new Person(name2,age2,city);
        System.out.println("Name: "+p2.getName());
        System.out.println("Age: "+p2.getAge());
        System.out.println("City: "+p2.getCity());
    }
    public static class Person{
        private String name;
        private int age;
        private String city;
        public Person(String name,int age){
            this.name=name;
            this.age=age;
            this.city="Unknown";
        }
        public Person(String name,int age,String city){
            this.name=name;
            this.age=age;
            this.city=city;
        }
        public String getName(){
            return name;
        }
        public int getAge(){
            return age;
        }
        public String getCity(){
            return city;
        }
    }
}