package application;

import application.util.PaintBrush;
import application.rect.RectObject;
import application.line.LineObject;

import java.util.*;

public class Port {
	
    private final double size = 10.0;

	public RectObject parent;
    private Point center = new Point();
    private List<LineObject> lines = new ArrayList<>();

    public Port() {}

	public Port(double x, double y, RectObject parent) {
		this.center.x = x;
        this.center.y = y;
        this.parent = parent;
	}

    public Point getPosition() {
        return center;
    }

    public void addLine(LineObject line) {
        lines.add(line);
    }

    public void draw(PaintBrush paintBrush) {
        paintBrush.fillRectangle(center.x-size/2, center.y-size/2, size, size);
    }

    public void move(double distX, double distY) {
        center.x += distX;
        center.y += distY;
    }
}
