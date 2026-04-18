import java.io.*;
public class FileOperations {
    public static void FileReaderImplementation(String[] args) {
        try{
            FileReader reader=new FileReader("example.txt");
             int character;
             while((character=reader.read())!=-1){
                 System.out.print((char)character);
            }
            reader.close();
        }catch(IOException e){
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
    public static void FileWriterImplementation(String[] args) {
        try {
            FileWriter writer = new FileWriter("example.txt");
            writer.write("Hello, this is a test file.\n");
            writer.write("This file is created using FileWriter in Java.");
            writer.close();
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}