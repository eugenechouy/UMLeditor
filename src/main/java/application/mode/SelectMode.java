package application.mode;

import application.Point;
import application.MainCanvas;
import application.UMLObject;
import application.composite.*;

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
	
	private Point start = new Point(),
				  last = new Point();

	private List<UMLObject> selected = new ArrayList<>();

	public SelectMode() {
		super();
	}
	
	@Override
	public void pressedAction(MouseEvent event) {
		for(int i=0 ; i<selected.size() ; ++i) 
			selected.get(i).setSelect(false);
		selected.clear();

		UMLObject clicked = main.getClickedObject(event.getX(), event.getY());
		if(clicked != null){
			clicked.setSelect(true);
			selected.add(clicked);
		}
		start.x = last.x = event.getX();
		start.y = last.y = event.getY();
		main.paint();
	}

	@Override
	public void draggedAction(MouseEvent event) {
		if(selected.size() > 0) {
			for(int i=0 ; i<selected.size() ; ++i)
				selected.get(i).move(event.getX() - last.x, event.getY() - last.y);
			main.paint();
		} else {
			double distX = event.getX() - start.x, 
				   distY = event.getY() - start.y;
			double sX = start.x, 
			       sY = start.y;
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
		last.x = event.getX();
		last.y = event.getY();
	}
	
	@Override
	public void releasedAction(MouseEvent event) {
		double distX = event.getX()-start.x, 
		       distY = event.getY()-start.y;
	    double sX = start.x, 
		       sY = start.y;
		if(distX < 0) {
			sX += distX;
			distX = Math.abs(distX);
		} 
		if(distY < 0) {
			sY += distY;
			distY = Math.abs(distY);
		}
		frontPaintBrush.clear();

		for(int i=0 ; i<objects.size() ; ++i) {
			if( objects.get(i).isCover(sX, sY, distX, distY) ){
				objects.get(i).setSelect(true);
				selected.add(objects.get(i));
			}
		}
		main.paint();
	}

	@Override
	public void group() {
		if(selected.size() > 1){
			List<UMLObject> composited = new ArrayList<>();
			for(int i=0 ; i<selected.size() ; ++i)
				composited.add(selected.get(i));
			for(int i=0 ; i<composited.size() ; ++i)
				objects.remove(composited.get(i));

			GroupObject newobject = new GroupObject(composited);
			objects.add(newobject);

			for(int i=0 ; i<selected.size() ; ++i) 
				selected.get(i).setSelect(false);
			selected.clear();
			selected.add(newobject);
		}
	}

	@Override
	public void unGroup() {
		if(selected.size() == 1){
			List<UMLObject> u = selected.get(0).getGroupObjects();
			if(u != null){
				for(int i=0 ; i<u.size() ; ++i){
					objects.add(u.get(i));
					selected.add(u.get(i));
				}
				objects.remove(selected.get(0));
				selected.remove(selected.get(0));
			}
		}
	}

	@Override
	public void changeObjectName() {
		if(selected.size() == 1 && selected.get(0).getGroupObjects() == null){
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
						selected.get(0).setName(input.getText());
						stage.close();
						main.paint();
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
