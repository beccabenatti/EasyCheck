package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.EscolaDao;
import dao.FornecedorDao;
import dao.NotaFiscalDao;
import dao.ProdutoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Fornecedor;
import model.Pdf;
import model.Produto;

public class TelaCadastroController implements Initializable{

	@FXML
	private ComboBox<String> cbProduto;

	@FXML
	private TextField txtQdt;

	@FXML
	private TextField txtPreco;

	@FXML
	private Button btInserir;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btFinalizar;

	@FXML
	private Button btAlterar;

	@FXML
	private TableView<Produto> tableProdutos;

	@FXML
	private TableColumn<Produto, String> colDesc;

	@FXML
	private TableColumn<Produto, String> colQdt;

	@FXML
	private TableColumn<Produto, Double> colPrecoUni;

	@FXML
	private Label lblNomeEscola;

	@FXML
	private ComboBox<String> cbFornecedor;

	@FXML
	private TextField txtNumNota;

	private List<Produto> produtosNF;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		EscolaDao escolaDao = new EscolaDao();
		ProdutoDao produtoDao = new ProdutoDao();
		FornecedorDao fornecedorDao = new FornecedorDao();

		ObservableList<String> obsProdutos = FXCollections.observableArrayList();
		ObservableList<String> obsFornecedor = FXCollections.observableArrayList();

		lblNomeEscola.setText(escolaDao.getEscola().getNomeEscola());
		
		for (Produto produto : produtoDao.listaProdutos()) {			
			obsProdutos.add(produto.getDescricao());
		}

		cbProduto.setItems(obsProdutos);

		colDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		colQdt.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		colPrecoUni.setCellValueFactory(new PropertyValueFactory<>("preco_uni"));

		for (Fornecedor fornecedor : fornecedorDao.listaFornecedor()) {			
			obsFornecedor.add(fornecedor.getNome());
		}
		cbFornecedor.setItems(obsFornecedor);
	}



	@FXML
	void inserirProduto(ActionEvent event) {

		for (int i = 0; i < tableProdutos.getItems().size(); i++) {
			if(cbProduto.getValue().equals(tableProdutos.getItems().get(i).getDescricao())) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alerta");
				alert.setHeaderText(null);
				alert.setContentText("Produto já inserido na tabela!");

				alert.showAndWait();
				return;
			}
		}

		if((cbProduto.getValue() == null) || (txtQdt.getText().equals("0"))|| (txtPreco.getText().equals("0"))) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText(null);
			alert.setContentText("Preencha todos os campos");

			alert.showAndWait();
		}else {

			Produto produto = new Produto();
			ObservableList<Produto> produtos = FXCollections.observableArrayList();

			produto.setDescricao(cbProduto.getValue());

			try { 

				produto.setQtd(Integer.parseInt(txtQdt.getText()));
				produto.setPreco_uni(Double.parseDouble(txtPreco.getText()));	
				produtos.add(produto);

				tableProdutos.getItems().addAll(produtos);

				txtQdt.clear();
				txtPreco.clear();

			} catch (NumberFormatException e) {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alerta");
				alert.setHeaderText(null);
				alert.setContentText("Campo quantidade e preço só aceitam números!");

				alert.showAndWait();

				txtQdt.clear();
				txtPreco.clear();
			}
		}
	}

	@FXML
	void excluirProduto(ActionEvent event) {

		if(tableProdutos.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um item da tabela!");

			alert.showAndWait();
		}else {
			tableProdutos.getItems().removeAll(tableProdutos.getSelectionModel().getSelectedItems());    		
		}    	

	}


	@FXML
	void alterarProduto(ActionEvent event) {
		boolean isEquals = false;

		for (int i = 0; i < tableProdutos.getItems().size(); i++) {
			if(cbProduto.getValue().equals(tableProdutos.getItems().get(i).getDescricao())) {
				tableProdutos.getItems().get(i).setQtd(Integer.parseInt((txtQdt.getText())));
				tableProdutos.getItems().get(i).setPreco_uni(Double.parseDouble((txtPreco.getText())));

				tableProdutos.getItems().set(i, tableProdutos.getItems().get(i));

				txtQdt.clear();
				txtPreco.clear();

				isEquals = true;

				break;
			}
		}

		if(!isEquals) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerta");
			alert.setHeaderText(null);
			alert.setContentText("produto não encontrado!");

			alert.showAndWait();
		}
	}

	@FXML
	void finalizarRegistro(ActionEvent event) throws SQLException {		
		
		Pdf pdf = new Pdf();
		
		ProdutoDao produtoDao = new ProdutoDao();	
		NotaFiscalDao notaFiscalDao = new NotaFiscalDao();
		produtosNF = new ArrayList<Produto>();
		int itensNF;
		
		String nomeEscola = lblNomeEscola.getText();		
		String nomeFornecedor = cbFornecedor.getValue();		
		int idNotaFiscal = Integer.parseInt(txtNumNota.getText());	
		
		notaFiscalDao.inserirNotaFiscalBanco(idNotaFiscal, nomeEscola, nomeFornecedor);

		for (int i = 0; i < tableProdutos.getItems().size(); i++) {
			
			Produto produto = new Produto();
			
			produto.setDescricao(tableProdutos.getItems().get(i).getDescricao());
			produto.setQtd(tableProdutos.getItems().get(i).getQtd());
			produto.setPreco_uni(tableProdutos.getItems().get(i).getPreco_uni());
			
			produtoDao.inserirProdutosBanco(produto, idNotaFiscal);
			produtosNF.add(produto);
			
		}
		itensNF = notaFiscalDao.getQuantItensNF(idNotaFiscal);
		pdf.gerarDocumento(nomeEscola, nomeFornecedor, idNotaFiscal, produtosNF, itensNF);
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Alerta");
		alert.setHeaderText(null);
		alert.setContentText("Nota criada com sucesso!");
		
		txtQdt.clear();
		txtPreco.clear();
		txtNumNota.clear();

		alert.showAndWait();
		
	}
}

