package controller;

import java.io.IOException;
import javax.swing.JOptionPane;
import dao.LoginDao;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Login;


public class LoginTopController {

	@FXML
	private TextField txtLogin;

	@FXML
	private TextField txtSenha;


	@FXML
	private void login(ActionEvent event) throws IOException {

		
		LoginDao dl = new LoginDao();
		Login login = dl.login(txtLogin.getText(), txtSenha.getText());
		
		System.out.println("usuario: " + txtLogin.getText());
		
		if(login != null) {
			
			//Chamando a segunda tela
			Stage primaryStage =  new Stage();
			Pane root = FXMLLoader.load(getClass().getResource("/view/TelaCadastro.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		}else {
			JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorreto(os)");
		}


	}

}
