package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conexao.FabricaDeConexao;
import model.Login;

public class LoginDao {

	Login login = null;
	public Login login(String usuario, String senha) {

			
		EscolaDao escolaDao = new EscolaDao();
		
		Connection con = FabricaDeConexao.criarConexao();

		try {

			Statement stmt = null;
			stmt = con.createStatement();

			String queryLogin = "select * from login where login = '" + usuario + "' AND senha = '" + senha + "';";
			ResultSet rs = stmt.executeQuery(queryLogin);

			while(rs.next()) {

				login = new Login(usuario, senha);
				escolaDao.setId(rs.getInt("idEscola"));
				login.setTipo(rs.getString("tipo"));
				
				return login;

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

		return login;
	}
	public boolean isEscola() {
		System.out.println(login.getTipo());
		if(login.getTipo().equals("admin")) {
			return true;
		}else {
			return false;
		}
	}
}
