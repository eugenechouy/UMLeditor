package application;

import application.PaintBrush;

import java.util.*;

public abstract class UMLObject { 
    
    protected int objectID;
    protected int depth = 0;
    protected boolean selected = false; 
    protected String name;
    private static int count = 0;

    public UMLObject() {
        objectID = count;
        depth = count++;
    }

    public int getDepth() {
		return depth;
	}

    public boolean getSelect() {
		return selected;
	}
	
	public void setSelect(boolean selected) {
		this.selected = selected;
	}

    public void setName(String name){
		this.name = name;
	}

    public List<UMLObject> getGroupObjects(){
        return null;
    }

    public abstract void draw(PaintBrush paintBrush);
    public abstract void move(double distX, double distY);
    public abstract boolean isInside(double x, double y);  
    public abstract boolean isCover(double sX, double sY, double width, double height);
} 