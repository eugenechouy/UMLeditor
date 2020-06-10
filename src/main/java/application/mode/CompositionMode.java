package application.mode;

import java.util.ArrayList;
import java.util.List;

import application.MainCanvas;
import application.Port;
import application.Point;
import application.line.LineObject;
import application.line.CompositionLine;
import application.rect.RectObject;
import application.UMLObject;

import javafx.scene.input.MouseEvent;

public class CompositionMode extends BaseMode {

	private Port start;
	
	public CompositionMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event) {
		UMLObject upperMost = main.getClickedObject(event.getX(), event.getY());
		if(upperMost instanceof RectObject)
			start = ((RectObject)upperMost).getClosestPort(new Point(event.getX(), event.getY()));
	}

	@Override
	public void draggedAction(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void releasedAction(MouseEvent event) {
		if(start != null) {
			UMLObject upperMost = main.getClickedObject(event.getX(), event.getY());
			if(upperMost instanceof RectObject) {
				Port end = ((RectObject)upperMost).getClosestPort(new Point(event.getX(), event.getY()));
				if(start != end){
					LineObject newline = new CompositionLine(start, end);
					objects.add(newline);
					start.addLine(newline);
					end.addLine(newline);
				}
				main.paint();
			}
		}
		start = null;
	}

}
