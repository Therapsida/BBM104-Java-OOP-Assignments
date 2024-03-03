import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class LibrarySystem {

    public static String output = ""; // Output of the system. All outputs stores in this String.

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Formatter.


    // These numbers are storing the general information
    private static int  numberOfMembers = 0;
    private static int numberOfBooks = 0;

    protected static int numberOfBorrowedBooks = 0;
    protected static int numberOfReadBooks = 0;


    private static int numberOfHandWrittenBook = 0;

    private static int numberOfPrintedBook = 0;

    private static int numberOfStudentMember = 0;

    private static int numberOfAcademicianMember = 0;


    // Systems collection. it stores objects with their id's as a key
    public static HashMap<Integer,Books> books = new HashMap<>();
    public static HashMap<Integer,Member> members = new HashMap<>();


    // calls members addMembers method.
    public static void addMember(String memberType ){
        if(memberType.equals("A") || memberType.equals("S")) {
            if (memberType.equals("A")) {
                numberOfAcademicianMember++;
            } else {
                numberOfStudentMember++;
            }

            String memberOutput = memberType.equals("S") ? "Student" : "Academic";
            members.put(++numberOfMembers, memberType.equals("S") ? new StudentMember(numberOfMembers) : new AcademicianMember(numberOfMembers));
            output += String.format("Created new member: %s [id: %d]\n", memberOutput, numberOfMembers);

        }
        else{
            output += "Wrong Output\n";
        }

    }

    // calls members addBook method.
    public static void addBook(String bookType ) {
        if (bookType.equals("H") || bookType.equals("P")) {

            if(bookType.equals("H")){numberOfHandWrittenBook++;}
            else{numberOfPrintedBook++;}

            String bookOutput = bookType.equals("H") ? "Handwritten" :  "Printed";
            books.put(++numberOfBooks, bookType.equals("H") ? new HandwrittenBook(numberOfBooks) : new PrintedBook(numberOfBooks));
            output += String.format("Created new book: %s [id: %d]\n",bookOutput,numberOfBooks);
        }
    else{output += "Wrong Output\n";}
    }



    // calls members borrowBook method.
    public static void borrowBook(int bookID, int memberID, String date) {
    try{
        if(members.get(memberID) != null && books.get(bookID) != null  ){
            output += members.get(memberID).borrowBook(bookID, LocalDate.parse(date,formatter),memberID);
        }

    }catch (Exception e){output+= e.getMessage();}
    }

    // calls members returnBook method.
    public static void returnBook(int bookID, int memberID, String date) {
        try{
            if(members.get(memberID) != null && books.get(bookID) != null  ){
                output += members.get(memberID).returnBook(bookID, LocalDate.parse(date,formatter),memberID);
            }

        }catch (Exception e){e.printStackTrace();}
    }

    // calls members readInLibrary method.
    public static void readInLibrary(int bookID, int memberID, String currentDate) {
        try{
            if(members.get(memberID) != null || members.get(bookID) != null  ){
                output += members.get(memberID).readInLibrary(bookID, LocalDate.parse(currentDate,formatter),memberID);
            }

        }catch (Exception e){output+= e.getMessage();}
    }

    // calls members extendBook method.
    public static void extendBook(int bookID, int memberID, String date) {
        try{
            if(members.get(memberID) != null || members.get(bookID) != null  ){
                output += members.get(memberID).extendBook(bookID, LocalDate.parse(date,formatter),memberID);
            }
        }catch (Exception e){output += "Wrong Output\n";}

    }


    // String method.
    public static void getTheHistory() {
        output += "History of library:\n";
        output += String.format("\nNumber of students: %d\n",numberOfStudentMember);
        for(Member member : members.values()) {

            if(member instanceof StudentMember){
                output += member.toString();
            }
        }
        output += String.format("\nNumber of academics: %d\n",numberOfAcademicianMember);
        for(Member member : members.values()) {

            if(member instanceof AcademicianMember){
                output += member.toString();
            }
        }
        output += String.format("\nNumber of printed books: %d\n",numberOfPrintedBook);
        for(Books book : books.values()){

            if(book instanceof PrintedBook){
                output += book.toString();
            }
        }

        output += String.format("\nNumber of handwritten books: %d\n",numberOfHandWrittenBook);
        for(Books book : books.values()){

            if(book instanceof HandwrittenBook){
                output += book.toString();
            }
        }

        output += String.format("\nNumber of borrowed books: %d\n",numberOfBorrowedBooks);
        for(Books book : books.values()){

            if(book instanceof PrintedBook){
                PrintedBook printedBook = (PrintedBook) book;
                if( printedBook.getIsBorrowed()){
                    output += printedBook.toBorrowString();
                }
            }
        }
        output += String.format("\nNumber of books read in library: %d\n",numberOfReadBooks);
        for(Books book : books.values()){

            if(book.isRead){
                output += book.toReadString();
            }
        }




    }




}
