package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		//Chamando a tela de login
		Pane root = FXMLLoader.load(getClass().getResource("/view/loginTop.fxml"));
		Scene scene = new Scene(root, 700, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
