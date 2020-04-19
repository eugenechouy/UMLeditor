package application.mode;

import java.util.ArrayList;
import java.util.List;

import application.MainCanvas;
import application.Point;
import application.line.GeneralizationLine;
import application.object.UMLObject;
import javafx.scene.input.MouseEvent;

public class GeneralizationMode extends BaseMode {

	private UMLObject start;
	private int startPort;
	
	public GeneralizationMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event) {
		int upperMost = main.getClickedObject(event.getX(), event.getY());
		if(upperMost != -1){
			startPort = object.get(upperMost).getClosestPort(new Point(event.getX(), event.getY()));
			if(startPort != -1)
				start = object.get(upperMost);
		}
	}

	@Override
	public void draggedAction(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void releasedAction(MouseEvent event) {
		if(start != null) {
			int upperMost = main.getClickedObject(event.getX(), event.getY());
			if(upperMost != -1){
				UMLObject end = object.get(upperMost);
				int endPort = end.getClosestPort(new Point(event.getX(), event.getY()));
				if(start != end && endPort != -1)
					lines.add(new GeneralizationLine(start, startPort, end, endPort));
				main.rePaint();
			}
			start = null;
		}
	}

}
