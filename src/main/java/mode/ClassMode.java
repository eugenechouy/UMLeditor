package application.mode;

import application.MainCanvas;
import application.object.ClassObject;
import javafx.scene.input.MouseEvent;

public class ClassMode extends BaseMode{
	
	private double startX, startY;
	
	public ClassMode(MainCanvas main) {
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
		frontPaintBrush.strokeRectangle(sX, sY, distX, distY);
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
		backPaintBrush.strokeRectangle(sX, sY, distX, distY);
		object.add(new ClassObject(sX, sY, distX, distY, main.getUpperMostInArea(sX, sY, distX, distY)));
	}
}
