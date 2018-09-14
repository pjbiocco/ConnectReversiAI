
package connectreversi2;

/**
 *
 * @author Paul Biocco
 */
public class PairValues2 {
    private int position;
    private int score; 
    
    public PairValues2(int x, int y){
        position = x; 
        score = y;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return score;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.score = value;
    }
}
