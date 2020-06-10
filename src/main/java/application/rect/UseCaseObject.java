package application.rect;

import application.PaintBrush;

import javafx.scene.paint.Color;

public class UseCaseObject extends RectObject{

	public UseCaseObject(double x, double y, double width, double height) {
		super(x, y, width, height, "A Use Case");
	}
	
	@Override
	public void draw(PaintBrush paintBrush) {
		paintBrush.setFill(Color.WHITE);
		paintBrush.fillOval(start.x, start.y, width, height);
		paintBrush.strokeOval(start.x, start.y, width, height);
		paintBrush.fillText(name, start.x+width/2, start.y+height/2);
		if(this.selected) {
			for(int i=0 ; i<4 ; ++i)
				ports[i].draw(paintBrush);
		}			
	}
	
}
