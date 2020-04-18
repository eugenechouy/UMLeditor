package application.mode;

import application.MainCanvas;
import application.object.*;

import java.util.*;

import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class SelectMode extends BaseMode {
	
	private double lastX, lastY;
	private double startX, startY;

	private List<Integer> selected = new ArrayList<>();

	public SelectMode(MainCanvas main) {
		super(main);
	}
	
	@Override
	public void pressedAction(MouseEvent event) {
		List<Integer> candidate = new ArrayList<>();
		selected.clear();
		for(int i=0 ; i<object.size() ; i++) {
			object.get(i).setSelect(false);
			if( object.get(i).cover(event.getX(), event.getY()) )
				candidate.add(i);
		}
		if(candidate.size() > 0) {
			int top = -1;
			Integer upperMost = candidate.get(0);
			for(int i=0 ; i<candidate.size(); i++) {
				if( object.get(i).getDepth() >= top ) {
					top = object.get(i).getDepth();
					upperMost = candidate.get(i);
				}	
			}
			object.get(upperMost).setSelect(true);
			selected.add(upperMost);
		}
		main.rePaint();
		lastX = event.getX();
		lastY = event.getY();
		startX = event.getX();
		startY = event.getY();
	}

	@Override
	public void draggedAction(MouseEvent event) {
		if(selected.size() > 0) {
			for(int i=0 ; i<selected.size() ; i++)
				object.get(selected.get(i)).move(event.getX() - lastX, event.getY() - lastY);
			main.rePaint();
		} else {
			double distX = event.getX()-startX, distY = event.getY()-startY;
			double sX = startX, sY = startY;
			if(distX < 0) {
				sX += distX;
				distX = Math.abs(distX);
			} 
			if(distY < 0) {
				sY += distY;
				distY = Math.abs(distY);
			}
			frontPaintBrush.clear();
			frontPaintBrush.strokeRectangle(sX, sY, distX, distY);
		}
		lastX = event.getX();
		lastY = event.getY();
	}
	
	@Override
	public void releasedAction(MouseEvent event) {
		double distX = event.getX()-startX, distY = event.getY()-startY;
	    double sX = startX, sY = startY;
		if(distX < 0) {
			sX += distX;
			distX = Math.abs(distX);
		} 
		if(distY < 0) {
			sY += distY;
			distY = Math.abs(distY);
		}
		frontPaintBrush.clear();

		for(int i=0 ; i<object.size() ; i++) {
			if( object.get(i).isInside(sX, sY, distX, distY) ){
				object.get(i).setSelect(true);
				selected.add(i);
			}
		}
		main.rePaint();
	}

	@Override
	public void group() {
		if(selected.size() > 1){
			List<UMLObject> composited = new ArrayList<>();
			for(int i=0 ; i<selected.size() ; i++)
				composited.add(object.get(selected.get(i)));
			for(int i=0 ; i<composited.size() ; i++)
				object.remove(composited.get(i));
			object.add(new CompositeObject(composited));
		}
	}

	@Override
	public void unGroup() {
		if(selected.size() == 1){
			List<UMLObject> u = object.get(selected.get(0)).getCompositedObject();
			if(u != null){
				for(int i=0 ; i<u.size() ; i++)
					object.add(u.get(i));
				object.remove(object.get(selected.get(0)));
			}
		}
	}

	@Override
	public void changeObjectName() {
		if(selected.size() == 1 && object.get(selected.get(0)).getCompositedObject() == null){
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/changeName.fxml"));
				Stage stage = new Stage();
				stage.setTitle("change object name");
				stage.setScene(new Scene(root));
				stage.show();

				Scene scene = stage.getScene();
				Button okButton = (Button)scene.lookup("#ok_btn");
				Button cancelButton = (Button)scene.lookup("#cancel_btn");
				TextArea input = (TextArea)scene.lookup("#input");

				okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						object.get(selected.get(0)).setName(input.getText());
						stage.close();
						main.rePaint();
					}
				});

				cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						stage.close();
					}
				});
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
