package application.button;

import application.mode.BaseMode;
import application.mode.UseCaseMode;
import application.MainCanvas;

import javafx.scene.control.Button;

public class UseCaseButton extends Button {

    public UseCaseButton() {
        super("UseCase");
        BaseMode usecasemode = new UseCaseMode();
        setOnAction(e -> {
            MainCanvas.getInstance().setMode(usecasemode);
        });
    }
}