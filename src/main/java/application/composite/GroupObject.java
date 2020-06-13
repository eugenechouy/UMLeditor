package application.composite;

import application.Point;
import application.UMLObject;
import application.util.PaintBrush;

import java.util.*;

public class GroupObject extends UMLObject{
	
    private List<UMLObject> objects = new ArrayList<>();

	public GroupObject(List<UMLObject> objects) {
        super();
		this.objects = objects;
	}
    
    @Override
    public void setSelect(boolean selected) {
        this.selected = selected;
		for(int i=0 ; i<objects.size() ; ++i)
            objects.get(i).setSelect(selected);
	}

    @Override
    public List<UMLObject> getGroupObjects(){
        return this.objects;
    }

    @Override
	public void draw(PaintBrush paintBrush) {
		for(int i=0 ; i<objects.size() ; ++i)
            objects.get(i).draw(paintBrush);
	}

    @Override
    public void move(double distX, double distY) {
		for(int i=0 ; i<objects.size() ; ++i){
            objects.get(i).move(distX, distY);
        }
	}
	
    @Override
    public boolean isInside(double x, double y) {
        for(int i=0 ; i<objects.size() ; ++i){
            if(objects.get(i).isInside(x, y))
                return true;
        }
		return false;
	}

    @Override
    public boolean isCover(double sX, double sY, double width, double height) {
		for(int i=0 ; i<objects.size() ; ++i){
            if(!objects.get(i).isCover(sX, sY, width, height))
                return false;
        }
		return true;
	}
}
