
package connectreversi2;

/**
 *
 * @author Paul Biocco
 */
public class GameAI2 {
    
    private int totalMovesCalced;
    private int[] moveScores;
    
    public GameAI2(){
       totalMovesCalced = 0; 
       moveScores = new int[7];
    }
    
    public int getTotalMoves(){
        return totalMovesCalced;
    }
    public void resetMoves(){
        totalMovesCalced = 0;
    }
    
    public PairValues2 maxValue(GameBoard2 x, int alpha, int beta, int depth, int maxDepth){  //Gets the max value move. Returns the optimal move with the value of the move. 
        int optimalMove = -1;  //Best move. Will be overridden. 

        
        totalMovesCalced++;   //Only for show. 
        if(depth >= maxDepth || x.isGameDone()){  //Check for terminal state. Terminal state = leafnode or 

            PairValues2 pv = new PairValues2(-1, AIValue(x));
            return pv;
        }
        int value = -9999999;  //Worst max value. Negative infinity. 
        for(int i = 0; i < x.getBoard().length; i++){  //Check for all possible moves. 
            GameBoard2 temp = new GameBoard2();  //Copy so edits can be made without effecting the main board. 
            for(int a = 0; a < 7; a++){ 
                for(int b = 0; b < 7; b++){
                    temp.setLocation(a, b, x.getSymbol(a, b));
                }
            } 
            if(temp.getSymbol(i, 6) == " "){   //Only check valid moves. (i, 6) = top row at collumn i. 
                temp.makeMove(i, 1);    //Move as player 1. 
                PairValues2 tempValue = minValue(temp, alpha, beta, depth+1, maxDepth); //Go to min. 
   
                if(tempValue.getValue() > value) {  //Calculate optimal move. 
                    value = tempValue.getValue();
                    optimalMove = i;  
                }
                if(tempValue.getValue() >= beta){ //Alpha-beta pruning. 
                    PairValues2 optimal = new PairValues2(optimalMove, value);
                    return optimal;
                }
                if(tempValue.getValue() > alpha) alpha = tempValue.getValue(); 
                
            }
        }
        PairValues2 pv = new PairValues2(optimalMove,value);  
        return pv; //Return the optimal move ojbject. 2
    }
    
    public PairValues2 minValue(GameBoard2 x, int alpha, int beta, int depth, int maxDepth){ //Gets the min value move. 
         int optimalMove = -1;
         totalMovesCalced++;
         if(depth >= maxDepth || x.isGameDone()){
            GameBoard2 temp = new GameBoard2();
            for(int a = 0; a < 7; a++){
                for(int b = 0; b < 7; b++){
                    temp.setLocation(a, b, x.getSymbol(a, b));
                }
            }
            PairValues2 pv = new PairValues2(-1, AIValue(temp));
            return pv;
        }
        int value = 9999999;
        for(int i = 0; i < x.getBoard().length; i++){
            GameBoard2 temp = new GameBoard2();
            for(int a = 0; a < 7; a++){
                for(int b = 0; b < 7; b++){
                    temp.setLocation(a, b, x.getSymbol(a, b));
                }
            }
            if(temp.getSymbol(i, 6) == " "){
                temp.makeMove(i, 2);            //Move as player 2.
                PairValues2 tempValue = maxValue(temp, alpha, beta, depth+1, maxDepth);
                if(tempValue.getValue() < value){
                    value = tempValue.getValue();
                    optimalMove = i;
                }
                if(tempValue.getValue() <= alpha){
                    PairValues2 optimal = new PairValues2(optimalMove,value);
                    return optimal;
                }
                if(tempValue.getValue() < beta) beta = tempValue.getValue(); 
            }
                    
        }
        PairValues2 pv = new PairValues2(optimalMove,value);
        return pv;
    }
    
    public int AIValue(GameBoard2 x){  //General heuristics go here. This one goes by total pieces. 
        
       int bot = 0;
       int player = 0;
       
       
       for(int i = 0; i < x.getBoard().length; i++){
           for(int j = 0; j < x.getBoard().length; j++){
               
               if(i == 0 || i == 6){
                   bot++;
                   player++;
               }
               if(j == 7 && (i == 0 || i == 6)){
                   bot = bot + 2;
                   player = player + 2;
               }
               
               if(j == 0){
                    
                    if(i == 0 || i == 6){
                         if(x.getSymbol(i, j) == "X") bot = bot + 4;
                         if(x.getSymbol(i, j) == "O") player = player + 4;
                    }
                    else{
                         if(x.getSymbol(i, j) == "X") bot = bot + 2;
                         if(x.getSymbol(i, j) == "O") player = player + 2;
                    }
               }else{
                    if(x.getSymbol(i, j) == "X") bot = bot + 1;
                    if(x.getSymbol(i, j) == "O") player = player+ 1;
               }
           }
       } 
       return bot - player;
    }
}
