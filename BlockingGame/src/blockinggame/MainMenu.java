
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author abina
 */
public class MainMenu {
    
    Btn btn1 = new Btn();
    Btn btn2 = new Btn();
    Btn btn3 = new Btn();
    Group root1 = new Group();
    Scene scene = new Scene(root1, 1000, 540); 
    Label label;

    public  MainMenu(Stage primaryStage){
        setText();
        setLayout();
        btn1.getBtn().setOnAction(event -> new Game(primaryStage));
        btn2.getBtn().setOnAction(event -> Platform.exit());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        scene.setFill(Color.DARKSLATEGRAY);
        primaryStage.setX(screenSize.getWidth() / 2 - 500);
        primaryStage.setY((screenSize.getHeight() / 2) - 270);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void setText() {
        label = new Label("Blocking Game");
        btn1.Text("Start");
        btn2.Text("Exit");
    }

    /*
     * Sets layout for everything in the main menu
     */
    private void setLayout() {
        btn1.setY(270);
        btn2.setY(340);
        btn1.setX(425);
        btn2.setX(425);
        label.setLayoutX(375);
        label.autosize();
        label.setPrefSize(400, 200);
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        root1.getChildren().add(btn1.getBtn());
        root1.getChildren().add(btn2.getBtn());
        root1.getChildren().add(label);
    }
}
