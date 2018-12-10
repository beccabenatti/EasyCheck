package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.FabricaDeConexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Produto;

public class ProdutoDao {

	public ObservableList<Produto> listaProdutos() {

		Produto produto = null;

		ObservableList<Produto> produtosBanco = FXCollections.observableArrayList();

		Connection con = FabricaDeConexao.criarConexao();

		try {
			Statement stmt = null;
			stmt = con.createStatement();

			String queryProdutos = "select * from produto";
			ResultSet rs = stmt.executeQuery(queryProdutos);

			while(rs.next()) {
				produto = new Produto();
				produto.setCod_produto(rs.getInt("cod_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setUnid_medida(rs.getString("unid_medida"));

				produtosBanco.add(produto);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return produtosBanco;

	}

	//public void inserirProdutosBanco(List<Produto> listaProdutos) {
	public void inserirProdutosBanco(Produto produto) {	

		Connection con = FabricaDeConexao.criarConexao();

		try {

			getCodProdutoBanco(produto);
			
			Statement stmt = null;
			stmt = con.createStatement();

			String queryProdutos;

			queryProdutos = "call calculaPrecoTotal(1, " + produto.getCod_produto() +", " + produto.getQtd() + ", " + produto.getPreco_uni() + ")";
			
			stmt.executeQuery(queryProdutos);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void getCodProdutoBanco(Produto produto) {
		Connection con = FabricaDeConexao.criarConexao();

		try {

			Statement stmt = null;
			stmt = con.createStatement();

			String queryCodProdutoBanco;

			queryCodProdutoBanco = "select cod_produto from produto where descricao = '" + produto.getDescricao() + "';";

			ResultSet rs = stmt.executeQuery(queryCodProdutoBanco);

			rs.next();
			
			produto.setCod_produto(rs.getInt("cod_produto"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

