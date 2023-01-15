/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import static blockinggame.Game.SCREENHEIGHT;
import static blockinggame.Game.SCREENWIDTH;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author abina
 */
public class AfterScreen {

    private final Rectangle coverScreen;
    private final Btn returnBtn;
    private Group root;
    private Scene scene;
    private boolean ifWin;

    public AfterScreen(int length, int height, boolean ifWin, Group root, Scene scene) {
        coverScreen = new Rectangle(0, 0, length, height);
        coverScreen.setOpacity(0.7);
        returnBtn = new Btn();
        returnBtn.setX((length / 2) - 75);
        returnBtn.setY((height / 2) + 200);
        this.ifWin = ifWin;
        this.root = root;
        this.scene = scene;
    }

    public void endGame() {
        root.getChildren().add(coverScreen);
        root.getChildren().add(returnBtn.getBtn());
        if (ifWin) {

        } else {

        }
    }
}
