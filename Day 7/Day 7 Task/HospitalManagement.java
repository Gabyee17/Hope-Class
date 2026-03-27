// Base Class
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

// Derived Class - Doctor
class Doctor extends Person {
    String specialization;

    Doctor(String name, int age, String specialization) {
        super(name, age);
        this.specialization = specialization;
    }

    void displayDoctor() {
        display();
        System.out.println("Specialization: " + specialization);
    }
}

// Derived Class - Patient
class Patient extends Person {
    String disease;

    Patient(String name, int age, String disease) {
        super(name, age);
        this.disease = disease;
    }

    void displayPatient() {
        display();
        System.out.println("Disease: " + disease);
    }
}

// Hospital Class
class Hospital {
    Doctor doctor;
    Patient patient;

    Hospital(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
    }

    void showDetails() {
        System.out.println("\n--- Doctor Details ---");
        doctor.displayDoctor();

        System.out.println("\n--- Patient Details ---");
        patient.displayPatient();
    }
}

// Main Class
public class HospitalManagement {
    public static void main(String[] args) {
        Doctor d1 = new Doctor("Dr. Smith", 45, "Cardiology");
        Patient p1 = new Patient("John", 30, "Fever");

        Hospital h = new Hospital(d1, p1);
        h.showDetails();
    }
}