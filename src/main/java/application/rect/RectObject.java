package application.rect;

import application.Point;
import application.Port;
import application.PaintBrush;
import application.UMLObject;

import java.util.*;

public abstract class RectObject extends UMLObject {
	
	protected Point start = new Point();
	protected double width, 
					 height;
	protected Port[] ports = new Port[4];
	
	public RectObject(double x, double y, double width, double height, String name) {
		super();
		this.start.x = x;
		this.start.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		
		double[] dx = { width/2, width, width/2, 0 },
				 dy = { 0, height/2, height, height/2 };
		for(int i=0 ; i<4 ; ++i)
			ports[i] = new Port(x + dx[i], y + dy[i], this);
	}
	
	public Port getClosestPort(Point clicked) {
		int ret = 0;
		double min = 1000000.0;
		for(int i=0 ; i<4 ; i++) {
			double u = clicked.distance(ports[i].getPosition());
			if(u < min) {
				min = u;
				ret = i;
			} 
		}
		return ports[ret];
	}

	@Override
	public abstract void draw(PaintBrush paintBrush);

	@Override
	public void move(double distX, double distY) {
		start.x += distX;
		start.y += distY;
		for(int i=0 ; i<4 ; ++i)
			ports[i].move(distX, distY);
	}

	@Override
	public boolean isInside(double x, double y) {
		if ( (x >= start.x && x <= start.x + width) &&
			 (y >= start.y && y <= start.y + height ))
			return true;
		return false;
	}

	@Override
	public boolean isCover(double sX, double sY, double width, double height) {
		if ( this.start.x >= sX &&
		     this.start.y >= sY &&
		     this.start.x + this.width <= sX + width &&
		     this.start.y + this.height <= sY + height )
			return true;
		return false;
	}
}
