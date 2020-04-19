package application.mode;

import application.MainCanvas;
import application.object.ClassObject;
import javafx.scene.input.MouseEvent;

public class ClassMode extends BaseMode{
	
	private final double width = 100.0,
						 height = 65.0;
	
	public ClassMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event)  {
		object.add(new ClassObject(event.getX(), event.getY(), width, height, main.getUpperMostInArea(event.getX(), event.getY(), width, height)));
		main.rePaint();
	}
	
	@Override
	public void draggedAction(MouseEvent event) {
		// nothing
	}
	
	@Override
	public void releasedAction(MouseEvent event) {
		// nothing
	}
}
