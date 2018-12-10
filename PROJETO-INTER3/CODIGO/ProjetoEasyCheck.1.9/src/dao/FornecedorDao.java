package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.FabricaDeConexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Fornecedor;

public class FornecedorDao {

	public ObservableList<Fornecedor> listaFornecedor(){
		
		Connection con = FabricaDeConexao.criarConexao();

		ObservableList<Fornecedor> listaFornecedor = FXCollections.observableArrayList();


		try {
			Statement stmt = null;
			stmt = con.createStatement();

			String queryFornecedor = "select * from fornecedor";
			ResultSet rs = stmt.executeQuery(queryFornecedor);

			while(rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				
				fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
				fornecedor.setCpf_cnpj(rs.getString("cpf_cnpj"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setEndereco(rs.getString("endereco"));
				fornecedor.setCep(rs.getString("cep"));

				listaFornecedor.add(fornecedor);				
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
		return listaFornecedor;
	}
}
