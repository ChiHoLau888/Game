/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author abina
 */
public class BlockingGame extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        new Game(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
