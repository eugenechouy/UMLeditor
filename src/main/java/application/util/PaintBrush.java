package application.util;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PaintBrush {
	private Canvas canvas;
	private GraphicsContext gc;
	
	public PaintBrush(Canvas canvas) {
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
	}
	
	public void setFill(Color color) {
		gc.setFill(color);
	}

	public void setLineWidth(double w) {
		gc.setLineWidth(w);
	}
	
	public void clear() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void border(){
		gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public void eraser(double x, double y, double width, double height) {
		gc.clearRect(x, y, width, height);
	}

	public void strokeRectangle(double x, double y, double width, double height) {
		gc.strokeRect(x, y, width, height);
	}
	
	public void fillRectangle(double x, double y, double width, double height) {
		gc.fillRect(x, y, width, height);
	}
	
	public void strokeOval(double x, double y, double width, double height) {
		gc.strokeOval(x, y, width, height);
	}
	
	public void fillOval(double x, double y, double width, double height) {
		gc.fillOval(x, y, width, height);
	}
	
	public void drawLine(double x1, double y1, double x2, double y2) {
		gc.strokeLine(x1, y1, x2, y2);
	}

	public void fillText(String text, double x, double y){
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
		gc.fillText(text, x, y);
	}

}
