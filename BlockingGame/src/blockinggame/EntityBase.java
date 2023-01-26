/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author abina
 */
public class EntityBase {

    private int height;
    private int length;
    private int xPosition;
    private int yPosition;
    final private Rectangle entity;
    private Color colour;

    /*
     * Constructor for the super class of all objects
     */
    public EntityBase(int height, int length, int xPosition, int yPosition) {
        this.height = height;
        this.length = length;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        entity = new Rectangle(xPosition, yPosition, height, length);
        entity.setFill(Color.BURLYWOOD);

    }

    /*
     * Returns value of objects height
     */
    public int getHeight() {
        return height;
    }

    /*
     * Returns value of objects length
     */
    public int getLength() {
        return length;
    }

    /*
     * Returns value of objects position of the x axis
     */
    public int getxPosition() {
        return xPosition;
    }

    /*
     * Returns value of objects position of the y axis
     */
    public int getyPosition() {
        return yPosition;
    }

    /*
     * Changes the value of the objects height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /*
     * Changes the value of the objects length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /*
     * Changes the value of the objects position in the x axis
     */
    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /*
     * Changes the value of the objects position in the x axis
     */
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /*
     * Returns rectangle of the object
     */
    public Rectangle getEntity() {
        return entity;
    }

    /*
     * Changes the x location of the object
     */
    public void setEntityXPosition(int xPosition) {
        this.xPosition=xPosition;
        entity.setX(xPosition);
    }
    /*
     * Changes the y location of the object
     */
    public void setEntityYPosition(int yPosition) {
        this.yPosition=yPosition;
        entity.setY(yPosition);
    }
    /*
     * Returns the colour of the object
     */
    public Color getColour() {
        return colour;
    }
    /*
     * Changes the colour of the object
     */
    public void setColour(Color colour) {
        this.colour = colour;
        entity.setFill(colour);
    }
    /*
     * Changes the opacity based on if it should increase or decrease
     */
    public void changeOpacity(boolean yes) {
        if (yes) {
            entity.opacityProperty().set(0.5);
        }
        else{
            entity.opacityProperty().set(1);
        }
    }


}
