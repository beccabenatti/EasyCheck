package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.EscolaDao;
import dao.NotaFiscalDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NotaFiscal;
import model.Pdf;

public class TelaVisualizacaoController implements Initializable{

	@FXML
	private ComboBox<String> cbEscola;

	@FXML
	private Button btGerarNota;

	@FXML
	private TableView<NotaFiscal> tableNotas;

	@FXML
	private TableColumn<NotaFiscal, Integer> colNotas;

	@FXML
	private TableColumn<NotaFiscal, String> colData;

	@FXML
	private Button btVisualizarNota;

	EscolaDao escolaDao = new EscolaDao();
	NotaFiscalDao notaDao = new NotaFiscalDao();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		for(int i = 0; i < escolaDao.listaEscolas().size(); i++) {
			cbEscola.getItems().addAll(escolaDao.listaEscolas().get(i).getNomeEscola());
		}

		colNotas.setCellValueFactory(new PropertyValueFactory<>("id_notaF"));
		colData.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));

	}

	@FXML
	void gerarNotas(ActionEvent event) {
		
		if(notaDao.listaNotas(cbEscola.getValue()).isEmpty()){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText(null);
			alert.setContentText("Escola selecionada ainda não possui relatórios!");

			alert.showAndWait();
		}else {

		tableNotas.setItems(notaDao.listaNotas(cbEscola.getValue()));
		
		}
	}

	@FXML
	void visualizarNota(ActionEvent event) throws IOException, InterruptedException {

		Pdf pdf = new Pdf();

		if(tableNotas.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um Relatório da tabela!");

			alert.showAndWait();
		}else {

			int numNota = tableNotas.getSelectionModel().getSelectedItem().getId_notaF();

			pdf.abrirDocumento(numNota);
		}

	}

}

