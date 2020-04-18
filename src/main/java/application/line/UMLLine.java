package application.line;

import application.PaintBrush;
import application.object.UMLObject;

public abstract class UMLLine {
	
	protected UMLObject start;
	protected UMLObject end;
	protected int startPort;
	protected int endPort;
	
	protected double arrowHeadSize = 10.0;
	
	public UMLLine() { }
	
	public UMLLine(UMLObject start, int startPort, UMLObject end, int endPort) {
		this.start = start;
		this.end = end;
		this.startPort = startPort;
		this.endPort = endPort;
	}
	
	public abstract void draw(PaintBrush paintBrush);
}
