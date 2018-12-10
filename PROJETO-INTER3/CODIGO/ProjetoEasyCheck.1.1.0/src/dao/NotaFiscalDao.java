package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.FabricaDeConexao;

public class NotaFiscalDao {

	public void inserirNotaFiscalBanco(int idNotaFiscal, String nomeEscola, String nomeFornecedor){		

		Connection con = FabricaDeConexao.criarConexao();

		try {
			Statement stmt = null;
			stmt = con.createStatement();

			String queryFornecedor = "call inserirNotaFisBanco(" + idNotaFiscal + ", " + getIdEscola(nomeEscola) + ", " + getIdFornecedor(nomeFornecedor) + ");";
			stmt.executeQuery(queryFornecedor);

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

	private int getIdEscola(String nomeEscola) {
		
		int idEscola = 0;
		
		Connection con = FabricaDeConexao.criarConexao();

		try {
			Statement stmt = null;
			stmt = con.createStatement();

			String queryIdEscola = "select idEscola from escola where nomeEscola = '" + nomeEscola + "';";
			ResultSet rs = stmt.executeQuery(queryIdEscola);
			
			rs.next();
			
			idEscola = rs.getInt("idEscola");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return idEscola;
	}

	private int getIdFornecedor(String nomeFornecedor) {
		
		int idFornecedor = 0;
		
		Connection con = FabricaDeConexao.criarConexao();

		try {
			Statement stmt = null;
			stmt = con.createStatement();

			String queryIdFornecedor = "select idFornecedor from fornecedor where nome = '" + nomeFornecedor +	"';";
			ResultSet rs = stmt.executeQuery(queryIdFornecedor);
			
			rs.next();
			
			idFornecedor = rs.getInt("idFornecedor");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return idFornecedor;
	}

}
