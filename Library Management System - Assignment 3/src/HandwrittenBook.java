import java.time.LocalDate;

public class HandwrittenBook extends Books{
    public HandwrittenBook(int idNumber) {
        super(idNumber);
    }

    @Override
    public String returnBook(LocalDate returnTime, int memberID){
        if(isRead){return super.returnBook(returnTime,memberID);}
        else {
            return "HandWritten books can not be returned.\n";
        }
    }


    // Handwritten books can only be read by academician so it checks and after that it calls super method.
    @Override
    public String readInLibrary(int idNumber,int memberID,LocalDate currentDate,boolean isAcademic){
        if(isAcademic){
            return super.readInLibrary(idNumber,memberID,currentDate, true);
        }
        else{
            return "Students can not read handwritten books!\n";
        }
    }
    @Override
    public String toString(){
        return String.format("Handwritten [id: %d]\n",idNumber);
    }
}
