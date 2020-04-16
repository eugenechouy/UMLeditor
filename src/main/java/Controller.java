package application;

import application.mode.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;

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
	}
}