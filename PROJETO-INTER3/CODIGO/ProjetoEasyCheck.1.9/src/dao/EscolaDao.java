package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
