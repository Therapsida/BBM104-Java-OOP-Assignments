public class Board {
    private static int XLocation; private static int YLocation; // Location of '*' we get it via getLocation() method
    private static int totalScore = 0;  // static total score value
    private  char[][] board;  // board array

    private String totalMovements = "";  // Legal moves of game
    public boolean isGameOver = true;   // For printing game over and breaking from loop


    // Board  Constructer with class method readfile()
    public  Board(String path) {
        String[] array = FileInput.readFile(path, true, true); // making board array which is 2d
        char[][] board = new char[array.length][];
        for (int i = 0; i < array.length; i++) {
            char[] charArray = array[i].replaceAll(" ","").toCharArray();
            board[i] = charArray;
            this.board = board;
        }
    }

    // To learn the X and Y axis of '*'
    public void getLocation(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == '*'){
                    YLocation = i;
                    XLocation =j;
                }
            }
        }
    }

    // Takes command and gives parameters for move function. For example move L function gives
    // XLocation-1 which will be swapped with left square
    public  boolean command(char X ){
        totalMovements += X ;
        switch(X){
            case 'L' : return move(XLocation-1,YLocation);
            case 'R' : return move(XLocation+1,YLocation);
            case 'D' : return move(XLocation,YLocation+1);
            case 'U' : return move(XLocation,YLocation-1);
        }

        return true;
    }


    // Swapping method of the game
    public boolean move(int moveX, int moveY){
        int[] move = {moveX,moveY};
        move = edgeChecker(move);
        moveX = move[0] ;  moveY = move[1];


        // Hole interaction
        if(board[moveY][moveX] == 'H'){
            board[YLocation][XLocation] = ' ';
            return false;
        }

        // Wall interaction. For example if moveY == YLocation which can prove it moves on X-axis
        // it changes the  value of moveX
        if(board[moveY][moveX] == 'W'){
            if(moveY == YLocation ){
                if(moveX > XLocation){moveX -= 2;}
                else{moveX += 2;}
            }
            if(moveX == XLocation){
                if(moveY > YLocation){moveY += 2;}
                else{moveY -= 2;}
                }

            // This part prevents getting out of the board.
            move[0] = moveX ; move[1] = moveY;
            move = edgeChecker(move);
            moveX = move[0] ;  moveY = move[1];
        }


        getScore(moveX,moveY);

        // Swapping part. X and Y locations are updated.
        char temp;
        temp = board[moveY][moveX];
        board[moveY][moveX] = '*';
        board[YLocation][XLocation] = temp;
        YLocation = moveY;
        XLocation = moveX;

        return true;
    }

    //Simply controls edge transportation and W interactions.
    public  int[] edgeChecker(int[] move){
        int moveX; int moveY;
        moveX = move[0] ; moveY= move[1];
        if(moveX < 0 ){moveX += board[0].length; }
        if(moveX > board[0].length-1){moveX -= board[0].length;}
        if(moveY < 0 ){moveY += board.length; }
        if(moveY > board.length-1){moveY -= board.length;}
        move[0] = moveX;
        move[1] = moveY;

        return move;


    }


    private void getScore(int moveX, int moveY){
        if (board[moveY][moveX] == 'R'){totalScore += 10;  board[moveY][moveX] = 'X';}
        if (board[moveY][moveX] == 'Y'){totalScore += 5;   board[moveY][moveX] = 'X';}
        if (board[moveY][moveX] == 'B'){totalScore -= 5;   board[moveY][moveX] = 'X';}

    }

    // Parameter situation gives the information about situation whether it is Game Board or Output.
    public  String getBoard(String situation){
        String boardSituation;
        if(situation.equals("start")){ boardSituation = "Game board:\n";}
        else{boardSituation = "Your output is:\n";}
        for(char[] line : board ){
            boardSituation += String.valueOf(line).replaceAll(".(?!$)", "$0 ") + "\n";
        }
        return boardSituation +"\n";
    }

    // Last information includes total movements and getBoard().
    public  String getLastInformation(){
        String output = "Your movement is:\n" + totalMovements +"\n\n" + getBoard("final");
        if(!isGameOver){output += "Game Over!\n";}
        return output + "Score: " + totalScore;

    }

}
