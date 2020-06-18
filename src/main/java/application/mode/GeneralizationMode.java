package application.mode;

import java.util.*;

import application.Port;
import application.Point;
import application.MainCanvas;
import application.line.LineObject;
import application.line.GeneralizationLine;
import application.rect.RectObject;
import application.UMLObject;

import javafx.scene.input.MouseEvent;

public class GeneralizationMode extends BaseMode {

	private Port start;
	
	public GeneralizationMode() {
		super();
	}
	
	@Override
	public void pressedAction(MouseEvent event) {
		UMLObject upperMost = main.getClickedObject(event.getX(), event.getY());
		start = upperMost.getClosestPort(new Point(event.getX(), event.getY()));
	}

	@Override
	public void draggedAction(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void releasedAction(MouseEvent event) {
		if(start != null) {
			UMLObject upperMost = main.getClickedObject(event.getX(), event.getY());
			Port end = upperMost.getClosestPort(new Point(event.getX(), event.getY()));
			if(end != null && start.getParent() != end.getParent()) {
				LineObject newline = new GeneralizationLine(start, end);
				objects.add(newline);
				start.addLine(newline);
				end.addLine(newline);
			}
			main.paint();
		}
		start = null;
	}

}
