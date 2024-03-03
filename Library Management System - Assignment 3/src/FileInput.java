import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class FileInput {
    public static void readFile(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path)); // Gets the content of file to the list.
            lines.removeIf(line -> line.trim().equals(""));
            lines.replaceAll(String::trim);

            //Reads  input  and calls Library system methods after parsing.
            for (String line : lines){
            try {


                String[] splitLine = line.split("\t");
                String command = splitLine[0];
                switch (command) {
                    case "addBook":
                        LibrarySystem.addBook(splitLine[1]);
                        break;
                    case "borrowBook":
                        LibrarySystem.borrowBook(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3]);
                        break;
                    case "returnBook":
                        LibrarySystem.returnBook(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3]);
                        break;
                    case "readInLibrary":
                        LibrarySystem.readInLibrary(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3]);
                        break;
                    case "getTheHistory":
                        LibrarySystem.getTheHistory();
                        break;
                    case "extendBook":
                        LibrarySystem.extendBook(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3]);
                        break;
                    case "addMember":
                        LibrarySystem.addMember(splitLine[1]);
                        break;
                }
            }catch (Exception e){LibrarySystem.output += ("Wrong Output\n");}

            }


        }catch (NoSuchFileException e) {
            LibrarySystem.output += String.format("No Such File as %s\n",path);
        }catch (IOException e){
            LibrarySystem.output += ("IO Error.\n");
        }
    }
}



