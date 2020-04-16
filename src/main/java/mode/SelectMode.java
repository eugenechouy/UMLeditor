package application.mode;

import application.MainCanvas;

import java.util.*;

import javafx.scene.input.MouseEvent;

public class SelectMode extends BaseMode {
	
	private double lastX, lastY;
	
	private List<Integer> selected = new ArrayList<>();
	
	public SelectMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event) {
		List<Integer> candidate = new ArrayList<>();
		selected.clear();
		for(int i=0 ; i<object.size() ; i++) {
			object.get(i).setSelect(false);
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
			object.get(upperMost).setSelect(true);
			selected.add(upperMost);
		}
		main.rePaint();
		lastX = event.getX();
		lastY = event.getY();
	}

	@Override
	public void draggedAction(MouseEvent event) {
		if(selected.size() > 0) {
			for(int i=0 ; i<selected.size() ; i++)
				object.get(selected.get(i)).move(event.getX() - lastX, event.getY() - lastY);
			main.rePaint();
		} 
		lastX = event.getX();
		lastY = event.getY();
	}
	
	@Override
	public void releasedAction(MouseEvent event) {
		
	}

}