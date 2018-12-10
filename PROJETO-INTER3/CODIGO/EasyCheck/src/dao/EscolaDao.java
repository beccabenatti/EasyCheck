package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexao.FabricaDeConexao;
import model.Escola;

public class EscolaDao {
	
	static Escola escola = new Escola();
	
	public void setId(int idEscola) {
		
		Connection con = FabricaDeConexao.criarConexao();
			
		try {
			Statement stmt = null;
			stmt = con.createStatement();
			
			String queryEscola = "select * from escola e inner join login l on l.idEscola = e.idEscola where l.idEscola = " + idEscola + ";";
			ResultSet rs = stmt.executeQuery(queryEscola);
			
			rs.next();
			
			escola.setNomeEscola(rs.getString("nomeEscola"));
			escola.setCpfCnpj(rs.getString("cpf_cnpj"));
			escola.setEndereco(rs.getString("endereco"));
			escola.setTelefone(rs.getString("telefone"));
			escola.setIdEscola(idEscola);

			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
	}
	
	public Escola getEscola() { 
		return escola; 
	}
	
	public List<Escola> listaEscolas(){
		
		List<Escola> listaEscolas = new ArrayList<Escola>();
		
		Connection con = FabricaDeConexao.criarConexao();

		Escola escola = null;
		
		try {
			Statement stmt = null;
			stmt = con.createStatement();

			String queryProdutos = "select * from escola";
			ResultSet rs = stmt.executeQuery(queryProdutos);

			while(rs.next()) {
				escola = new Escola();
				escola.setNomeEscola(rs.getString("nomeEscola"));
				listaEscolas.add(escola);				
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
		
		return listaEscolas;
	}

}
