package application.line;

import application.Port;
import application.Point;
import application.UMLObject;
import application.util.PaintBrush;

public abstract class LineObject extends UMLObject {
	
	protected Port start;
	protected Port end;
	
	protected double arrowHeadSize = 10.0;
	
	public LineObject(Port start, Port end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public abstract void draw(PaintBrush paintBrush);

	@Override
	public void move(double distX, double distY){
		// nothing
	}

	@Override
	public boolean isInside(double x, double y) {
		// line dont need to be clicked yet
		return false;
	}

	@Override
	public boolean isCover(double sX, double sY, double width, double height) {
		Point port1 = start.getPosition(),
			  port2 = end.getPosition();
		if(port1.x >= sX && port1.x <= sX + width &&
		   port1.y >= sY && port1.y <= sY + height && 
		   port2.x >= sX && port2.x <= sX + width &&
		   port2.y >= sY && port2.y <= sY + height)
		   return true;
		return false;
	}
}
