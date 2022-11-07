package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio1 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox vbox = new VBox(8);
		    vbox.getChildren().addAll(new TextField("Nombre"),new TextField("Contraseña"), new Button("Entrar"));
			
			Scene scene = new Scene(vbox, 400, 300);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ejercicio1");
			primaryStage.show();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Confirmacion");
			alert.setHeaderText("Bienbenido");
			alert.setContentText(null);

			alert.showAndWait();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
