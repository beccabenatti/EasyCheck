package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.FabricaDeConexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.NotaFiscal;

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
	
	public String getData(int numNotaF) {
		String dataPedido = "";

		Connection con = FabricaDeConexao.criarConexao();

		try {
			Statement stmt = null;
			stmt = con.createStatement();

			String queryGetData= "select date_format(dataPedido, '%d/%m/%Y') as data from notaFiscal where numNotaF = '" + numNotaF +	"';";
			ResultSet rs = stmt.executeQuery(queryGetData);

			rs.next();

			dataPedido = rs.getString("data");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return dataPedido;
	}

	public ObservableList<NotaFiscal> listaNotas(String nomeEscola) {

		Connection con = FabricaDeConexao.criarConexao();
		
		ObservableList<NotaFiscal> listaNotas = FXCollections.observableArrayList();
		

		try {
			
			Statement stmt = null;
			stmt = con.createStatement();

			String queryNotas = "select nf.numNotaF, date_format(nf.dataPedido, '%d/%m/%Y') AS 'dataPedido' from notaFiscal nf \r\n" + 
					"inner join escola e where nf.idEscola = (select idEscola from escola e where e.nomeEscola = '" + nomeEscola + "')\r\n" + 
					"group by nf.numNotaF";
			
			ResultSet rs = stmt.executeQuery(queryNotas);

			while(rs.next()) {

				NotaFiscal notaFiscal = new NotaFiscal();
				
				notaFiscal.setId_notaF(rs.getInt("numNotaF"));
				notaFiscal.setDataPedido(rs.getString("dataPedido"));
				
				listaNotas.add(notaFiscal);
			
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
		
		return listaNotas;
	}
	
	public int getQuantItensNF(int idNotaFiscal) {
		int quantItensNF = 0;
		
		Connection con = FabricaDeConexao.criarConexao();

		try {
			Statement stmt = null;
			stmt = con.createStatement();
			String queryTotalItens = "select quant_itens("+idNotaFiscal+") as 'Quantidade Itens Nota'";
			ResultSet rs = stmt.executeQuery(queryTotalItens);
			
			rs.next();
			
		quantItensNF = rs.getInt("Quantidade Itens Nota");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return quantItensNF;
	}

}
