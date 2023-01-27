package blockinggame;

import javafx.scene.paint.Color;

/**
 *
 * @author abina
 */
public class Enemy extends EntityBase{
    /*
     * Constructor for the enemy class
     */
    public Enemy(int height, int length, int xPosition, int yPosition) {
        super(height, length, xPosition, yPosition);
        super.setColour(Color.GREY);
        
    }
    
}
