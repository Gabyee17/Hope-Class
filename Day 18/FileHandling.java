import java.io.*;
public class FileHandling{
    public static void main(String[] args) {
        try{
            File file=new File("example.txt");
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}