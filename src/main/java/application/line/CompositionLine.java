package application.line;

import application.Port;
import application.PaintBrush;
import application.Point;

public class CompositionLine extends LineObject {

	public CompositionLine(Port start, Port end) {
		super(start, end);
	}
	
	@Override
	public void draw(PaintBrush paintBrush) {
		Point s = start.getPosition(),
			  e = end.getPosition();
		
		double angle = Math.atan2((e.y - s.y), (e.x - s.x)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + e.x;
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + e.y;

        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + e.x;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + e.y;
        
        double x4 = (x1 + x2) - e.x;
        double y4 = (y1 + y2) - e.y;
        
        paintBrush.drawLine(s.x, s.y, x4, y4);
        paintBrush.drawLine(e.x, e.y, x1, y1);
        paintBrush.drawLine(e.x, e.y, x2, y2);
        paintBrush.drawLine(x1, y1, x4, y4);
        paintBrush.drawLine(x2, y2, x4, y4);
	}
    
}
