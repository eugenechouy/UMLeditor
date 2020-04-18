package application.object;

import application.Point;
import application.PaintBrush;
import application.object.UMLObject;

import java.util.*;

public class CompositeObject extends UMLObject{
	
    private List<UMLObject> composited = new ArrayList<>();

	public CompositeObject(List<UMLObject> composited) {
		this.composited = composited;
        for(int i=0 ; i<composited.size() ; i++)
            if(composited.get(i).getDepth() > this.depth)
                this.depth = composited.get(i).getDepth();
	}
    
    public void setSelect(boolean Select) {
		for(int i=0 ; i<composited.size() ; i++)
            composited.get(i).setSelect(Select);
	}

    @Override
    public List<UMLObject> getCompositedObject(){
        return this.composited;
    }

    @Override
	public void draw(PaintBrush paintBrush) {
		for(int i=0 ; i<composited.size() ; i++)
            composited.get(i).draw(paintBrush);
	}
	
    @Override
    public boolean cover(double x, double y) {
        for(int i=0 ; i<composited.size() ; i++){
            if(composited.get(i).cover(x, y))
                return true;
        }
		return false;
	}

    @Override
    public boolean isInside(double sX, double sY, double width, double height) {
		for(int i=0 ; i<composited.size() ; i++){
            if(!composited.get(i).isInside(sX, sY, width, height))
                return false;
        }
		return true;
	}

    @Override
    public void move(double distX, double distY) {
		for(int i=0 ; i<composited.size() ; i++){
            composited.get(i).move(distX, distY);
        }
	}
	
    @Override
    public int getClosestPort(Point clicked) {
		return -1;
	}

}
