package application.mode;

import java.util.ArrayList;
import java.util.List;

import application.MainCanvas;
import application.Point;
import application.line.CompositionLine;
import application.object.UMLObject;
import javafx.scene.input.MouseEvent;

public class CompositionMode extends BaseMode {

	private UMLObject start;
	private int startPort;
	
	public CompositionMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event) {
		List<Integer> candidate = new ArrayList<>();
		for(int i=0 ; i<object.size() ; i++) {
			if( object.get(i).cover(event.getX(), event.getY()) )
				candidate.add(i);
		}
		if(candidate.size() > 0) {
			int top = -1;
			Integer upperMost = candidate.get(0);
			for(int i=0 ; i<candidate.size(); i++) {
				if( object.get(i).getDepth() >= top ) {
					top = object.get(i).getDepth();
					upperMost = candidate.get(i);
				}	
			}
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
			List<Integer> candidate = new ArrayList<>();
			for(int i=0 ; i<object.size() ; i++) {
				if( object.get(i).cover(event.getX(), event.getY()) )
					candidate.add(i);
			}
			if(candidate.size() > 0) {
				int top = -1;
				Integer upperMost = candidate.get(0);
				for(int i=0 ; i<candidate.size(); i++) {
					if( object.get(i).getDepth() >= top ) {
						top = object.get(i).getDepth();
						upperMost = candidate.get(i);
					}	
				}
				if(start != object.get(upperMost) && object.get(upperMost).getClosestPort(new Point(event.getX(), event.getY())) != -1)
					lines.add(new CompositionLine(start, startPort, 
							 					  object.get(upperMost), 
							 					  object.get(upperMost).getClosestPort(new Point(event.getX(), event.getY()))
							 					  ));
				main.rePaint();
			}
			start = null;
		}
	}

}
