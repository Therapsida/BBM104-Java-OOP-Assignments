import java.time.LocalDate;
import java.util.ArrayList;

public abstract class  Member {

    protected int borrowedBooks = 0;

    protected int idNumber;

    public Member(int idNumber){
        this.idNumber = idNumber;
    }

    public abstract String borrowBook(int bookID, LocalDate returnTime,int memberID);

    public abstract String extendBook(int bookID, LocalDate extendTime, int memberID);

    public abstract boolean getIsAcademic();


    // Sends it to the books class after some operations. This operations are numbers of borrowed books(Has limit for every member)
    // and Limit of extending for other members.
    public String  borrowBook(int bookID, LocalDate returnTime, int memberID, int MAX_NUMBER_OF_BOOK,int MAX_RETURN_TIME_DAYS){
    try {
         if(borrowedBooks < MAX_NUMBER_OF_BOOK){
            String output = LibrarySystem.books.get(bookID).borrowBook(returnTime,memberID,MAX_RETURN_TIME_DAYS);
            borrowedBooks++;
            return output;
        }
        else{return "You have exceeded the borrowing limit!\n";}
    } catch (Exception e ){return "";}
    }


    // After getting limit for extending it calls books functions.
    public String extendBook(int bookID, LocalDate extendTime, int memberID,int MAX_RETURN_TIME_DAYS) {
        return LibrarySystem.books.get(bookID).extendTime(extendTime,memberID,MAX_RETURN_TIME_DAYS);
    }

    // After getting limit for extending it calls books functions.
    public String returnBook(int bookID, LocalDate returnTime, int memberID) {
        String output = LibrarySystem.books.get(bookID).returnBook(returnTime,memberID);
        borrowedBooks--; //borrowedBooks calculations.
        return output;
    }

    // HandWritten books can be read by Academicians only.
    public String readInLibrary(int bookID, LocalDate currentTime, int memberID) {
        return LibrarySystem.books.get(bookID).readInLibrary(bookID,memberID,currentTime,getIsAcademic());
    }


}
