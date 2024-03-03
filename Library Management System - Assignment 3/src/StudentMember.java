import java.time.LocalDate;

public class StudentMember extends Member{

    public final int MAX_NUMBER_OF_BOOK = 2; //Number of books that can be borrowed.

    public final static int MAX_RETURN_TIME_DAYS = 7;  // Limit for extending

    private final boolean isAcademic = false;  // Only academicians can read Handwritten books.

    public StudentMember(int idNumber) {
        super(idNumber);
    }

    @Override
    public String borrowBook(int bookID, LocalDate returnTime, int memberID ){
        return super.borrowBook(bookID,  returnTime, memberID, MAX_NUMBER_OF_BOOK, MAX_RETURN_TIME_DAYS);
    }


    @Override
    public String extendBook(int bookID, LocalDate extendTime,int memberID) {
        return super.extendBook(bookID, extendTime, memberID, MAX_RETURN_TIME_DAYS);
    }

    @Override
    public boolean getIsAcademic() {
        return isAcademic;
    }

    @Override
    public String toString(){
        return String.format("Student [id: %d]\n",idNumber);
    }
}
