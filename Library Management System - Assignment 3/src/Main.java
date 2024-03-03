import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


// Yunus Emre Uluer 2210356102

public class Main {
    public static void main(String[] args) {

         try {
             FileInput.readFile(args[0]);
         }catch (ArrayIndexOutOfBoundsException e){
             System.out.println("Missing input argument");
         }
         finally {
           try {
               PrintStream printStream = new PrintStream(new FileOutputStream(args[1], false));
               printStream.print( LibrarySystem.output);
           }
           catch (ArrayIndexOutOfBoundsException e){
               System.out.println("Missing output argument");
           }
           catch (FileNotFoundException e){
               System.out.println("File not found");
           }

         }




    }


}