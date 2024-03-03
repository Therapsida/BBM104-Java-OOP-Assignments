import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PrintedBook extends Books{




    private int borrowedMemberID;   // When book is borrowed it also stores who borrowed it.

    private LocalDate returnTime ;  // Deadline of the book.

    private LocalDate borrowedTime; // Time when the book is borrowed.

    private boolean isExtended = false; // Every book can be extended one time.


    public PrintedBook(int idNumber) {
        super(idNumber);
    }


    public boolean getIsBorrowed(){
        return isBorrowed;
    }


    //borrow book function. Only printed books can be borrowed. Checks if it is already borrowed or read.
    @Override
    public String borrowBook(LocalDate returnTime,int memberID, int MAX_RETURN_TIME_DAYS){
          if (isBorrowed || isRead) {
              return ("You cannot borrow this book!\n");

          }
          else {
              LibrarySystem.numberOfBorrowedBooks++;
              this.borrowedTime = returnTime;
              this.returnTime = returnTime.plusDays(MAX_RETURN_TIME_DAYS);
              isBorrowed = true;
              borrowedMemberID = memberID;
              return toBorrowString();
          }
    }


    // Returning the book. Read and Borrowed books can be returned so after it checks if it is borrowed. It calls super method
    // which returns the book if it is read.
    @Override
    public String returnBook(LocalDate returnTime, int memberID){
        if(isBorrowed){
            if(borrowedMemberID != memberID){
                return String.format("Only member [%d] can return this book.\n",borrowedMemberID);  // MemberID and books memberID should match.
            }
            this.isBorrowed = false;
            isExtended = false;
            long fee = 0;
            if(returnTime.isAfter(this.returnTime)){
                fee =  ChronoUnit.DAYS.between(this.returnTime, returnTime);}
            this.returnTime = null;
            LibrarySystem.numberOfBorrowedBooks--;  // It stores the numbers for getHistory method.

            String output =    String.format("The book [%d] was returned by member [%d] at %s Fee: %d\n",idNumber,memberID,returnTime,fee);
            if(fee == 0){
                return output;
            }
            else{
                return output + "You must pay a penalty!\n";
            }
        }
        else{
            return super.returnBook(returnTime,memberID);
        }
    }


    // This method is for extends books returnTime. MAX_RETURN_TIME_DAYS is a members limit for extending.
    @Override
    public String extendTime(LocalDate extendTime, int memberID, int MAX_RETURN_TIME_DAYS) {
        if(returnTime == null){return "Can not extend book because this book is not borrowed.\n";}
        else if(borrowedMemberID != memberID){
            return String.format("Only member [%d] can extend this book.\n",borrowedMemberID);
        }
        else if(isExtended){return "You cannot extend the deadline!\n";}
        else if(!(extendTime.isAfter(returnTime))){
            returnTime = returnTime.plusDays(MAX_RETURN_TIME_DAYS);
            isExtended = true;
            return String.format("The deadline of book [%d] was extended by member [%d] at %s\nNew deadline of book [%d] is %s\n",idNumber,memberID,extendTime,idNumber,returnTime);

        }
        else{
            return "Return time has passed. You need to pay fee.\n";
        }
    }

    public String toBorrowString() {
        return String.format("The book [%d] was borrowed by member [%d] at %s\n",idNumber,borrowedMemberID,borrowedTime);
    }

    @Override
    public String toString(){
        return String.format("Printed [id: %d]\n",idNumber);
    }
}
