import java.util.*;
public class Constructor {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your name:");
        String name=sc.nextLine();
        System.out.println("Enter your age:");
        int age=sc.nextInt();
        Person p=new Person(name,age);
        System.out.println("Name: "+p.getName());
        System.out.println("Age: "+p.getAge());
    }
    public static class Person{
        private String name;
        private int age;
        public Person(String name,int age){
            this.name=name;
            this.age=age;
        }
        public String getName(){
            return name;
        }
        public int getAge(){
            return age;
        }
    }
}