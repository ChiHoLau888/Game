/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import static blockinggame.Game.ENEMYSIZE;
import static blockinggame.Game.SCREENHEIGHT;

/**
 *
 * @author abina
 */
public class Projectile extends EntityBase {

    private int dx = 5;
    private int dy = 5;
    private int direction;

    /*
     * Constructor for the enemy class
     */
    public Projectile(int height, int length, int xPosition, int yPosition, int direction) {
        super(height, length, xPosition, yPosition);
        this.direction = direction;
    }



    /*
     * Returns value of projectiles speed in the x axis
     */
    public int getDx() {
        return dx;
    }

    /*
     * Changes the value of the projectiles speed in the x axis
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /*
     * Returns value of projectiles speed in the y axis
     */
    public int getDy() {
        return dy;
    }

    /*
     * Changes the value of the projectiles speed in the y axis
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /*
     * Returns value of projectiles direction
     */
    public int getDirection() {
        return direction;
    }

    /*
     * Sets the projectiles direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

}
