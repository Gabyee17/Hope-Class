class Person {
    private String name = "Harikumar";    
    int age = 20;                      
    protected String city = "Chennai"; 
    String country = "India";   

    public String getName() {
        return name;
    }
}

class Student extends Person {
    void displayStudent() {
        System.out.println("Student Details:");
        System.out.println("Name: " + getName()); 
        System.out.println("Age: " + age);       
        System.out.println("City: " + city);      
        System.out.println("Country: " + country);
    }
}

class CollegeStudent extends Student {
    void displayCollege() {
        System.out.println("\nCollege Student Details:");
        System.out.println("Name: " + getName());
        System.out.println("City: " + city);
    }
}

class GraduateStudent extends CollegeStudent {
    void displayGraduate() {
        System.out.println("\nGraduate Student Details:");
        System.out.println("Country: " + country);
    }
}

public class Main {
    public static void main(String[] args) {
        GraduateStudent g = new GraduateStudent();

        g.displayStudent();
        g.displayCollege();
        g.displayGraduate();
    }
}