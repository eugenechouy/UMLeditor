package application.object;

import application.Point;
import application.PaintBrush;

import java.util.*;

public abstract class UMLObject {
	
	protected Point start = new Point();
	protected double width, height;

	protected double portWidth = 10.0;
	
	protected int depth = 0;
	protected boolean Select = false; 

	protected String textField;
	
	public UMLObject() { 
	}
	
	public UMLObject(double x, double y, double width, double height, int depth, String textField) {
		this.start.x = x;
		this.start.y = y;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.textField = textField;
	}
	
	public void move(double distX, double distY) {
		this.start.x += distX;
		this.start.y += distY;
	}
	
	public void setSelect(boolean Select) {
		this.Select = Select;
	}
	
	public boolean cover(double x, double y) {
		if ( (x >= this.start.x && x <= this.start.x + this.width) &&
			 (y >= this.start.y && y <= this.start.y + this.height ))
			return true;
		return false;
	}
	
	public boolean intercept(double sX, double sY, double width, double height) {
		double endX = this.start.x + this.width,
			   endY = this.start.y + this.height;
		if ( ( sX <= endX ) && 
			 ( sY <= endY ) && 
			 ! ( ( sX + width <= this.start.x ) && ( sY + height <= this.start.y ) ) 
		   )
			return true;
		if ( ( sX + width >= this.start.x ) && 
			 ( sY + height >= this.start.y ) &&
			 ! ( ( sX >= endX ) && ( sY >= endY ) )
		    ) 
			return true;
		return false;
	}
	
	public boolean isInside(double sX, double sY, double width, double height) {
		if ( this.start.x >= sX &&
		     this.start.y >= sY &&
		     this.start.x + this.width <= sX + width &&
		     this.start.y + this.height <= sY + height )
			return true;
		return false;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public boolean getSelect() {
		return Select;
	}
	
	public int getClosestPort(Point clicked) {
		int ret = 0;
		double min = 1000000.0;
		double[] dx = { width/2, width, width/2, 0 },
			     dy = { 0, height/2, height, height/2 };
		for(int i=0 ; i<4 ; i++) {
			double u = clicked.distance(start.x + dx[i], start.y + dy[i]);
			if(u < min) {
				min = u;
				ret = i;
			}
		}
		return ret;
	}
	
	public Point getPortPos(int portNum) {
		double[] dx = { width/2, width, width/2, 0 },
			     dy = { 0, height/2, height, height/2 };
		return new Point(start.x + dx[portNum], start.y + dy[portNum]);
	}
	
	public List<UMLObject> getCompositedObject(){
        return null;
    }

	public abstract void draw(PaintBrush paintBrush);
}
