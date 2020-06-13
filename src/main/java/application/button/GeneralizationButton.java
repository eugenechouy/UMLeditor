package application.button;

import application.mode.BaseMode;
import application.mode.GeneralizationMode;
import application.MainCanvas;

import javafx.scene.control.Button;

public class GeneralizationButton extends Button {

    public GeneralizationButton() {
        super("Generalization Line");
        BaseMode generalizationmode = new GeneralizationMode();
        setOnAction(e -> {
            MainCanvas.getInstance().setMode(generalizationmode);
        });
    }
}