package application.mode;

import java.util.List;

import application.MainCanvas;
import application.PaintBrush;
import application.object.UMLObject;
import application.line.UMLLine;

import javafx.scene.input.MouseEvent;

public abstract class BaseMode {
	
	protected PaintBrush frontPaintBrush, backPaintBrush;
	protected MainCanvas main;
	
	protected List<UMLLine> lines;
	protected List<UMLObject> object;

	public BaseMode(MainCanvas main) {
		this.main = main;
		this.frontPaintBrush = main.getFrontPaintBrush();
		this.backPaintBrush = main.getBackPaintBrush();
		this.object = main.getObject();
		this.lines = main.getLines();
	}

	public void group() {};
	public void unGroup() {};
	public void changeObjectName() {};
	public abstract void pressedAction(MouseEvent event);
	public abstract void draggedAction(MouseEvent event);
	public abstract void releasedAction(MouseEvent event);
}
