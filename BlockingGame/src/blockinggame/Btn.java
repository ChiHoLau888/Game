/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import javafx.scene.control.Button;
/**
 *
 * @author abina
 */
public class Btn {
    
    private Button btn = new Button();
    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public Btn() {
        setcolour();
    }

    private void setcolour() {
        btn.setMinSize(150, 50);
        btn.setStyle("-fx-border-color: #ffffff;-fx-background-color: #000000;-fx-text-fill: #ffffff;-fx-font-size: 2em;-fx-font-family:monospace;-fx-font-weight:bold;");
    }

    void setY(int i) {
        btn.setLayoutY(i);
    }

    void setX(int i) {
        btn.setLayoutX(i);
    }

    void Text(String text) {
        btn.setText(text);
    }
}
