public class ComparableImplementation {
    public static void main(String[] args){
       List<Student> students=new ArrayList<>();
         students.add(new Student("Alice", 85));
            students.add(new Student("Bob", 90));
            students.add(new Student("Charlie", 80));
            Collections.sort(students);
            for(Student student:students){
                System.out.println(student.rollno()+" - "+student.name());
        }
    }
}
class Student implements Comparable<Student>{
    private String name;
    private int rollno;
    public Student(String name,int rollno){
        this.name=name;
        this.rollno=rollno;
    }
    public String name(){
        return name;
    }
    public int rollno(){
        return rollno;
    }
    @Override
    public int compareTo(Student other){
        return Integer.compare(this.rollno,other.rollno);
    }
}