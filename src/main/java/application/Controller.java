package application;

import java.net.URL;
import java.util.*;

import application.button.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class Controller implements Initializable {

	@FXML
	private VBox taskbar;

	@FXML
	private Canvas frontCanvas, backCanvas;
	
	@FXML
	private MenuItem group, ungroup, change_object_name;

	private MainCanvas mainCanvas;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainCanvas = MainCanvas.getInstance();
		mainCanvas.registerCanvas(frontCanvas, backCanvas);
		registerButton();
		registerMenuEvent();
	}

	private void registerButton() {
		Button selectbutton = new SelectButton();
		Button assoicationbutton = new AssociationButton();
		Button generalizationbutton = new GeneralizationButton();
		Button compositionbutton = new CompositionButton();
		Button classbutton = new ClassButton();
		Button usecasebutton = new UseCaseButton();
	
		taskbar.getChildren().addAll(selectbutton, 
									 assoicationbutton, 
									 generalizationbutton, 
									 compositionbutton, 
									 classbutton, 
									 usecasebutton);
	}

	private void registerMenuEvent() {
		group.setOnAction(e -> {
			mainCanvas.group();
		});

		ungroup.setOnAction(e -> {
			mainCanvas.unGroup();
		});

		change_object_name.setOnAction(e -> {
			mainCanvas.changeObjectName();
		});
	}
}
