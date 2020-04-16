package application.object;

import application.PaintBrush;

public class UseCaseObject extends UMLObject{

	public UseCaseObject(double x, double y, double width, double height, int depth) {
		super(x, y, width, height, depth);
	}
	
	public void draw(PaintBrush paintBrush) {
		if(this.Select) {
			double[] dx = { width/2, width, width/2, 0 },
				     dy = { 0, height/2, height, height/2 };
			for(int i=0 ; i<4 ; i++)
				paintBrush.fillRectangle(start.x+dx[i]-portWidth/2, start.y+dy[i]-portWidth/2, portWidth, portWidth);
		}			
		paintBrush.strokeOval(start.x, start.y, width, height);
	}
	
}
