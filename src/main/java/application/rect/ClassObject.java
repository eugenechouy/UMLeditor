package application.rect;

import application.util.PaintBrush;

import javafx.scene.paint.Color;

public class ClassObject extends RectObject{
	
	public ClassObject(double x, double y, double width, double height) {
		super(x, y, width, height, "A Class");
	}
	
	@Override
	public void draw(PaintBrush paintBrush) {
		paintBrush.setFill(Color.WHITE);
		paintBrush.fillRectangle(start.x, start.y, width, height);
		paintBrush.strokeRectangle(start.x, start.y, width, height);
		paintBrush.fillText(name, start.x+width/2, start.y+height/2);
		if(this.selected) {
			for(int i=0 ; i<4 ; ++i)
				ports[i].draw(paintBrush);
		}			
	}
	
}
