
package connectreversi2;

import java.util.Scanner;

/**
 *
 * @author Paul Biocco
 */
public class ConnectReversi2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                // TODO code application logic here
       
        //Game prompt
        System.out.println("Welcome to connect Reversi! To move enter an integer 1-7.");
        System.out.println("The object is to have your pieces occupy the most spaces!");
        System.out.println("Good luck");
        
        //Create required objects.
        GameBoard2 game = new GameBoard2();
        GameAI2 robot = new GameAI2();
        game.printBoard();
        
        Scanner keyreader = new Scanner(System.in);
        
        int playerStatus = 1; //Player 1 goes first. 
        
        while(!game.isGameDone()){  
            
            int move;
            
            if(playerStatus == 2){  //Get Robot move. 
                    
                
                GameBoard2 temp = new GameBoard2(); //Clone the board. This is java specific. 
                for(int i = 0; i < 7; i++){
                    for(int j = 0; j < 7; j++){
                        temp.setLocation(i, j, game.getSymbol(i, j));
                    }
                }
               
                PairValues2 x = robot.minValue(temp, -9999999, 9999999, 0, 10);  // Gain a move value/pair.  //If runs too slow, change 10 to something lower. 
                System.out.println("Calculated number: " + robot.getTotalMoves());  //Print total moves calculated. 
                System.out.println("Game state score: " + x.getValue());
                robot.resetMoves(); //Reset move counter.
                move = x.getPosition() + 1;  //Make a move set that's 0-6 to 1-7 for easier input. 
            }else{  // Get player move. 
                 String command = keyreader.next();
                 if(command.equalsIgnoreCase("quit")) break;  
                 move = Integer.parseInt(command);
            }
            
            while(move < 1 || move > 7 || !game.makeMove(move-1, playerStatus)){   //Check for valid moves. 
                System.out.println("Your move is off the board. Please try again. Valid moves are 1-7");
                move = keyreader.nextInt();
                        
            }
            game.printBoard(); // Show board. 
            
            if(playerStatus ==1) playerStatus = 2;           
            else if(playerStatus ==2) playerStatus = 1;
            
        }
        int winner = game.checkWinner(); //Calc winner. 
        
        if(winner == 2){
            System.out.println("The robot won");
        }else{
            System.out.println("The player won");
        }
        

    }
    
}
