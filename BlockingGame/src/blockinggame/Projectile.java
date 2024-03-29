package blockinggame;

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
     * Returns the value of projectiles speed in the x axis
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
     * Returns the value of projectiles direction
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
