package application.button;

import application.mode.BaseMode;
import application.mode.SelectMode;
import application.MainCanvas;

import javafx.scene.control.Button;

public class SelectButton extends Button {

    public SelectButton() {
        super("Select");
        BaseMode selectmode = new SelectMode();
        setOnAction(e -> {
            MainCanvas.getInstance().setMode(selectmode);
        });
    }
}