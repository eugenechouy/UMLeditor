package application.mode;

import application.MainCanvas;
import application.Point;
import application.object.AssociationLine;
import application.object.UMLObject;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.MouseEvent;

public class AssociationMode extends BaseMode {

	private UMLObject start;
	private int startPort;
	
	public AssociationMode(MainCanvas main) {
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
			start = object.get(upperMost);
			startPort = start.getClosestPort(new Point(event.getX(), event.getY()));
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
				if(start != object.get(upperMost))
					lines.add(new AssociationLine(start, startPort, 
							 					  object.get(upperMost), 
							 					  object.get(upperMost).getClosestPort(new Point(event.getX(), event.getY()))
							 					  ));
				main.rePaint();
			}
			start = null;
		}
	}

}
