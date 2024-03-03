import java.time.LocalDate;

public abstract class Books  {

    protected int idNumber;  //idNumber of books
    protected boolean isRead = false; // if it is read or not.
    protected boolean isBorrowed = false; // if it is borrowed or not.
    private LocalDate readTime; // stores value for getHistory method.

    private int readMemberID;  // gets id of member who reads the book.

    public Books(int idNumber){
        this.idNumber = idNumber;
    }

    public String borrowBook(LocalDate returnTime,int memberID,int MAX_RETURN_TIME_DAYS) {
       return "This book can not be borrowed!\n";}

    public String toReadString() {
        return String.format("The book [%d] was read in library by member [%d] at %s\n",idNumber,readMemberID,readTime);
    }

    // Super method of reading. Because every book can be read.
    public String returnBook(LocalDate returnTime, int memberID) {
        if(isRead){
            if(readMemberID != memberID){
                return String.format("Only member [%d] can return this book.\n",readMemberID);  // MemberID and books memberID should match.
            }
            this.isRead = false;
            LibrarySystem.numberOfReadBooks--;
            return String.format("The book [%d] was returned by member [%d] at %s Fee: 0\n",idNumber,memberID,returnTime);
        }
        else{
            return "This book cannot be returned\n"; // If it is not borrowed or read it prints can not be returned.
        }
    }

    // Reading in library. After checking if the member is Academician or not. It allows reading.
    public String readInLibrary(int idNumber,int memberID,LocalDate currentDate,boolean isAcademic){
       if(isRead || isBorrowed){return "You can not read this book!\n";}
        else {this.isRead = true;
        this.readTime = currentDate;
        this.readMemberID = memberID;
        LibrarySystem.numberOfReadBooks++;
        return toReadString();
        }
    }

    // Polymorphism method of extend time. If it is handwritten it says that you can not extend because it can not be borrowed.
    public String extendTime(LocalDate extendTime, int memberID, int MAX_RETURN_TIME_DAYS) {
        return "You can not extend the deadline!\n";
    }
}
