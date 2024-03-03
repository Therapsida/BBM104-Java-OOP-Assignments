//Yunus Emre Uluer 2210356102
public class Main {
    public static void main(String[] args){
        String output = "";

        // Creating Board object
        Board board = new Board(args[0]);

        char[] charArray = FileInput.readFile(args[1],true, true)[0].toCharArray();
        output += board.getBoard("start");
        board.getLocation();

        //Reading commands
        for(char X : charArray){
            board.isGameOver = board.command(X);
            if(!board.isGameOver){break;}
        }

        output += board.getLastInformation();

        //FileOutput method
        FileOutput.writeToFile("output.txt",output,false,false);




    }
}



