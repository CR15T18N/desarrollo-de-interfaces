package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ejercicio2 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane panel = new StackPane();
			
			Rectangle r1 = new Rectangle(400,400,Color.DARKGREEN);
			Rectangle r2 = new Rectangle(300,300,Color.GREEN);
			Rectangle r3 = new Rectangle(200,00,Color.LIGHTGREEN);
			
			panel.getChildren().addAll(r1, r2, r3);
			
			Scene scene = new Scene(panel, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
