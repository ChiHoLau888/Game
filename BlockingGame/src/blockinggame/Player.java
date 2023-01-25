/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.W;
import javafx.util.Duration;

/**
 *
 * @author abina
 */
public class Player extends EntityBase {

    private int lives = 3;
    private boolean blocking = false;
    private int blockDirection;
    private Timeline stopBlocking = new Timeline();
    private int timeSeconds = 0;

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
            blocking = true;
            blockDirection = 1;
            System.out.println("left");
            stopBlocking.playFromStart();
        }
        if (code.equals(D)) {
            blocking = true;
            blockDirection = 2;
            System.out.println("right");
            stopBlocking.playFromStart();
        }
        if (code.equals(W)) {
            blocking = true;
            blockDirection = 0;
            System.out.println("up");
            stopBlocking.playFromStart();
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
        createBlockTimer();
        Image playerImg = new Image(BlockingGame.class.getResourceAsStream("BlockingGame/Player.png")) ;
        Image playerImage = new Image(new FileInputStream("url for the image"));
        super.getEntity().setFill(new ImagePattern(playerImg));
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

    private void createBlockTimer() {
        stopBlocking.setCycleCount(1);
        stopBlocking.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.7),
                        new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        timeSeconds++;
                        if (timeSeconds == 1) {
                            blocking = false;
                            blockDirection = 4;
                            timeSeconds = 0;
                        }
                    }
                }));
    }

    public void stopBlockTimer() {
        stopBlocking.stop();
    }

}
