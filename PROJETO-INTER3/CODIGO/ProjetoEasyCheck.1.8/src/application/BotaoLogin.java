/*
package application;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexao.FabricaDeConexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class BotaoLogin extends FabricaDeConexao{
	@FXML
	public Button botaoEnter;

	@FXML
	private TextField txtLogin;

	@FXML
	private TextField txtSenha;
	
	private List<String> produtos = new ArrayList<>(); 
	
	ObservableList<String> obsDescricao;

	@FXML
	private ComboBox<String> cbDescricao; // = new ComboBox(obsDescricao);
	
	FabricaDeConexao con = new FabricaDeConexao();

	@FXML
	private void enter(ActionEvent event) throws IOException {
		con.conectar();
		if(con.estaConectado()) {
			try {
				Statement stmt = null;
				stmt = con.conecction.createStatement();
				String queryLogin = "select * from login where login = '" + txtLogin.getText() + "';";
				rs = stmt.executeQuery(queryLogin);
				while(rs.next()) {
					String login = rs.getString("login");
					String senha = rs.getString("senha");

					if(txtLogin.getText().equals(login) && txtSenha.getText().equals(senha)) {
						//Chamando a segunda tela
						Stage primaryStage =  new Stage();
						Pane root = FXMLLoader.load(getClass().getResource("/view/TelaCadastro.fxml"));
						Scene scene = new Scene(root, 800, 600);
						primaryStage.setScene(scene);
						primaryStage.show();

						//Para carregar comboBox
						
						String queryComboBox = "select * from produto";
						rs = stmt.executeQuery(queryComboBox);
						String nomeProduto;
						
						while(rs.next()) {
							nomeProduto = rs.getString("descricao");
							System.out.println("nome produto: " + nomeProduto);
							produtos.add(nomeProduto);
						}
						
						ObservableList<String> obsDescricao = FXCollections.observableArrayList(produtos);
						
						for (int i = 0; i < obsDescricao.size(); i++) {
							System.out.println("nome produto obs: " + obsDescricao.get(i));
						}
						
						cbDescricao = new ComboBox<String>(obsDescricao);
						
						//for (int i = 0; i < obsDescricao.size(); i++) {
						//cbDescricao.getItems().addAll(obsDescricao.get(i));
						//}
												
						cbDescricao.setItems(obsDescricao);
						
						cbDescricao.setValue(obsDescricao.get(0).toString());
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorreto(os)");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("\nNão foi possível conectar ao BD\n");
		}

	}

}
*/