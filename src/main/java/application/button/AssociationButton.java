package application.button;

import application.mode.BaseMode;
import application.mode.AssociationMode;
import application.MainCanvas;

import javafx.scene.control.Button;

public class AssociationButton extends Button {

    public AssociationButton() {
        super("Association Line");
        BaseMode associationmode = new AssociationMode();
        setOnAction(e -> {
            MainCanvas.getInstance().setMode(associationmode);
        });
    }
}