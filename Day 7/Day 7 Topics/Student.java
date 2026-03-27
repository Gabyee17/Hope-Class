class StudentDetails{
    String rollno;
    String name;
    StudentDetails(String name, String rollno){
        this.name = name;
        this.rollno = rollno;

    }
    void display(){
        System.out.println("Name: " + this.name + "\n" + "\nRoll Number: " + this.rollno);
    }
}

public class Student{
    public static void main(String[] args){
        StudentDetails s1 = new StudentDetails("Gabriel SK", "24CS510");
        s1.display();
        StudentDetails s2 = new StudentDetails("Hari Kumar", "24CSS128");
        s2.display();
        StudentDetails s3 = new StudentDetails("John Breedon", "24CS736");
        s3.display();

    }
}