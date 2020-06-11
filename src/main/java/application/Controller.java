package application;

import java.net.URL;
import java.util.*;

import application.mode.AssociationMode;
import application.mode.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

public class Controller implements Initializable {

	@FXML 
	private ArrayList<Button> buttonList;

	@FXML
	private Canvas frontCanvas, backCanvas;
	
	@FXML
	private MenuItem group, ungroup, change_object_name;

	private MainCanvas mainCanvas;
	private ArrayList<BaseMode> modes = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainCanvas = new MainCanvas(frontCanvas, backCanvas);
		modes.add(new SelectMode(mainCanvas));
		modes.add(new AssociationMode(mainCanvas));
		modes.add(new GeneralizationMode(mainCanvas));
		modes.add(new CompositionMode(mainCanvas));
		modes.add(new ClassMode(mainCanvas));
		modes.add(new UseCaseMode(mainCanvas));
		setButtonEvent();
	}

	private void setButtonEvent() {
		for(int i=0 ; i<buttonList.size() ; ++i){
			final int index = i;
			buttonList.get(i).setOnAction(e -> {
				mainCanvas.setMode(modes.get(index));
			});
		}
		
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
