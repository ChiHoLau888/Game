/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import javafx.scene.paint.Color;

/**
 *
 * @author abina
 */
public class Enemy extends EntityBase{
    private int transparancy;
    /*
     * Constructor for the enemy class
     */
    public Enemy(int height, int length, int xPosition, int yPosition) {
        super(height, length, xPosition, yPosition);
        transparancy=50;
        super.setColour(Color.GREY);
        
    }
    
}
