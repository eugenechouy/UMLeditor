package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.mode.AssociationMode;
import application.mode.ClassMode;
import application.mode.CompositionMode;
import application.mode.GeneralizationMode;
import application.mode.SelectMode;
import application.mode.UseCaseMode;
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
	private Button select_btn,
				   association_btn,
				   generalization_btn,
				   composition_btn,
				   class_btn,
				   use_case_btn;
	
	@FXML
	private Canvas frontCanvas, backCanvas;
	
	@FXML
	private MenuItem group, ungroup;

	private MainCanvas mainCanvas;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainCanvas = new MainCanvas(frontCanvas, backCanvas);
		setButtonEvent();
	}

	private void setButtonEvent() {
		select_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainCanvas.setMode(new SelectMode(mainCanvas));
			}
		});
		
		class_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainCanvas.setMode(new ClassMode(mainCanvas));
			}
		});
		
		use_case_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainCanvas.setMode(new UseCaseMode(mainCanvas));
			}
		});
		
		association_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainCanvas.setMode(new AssociationMode(mainCanvas));
			}
		});
		
		generalization_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainCanvas.setMode(new GeneralizationMode(mainCanvas));
			}
		});
		
		composition_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainCanvas.setMode(new CompositionMode(mainCanvas));
			}
		});

		group.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainCanvas.group();
			}
		});

		ungroup.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainCanvas.unGroup();
			}
		});
	}
}
