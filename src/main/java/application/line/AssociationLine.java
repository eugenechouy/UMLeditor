package application.line;

import application.Point;
import application.PaintBrush;
import application.object.UMLObject;

public class AssociationLine extends UMLLine {
	
	public AssociationLine(UMLObject start, int startPort, UMLObject end, int endPort) {
		super(start, startPort, end, endPort);
	}
	
	@Override
	public void draw(PaintBrush paintBrush) {
		Point s = start.getPortPos(startPort),
			  e = end.getPortPos(endPort);
		
		double angle = Math.atan2((e.y - s.y), (e.x - s.x)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + e.x;
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + e.y;

        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + e.x;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + e.y;
        
        paintBrush.drawLine(s.x, s.y, e.x, e.y);
        paintBrush.drawLine(e.x, e.y, x1, y1);
        paintBrush.drawLine(e.x, e.y, x2, y2);
	}

}
