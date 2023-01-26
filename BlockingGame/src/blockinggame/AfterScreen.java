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
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * $files = Get-ChildItem -Path "\\personalcloud\elmert\CCTV\Rover" -Recurse |
 * Where-Object { $_.LastWriteTime -lt (Get-Date).AddDays(-10) }
 * $files = Get-ChildItem -Path "\\personalcloud\elmert\CCTV\Rover" -Recurse |
 * `Where-Object { $_.LastWriteTime -lt (Get-Date).AddDays(-10) }
 * 
 * @author abina
 */
public class AfterScreen {

    private final Rectangle coverScreen;
    private final Btn returnBtn;
    private Group root;
    private Scene scene;
    private boolean ifWin;
    private Stage primaryStage;
    int length;
    int height;
    Label label;

    public AfterScreen(int length, int height, boolean ifWin, Group root, Scene scene, Stage primaryStage) {
        coverScreen = new Rectangle(0, 0, length, height);
        coverScreen.setOpacity(0.7);
        returnBtn = new Btn();
        returnBtn.setX((length / 2) - 75);
        returnBtn.setY((height / 2) + 200);
        this.ifWin = ifWin;
        this.root = root;
        this.scene = scene;
        this.primaryStage = primaryStage;
        this.length = length;
        this.height = height;
    }

    public void endGame() {
        root.getChildren().add(coverScreen);
        root.getChildren().add(returnBtn.getBtn());
        returnBtn.getBtn().setOnAction(event -> new MainMenu(primaryStage));
        if (ifWin) {
            label = new Label("You win");

            label.setLayoutX((length / 2) - 125);
            label.setLayoutY((height / 2) - 125);
            label.autosize();
            label.setPrefSize(900, 200);
            label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        } else {
            label = new Label("You lose");

            label.setLayoutX((length / 2) - 125);
            label.setLayoutY((height / 2) - 125);
            label.autosize();
            label.setPrefSize(900, 200);
            label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        }
        root.getChildren().add(label);
    }
}
