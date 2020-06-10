package application.mode;

import application.MainCanvas;
import application.Port;
import application.Point;
import application.line.AssociationLine;
import application.rect.RectObject;
import application.UMLObject;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.MouseEvent;

public class AssociationMode extends BaseMode {

	private Port start;
	
	public AssociationMode(MainCanvas main) {
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
				if(start != end)
					objects.add(new AssociationLine(start, end));
				main.paint();
			}
		}
		start = null;
	}

}
