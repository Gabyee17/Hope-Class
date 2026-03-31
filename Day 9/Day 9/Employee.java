import java.util.*;

interface Emp{
    void calcSalary();
}

class FullTimeEmployee implements Emp{
    String name;
    int id;
    FullTimeEmployee(String name,int id){
        this.name=name;
        this.id=id;
    }
    public void calcSalary(){
        System.out.println("Salary for your fulltime job is:"+(100000));
    }
}

class PartTimeEmployee implements Emp{
    String name;
    int id;
    PartTimeEmployee(String name,int id){
        this.name=name;
        this.id=id;
    }
    Scanner sc=new Scanner(System.in);
    public void calcSalary(){
        System.out.println("Total Number of days worked in the month :");
        int total=sc.nextInt();
        System.out.println("Salary for the part time job :"+(total*1000));
    }
}
public class Employee{
    public static void main(String[] args){
        Emp f = new FullTimeEmployee("Ed",1);
        Emp p = new PartTimeEmployee("Abi",2);
        f.calcSalary();
        p.calcSalary();
    }
}