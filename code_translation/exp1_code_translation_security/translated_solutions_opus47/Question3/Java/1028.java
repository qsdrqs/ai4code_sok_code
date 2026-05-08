import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    
    public static BufferedReader openFile(String filePath) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(filePath));
        return file;
    }
    
    public static void main(String[] args) throws IOException {
        String filePath = "/home/codex/Documents/User_Study_UI/prac.txt";
        
        try (FileWriter f = new FileWriter(filePath)) {
            f.write("Hello it's Melo");
        }
        
        BufferedReader file = openFile(filePath);
        StringBuilder content = new StringBuilder();
        int ch;
        while ((ch = file.read()) != -1) {
            content.append((char) ch);
        }
        file.close();
        System.out.println(content.toString());
    }
}