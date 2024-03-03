import java.time.LocalDate;

public class AcademicianMember extends Member {

    public final static int MAX_NUMBER_OF_BOOK = 4 ; //Number of books that can be borrowed.

    public final static int MAX_RETURN_TIME_DAYS = 14;  // Limit for extending

    private final boolean isAcademic = true; // Only academicians can read Handwritten books.

    public AcademicianMember(int idNumber) {
        super(idNumber);
    }

    @Override
    public String borrowBook(int bookID, LocalDate returnTime,int memberID ){
            return super.borrowBook(bookID,  returnTime,memberID, MAX_NUMBER_OF_BOOK, MAX_RETURN_TIME_DAYS);
        }


    @Override
    public String extendBook(int bookID, LocalDate extendTime,int memberID) {
        return super.extendBook(bookID,  extendTime ,memberID, MAX_RETURN_TIME_DAYS);
    }

    @Override
    public boolean getIsAcademic() {
        return isAcademic;
    }

    @Override
    public String toString(){
        return String.format("Academic [id: %d]\n",idNumber);
    }


}

