package application;

import application.rect.*;
import application.line.*;
import application.mode.*;
import application.composite.*;

import java.util.*;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class MainCanvas {
	private Canvas frontCanvas, backCanvas;
	private PaintBrush frontPaintBrush, backPaintBrush;
	
	private BaseMode mode;
	
	private List<UMLObject> objects = new ArrayList<>();
		
	public void setMode(BaseMode mode) {
		this.mode = mode;
	}

	public List<UMLObject> getObject() {
		return objects;
	}
	
	public PaintBrush getFrontPaintBrush() {
		return frontPaintBrush;
	}
	
	public PaintBrush getBackPaintBrush() {
		return backPaintBrush;
	}
	
	public MainCanvas(Canvas front, Canvas back) {
		frontCanvas = front;
		backCanvas = back;
		frontPaintBrush = new PaintBrush(frontCanvas);
		backPaintBrush = new PaintBrush(backCanvas);
		mode = new SelectMode(this);
		paint();
		setCanvasEvent();
	}
	
	private void setCanvasEvent() {
		frontCanvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mode.pressedAction(event);
			}
		});
		
		frontCanvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mode.draggedAction(event);
			}
		});
		
		frontCanvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mode.releasedAction(event);
			}
		});
	}

	public void group(){
		mode.group();
	}

	public void unGroup(){
		mode.unGroup();
	}
	
	public void changeObjectName(){
		mode.changeObjectName();
	}
	
	public void paint() {
		backPaintBrush.eraser(0, 0, backCanvas.getWidth(), backCanvas.getHeight());
		for(int i=0 ; i<objects.size() ; ++i) 
			objects.get(i).draw(backPaintBrush);
		backPaintBrush.border();
	}

	public UMLObject getClickedObject(double x, double y){
		List<UMLObject> candidate = new ArrayList<>();
		for(int i=0 ; i<objects.size() ; ++i) {
			if( objects.get(i).isInside(x, y) )
				candidate.add(objects.get(i));
		}
		UMLObject clicked = null;
		if(candidate.size() > 0) {
			clicked = candidate.get(0);
			for(int i=0 ; i<candidate.size(); ++i) {
				if( candidate.get(i).getDepth() >= clicked.getDepth() ) 
					clicked = candidate.get(i);
			}
		}
		return clicked;
	}
	
}
