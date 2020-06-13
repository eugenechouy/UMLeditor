package application.button;

import application.mode.BaseMode;
import application.mode.ClassMode;
import application.MainCanvas;

import javafx.scene.control.Button;

public class ClassButton extends Button {

    public ClassButton() {
        super("Class");
        BaseMode classmode = new ClassMode();
        setOnAction(e -> {
            MainCanvas.getInstance().setMode(classmode);
        });
    }
}