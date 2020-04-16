package application;

import application.object.*;
import application.mode.*;

import java.util.*;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class MainCanvas {
	private Canvas frontCanvas, backCanvas;
	private PaintBrush frontPaintBrush, backPaintBrush;
	
	private BaseMode mode;
	
	private List<UMLObject> object = new ArrayList<>();
	private List<UMLLine> lines = new ArrayList<>();
	
	public void setMode(BaseMode mode) {
		this.mode = null;
		this.mode = mode;
	}
	
	public List<UMLLine> getLines(){
		return lines;
	}

	public List<UMLObject> getObject() {
		return object;
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
	
	public void rePaint() {
		backPaintBrush.eraser(0, 0, frontCanvas.getWidth(), frontCanvas.getHeight());
		for(int i=0 ; i<object.size() ; i++) 
			object.get(i).draw(backPaintBrush);
		for(int i=0 ; i<lines.size() ; i++)
			lines.get(i).draw(backPaintBrush);
	}
	
	public int getUpperMostInArea(double sX, double sY, double width, double height) {
		List<Integer> candidate = new ArrayList<>();
		for(int i=0 ; i<object.size() ; i++) {
			if(object.get(i).intercept(sX, sY, width, height))
				candidate.add(i);
		}
		int top = 0;
		for(int i=0 ; i<candidate.size(); i++) {
			int u = candidate.get(i).intValue();
			if( object.get(u).getDepth() > top ) 
				top = object.get(u).getDepth();
		}
		return candidate.size() == 0 ? 0 : top+1;
	}

	public void group(){
		mode.group();
	}
}
