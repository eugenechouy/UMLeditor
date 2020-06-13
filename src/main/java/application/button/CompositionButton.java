package application.button;

import application.mode.BaseMode;
import application.mode.CompositionMode;
import application.MainCanvas;

import javafx.scene.control.Button;

public class CompositionButton extends Button {

    public CompositionButton() {
        super("Composition Line");
        BaseMode compositionmode = new CompositionMode();
        setOnAction(e -> {
            MainCanvas.getInstance().setMode(compositionmode);
        });
    }
}