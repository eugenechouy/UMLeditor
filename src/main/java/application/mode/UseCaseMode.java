package application.mode;

import application.MainCanvas;
import application.object.UseCaseObject;
import javafx.scene.input.MouseEvent;

public class UseCaseMode extends BaseMode {
	
	private final double width = 100.0,
						 height = 65.0;

	public UseCaseMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event)  {
		object.add(new UseCaseObject(event.getX(), event.getY(), width, height, main.getUpperMostInArea(event.getX(), event.getY(), width, height)));
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
