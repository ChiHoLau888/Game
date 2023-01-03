/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.W;

/**
 *
 * @author abina
 */
public class Player extends EntityBase{
    private int lives = 3;
    private boolean blocking=false;
    private int blockDirection;
    /*
     * Returns the boolean value of the blocking variable
     */
    public boolean isBlocking() {
        return blocking;
    }
    /*
     * Changes the boolean value of the blocking variable
     */
    public void setBlocking(boolean blocking) {
        this.blocking = blocking;

    }
    /*
     * Changes the boolean value of the blocking variable and sets the direction of the block
     */
    public void setBlockingStatus(KeyCode code) {
        if (code.equals(A)) {
            blocking=true;
            blockDirection=1;
            System.out.println("left");
        }
        if (code.equals(D)) {
            blocking=true;
            blockDirection=2;
            System.out.println("right");
        }
        if (code.equals(W)) {
            blocking=true;
            blockDirection=0;
            System.out.println("up");
        }
    }
    /*
     * Returns the direction of where the player is blocking
     */
    public int getBlockDirection() {
        return blockDirection;
    }
    /*
     * Returns the direction of where the player is blocking
     */
    public void setBlockDirection(int blockDirection) {
        this.blockDirection = blockDirection;
    }
    
    /*
     * Constructor for the enemy class
     */
    public Player(int height, int length, int xPosition, int yPosition) {
        super(height, length, xPosition, yPosition);
    }
    /*
     * Returns remaining lives of the player
     */
    public int getLives() {
        return lives;
    }
    /*
     * Changes the value of the players life
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
    
}
