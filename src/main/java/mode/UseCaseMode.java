package application.mode;

import application.MainCanvas;
import application.object.UseCaseObject;
import javafx.scene.input.MouseEvent;

public class UseCaseMode extends BaseMode {
	
	private double startX, startY;
	
	public UseCaseMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event)  {
		startX = event.getX();
		startY = event.getY();
	}
	
	@Override
	public void draggedAction(MouseEvent event) {
		double distX = event.getX()-startX, distY = event.getY()-startY;
	    double sX = startX, sY = startY;
		if(distX < 0) {
			sX += distX;
			distX = Math.abs(distX);
		} 
		if(distY < 0) {
			sY += distY;
			distY = Math.abs(distY);
		}
		frontPaintBrush.clear();
		frontPaintBrush.strokeOval(sX, sY, distX, distY);
	}
	
	@Override
	public void releasedAction(MouseEvent event) {
		double distX = event.getX()-startX, distY = event.getY()-startY;
	    double sX = startX, sY = startY;
		if(distX < 0) {
			sX += distX;
			distX = Math.abs(distX);
		} 
		if(distY < 0) {
			sY += distY;
			distY = Math.abs(distY);
		}
		frontPaintBrush.clear();
		backPaintBrush.strokeOval(sX, sY, distX, distY);
		object.add(new UseCaseObject(sX, sY, distX, distY, main.getUpperMostInArea(sX, sY, distX, distY)));
	}
}
