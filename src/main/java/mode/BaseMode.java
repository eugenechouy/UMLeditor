package application.mode;

import java.util.List;

import application.MainCanvas;
import application.PaintBrush;
import application.object.UMLObject;
import application.object.UMLLine;

import javafx.scene.input.MouseEvent;

public abstract class BaseMode {
	
	protected PaintBrush frontPaintBrush, backPaintBrush;
	protected String mode;
	protected MainCanvas main;
	
	List<UMLLine> lines;
	List<UMLObject> object;
	
	public BaseMode(MainCanvas main) {
		this.main = main;
		this.frontPaintBrush = main.getFrontPaintBrush();
		this.backPaintBrush = main.getBackPaintBrush();
		this.object = main.getObject();
		this.lines = main.getLines();
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public abstract void pressedAction(MouseEvent event);
	public abstract void draggedAction(MouseEvent event);
	public abstract void releasedAction(MouseEvent event);
}
