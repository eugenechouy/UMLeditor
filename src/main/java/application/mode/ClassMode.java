package application.mode;

import application.MainCanvas;
import application.rect.ClassObject;
import javafx.scene.input.MouseEvent;

public class ClassMode extends BaseMode{
	
	private final double width = 100.0,
						 height = 65.0;
	
	public ClassMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event)  {
		objects.add(new ClassObject(event.getX(), event.getY(), width, height));
		main.paint();
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
