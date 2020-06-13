package application.mode;

import java.util.List;

import application.MainCanvas;
import application.UMLObject;
import application.util.PaintBrush;

import javafx.scene.input.MouseEvent;

public abstract class BaseMode {
	
	protected PaintBrush frontPaintBrush, backPaintBrush;
	protected MainCanvas main;
	
	protected List<UMLObject> objects;

	public BaseMode() {
		this.main = MainCanvas.getInstance();
		this.frontPaintBrush = main.getFrontPaintBrush();
		this.backPaintBrush = main.getBackPaintBrush();
		this.objects = main.getObject();
	}

	public void group() {};
	public void unGroup() {};
	public void changeObjectName() {};
	public abstract void pressedAction(MouseEvent event);
	public abstract void draggedAction(MouseEvent event);
	public abstract void releasedAction(MouseEvent event);
}
