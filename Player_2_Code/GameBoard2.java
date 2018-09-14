
package connectreversi2;

/**
 *
 * @author Paul Biocco
 */
public class GameBoard2 {
        private String[][] board = new String[7][7];



    public GameBoard2() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = " ";
            }
        }
    }
    
    public GameBoard2(GameBoard2 x){
        this.board = x.getBoard();
    }
    
    public void setLocation(int x, int y, String symbol){
        board[x][y] = symbol;
    }

    public boolean isGameDone() { //Returns false if a move wasn't possible to make. 

        for (int i = 0; i < getBoard().length; i++) {
            if (" ".equals(getBoard()[i][6])) {   //Only need to check the top row
                return false;  //There is a space remaining open. 
            }
        }
        return true;  //There is no space remaining.
    }
    
    public String getSymbol(int x, int y){
        return getBoard()[x][y];
    }

    public boolean makeMove(int x, int playerMove) { //Return false if move was invalid.
        for (int i = 0; i < getBoard().length; i++) {  
            if (" ".equals(getBoard()[x][i])) {   //Only need to check the chosen collumn for move validation. Will fill the appropriate spot in the collumn.
                if (playerMove == 1) {
                    getBoard()[x][i] = "X";
                    convertOrbs(x, i, getBoard()[x][i]); //Calc flips

                    return true;
                } else {
                    getBoard()[x][i] = "O";
                    convertOrbs(x, i, getBoard()[x][i]); //Calc flips
                    return true;
                }
            }
        }
        System.out.println("Invalid move");
        return false;
    }

    public void printBoard() {
        for (int i = getBoard().length - 1; i >= 0; i--) {
            for (int j = 0; j < getBoard().length; j++) {
                System.out.print("|" + getBoard()[j][i]);
            }
            System.out.println("|");
        }

        System.out.println("____________________");

        System.out.println(" 1 2 3 4 5 6 7");

    }
    
    public int checkWinner(){
        int p1 = 0;
        int p2 = 0;
        
        for(int i = 0; i < getBoard().length; i++){
            for(int j = 0; j < getBoard().length; j++){
                if(getBoard()[i][j] == "X"){
                    p1++;
                }else if(getBoard()[i][j] == "O"){
                    p2++;
                }
            }
        }
        System.out.println("Player one had a score of: " + p1);
        System.out.println("Player two had a score of: " + p2);
        
        if(p1 > p2)return 1;
        else return 2;
    }

    public void convertOrbs(int a, int b, String symbol) { // A, b, = x,y coordinate of game. Symbol = player's symbol 
        for (int y = b - 1; y <= b + 1; y++) {  //Checking a 3x3 area around the places piece.                
            for (int x = a - 1; x <= a + 1; x++) { 
                if (x >= 0 && y >= 00 && x <= 6 && y <= 6) {   //Make sure the move is within bounds. 
                    if (getBoard()[x][y] != " " && getBoard()[x][y] != symbol) {

                        //South. Note, north will never be used. Can neve capture from below
                        if (b > y && x == a) {
                            int distance = -1;
                            for (int z = 1; z < 4; z++) {  //Check for at most 3 moves in any direction. 
                                if (b - z >= 0) {
                                    if(getBoard()[a][b - z] == " "){   //If there is a space in that direction, don't swap.
                                        break;
                                    }
                                    if (getBoard()[a][b - z] == symbol) { // If there is a matching symbol, calc distance. 
                                        distance = b - (b - z);
                                        
                                    }
                                }
                            }
                            for (int z = 0; z < distance; z++) { //Swap appriopriate distance. 
                                getBoard()[a][b - z] = symbol;
                            }
                        }

                        //West
                        if (a > x && y == b) {
                            int distance = -1;
                            for (int z = 1; z < 4; z++) {
                                if (a - z >= 0) {
                                    if(getBoard()[a - z][b] == " "){
                                        break;
                                    }
                                    if (getBoard()[a - z][b] == symbol) {
                                        distance = a - (a - z);
                                        
                                    }
                                }
                            }
                            for (int z = 0; z < distance; z++) {
                                getBoard()[a - z][b] = symbol;
                            }
                        }

                        //East
                        if (a < x && y == b) {
                            int distance = -1;
                            for (int z = 1; z < 4; z++) {
                                if (a + z < 7) {
                                    if(getBoard()[a+z][b] == " "){
                                        break;
                                    }
                                    if (getBoard()[a + z][b] == symbol) {
                                        distance = a + z - a;
                                        
                                    }
                                }
                            }
                            for (int z = 0; z < distance; z++) {
                                getBoard()[a + z][b] = symbol;
                            }
                        }

                        //Southwest
                        if (b > y && a > x) {
                            int distance = -1;
                            for (int z = 1; z < 4; z++) {
                                if (b - z >= 0 && a - z >= 0) {
                                    if(getBoard()[a - z][b-z] == " "){
                                        break;
                                    }
                                    if (getBoard()[a - z][b - z] == symbol) {
                                        distance = b - (b - z);
                                        
                                    }
                                }
                            }
                            for (int z = 0; z < distance; z++) {
                                getBoard()[a - z][b - z] = symbol;
                            }

                        }

                        //Southeast
                        if (b > y && a < x) {
                            int distance = -1;
                            for (int z = 1; z < 4; z++) {
                                if (b - z >= 0 && a + z < 7) {
                                    if(getBoard()[a + z][b - z] == " "){
                                        break;
                                    }
                                    if (getBoard()[a + z][b - z] == symbol) {
                                        distance = b - (b - z);
                                        
                                    }
                                }
                            }
                            for (int z = 0; z < distance; z++) {
                                getBoard()[a + z][b - z] = symbol;
                            }

                        }

                        //Northwest  
                        if (b < y && a > x) {
                            int distance = -1;
                            for (int z = 1; z < 4; z++) {
                                if (b + z < 7 && a - z >= 0) {
                                    if(getBoard()[a - z][b + z] == " "){
                                        break;
                                    }
                                    if (getBoard()[a - z][b + z] == symbol) {
                                        distance = a - (a - z);
                                        
                                    }
                                }
                            }
                            for (int z = 0; z < distance; z++) {
                                getBoard()[a - z][b + z] = symbol;
                            }

                        }

                        //Northeast
                        if (b < y && a < x) {
                            int distance = -1;
                            for (int z = 1; z < 4; z++) {
                                if (b + z < 7 && a + z < 7) {
                                    if(getBoard()[a + z][b + z] == " "){
                                        break;
                                    }
                                    if (getBoard()[a + z][b + z] == symbol) {
                                        distance = (b + z) - b;
                                        
                                    }
                                }
                            }
                            for (int z = 0; z < distance; z++) {
                                getBoard()[a + z][b + z] = symbol;
                            }

                        }

                    }
                }
            }
        }

    }

    /**
     * @return the board
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(String[][] board) {
        this.board = board;
    }
}
