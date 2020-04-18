package application.object;

import application.PaintBrush;

import javafx.scene.paint.Color;

public class ClassObject extends UMLObject{
	
	public ClassObject(double x, double y, double width, double height, int depth) {
		super(x, y, width, height, depth, "A Class");
	}
	
	@Override
	public void draw(PaintBrush paintBrush) {
		paintBrush.setFill(Color.WHITE);
		paintBrush.fillRectangle(start.x, start.y, width, height);
		paintBrush.strokeRectangle(start.x, start.y, width, height);
		paintBrush.fillText(textField, start.x+width/2, start.y+height/2);
		if(this.Select) {
			double[] dx = { width/2, width, width/2, 0 },
				     dy = { 0, height/2, height, height/2 };
			paintBrush.setFill(Color.BLACK);
			for(int i=0 ; i<4 ; i++)
				paintBrush.fillRectangle(start.x+dx[i]-portWidth/2, start.y+dy[i]-portWidth/2, portWidth, portWidth);
		}	
	}
	
}
