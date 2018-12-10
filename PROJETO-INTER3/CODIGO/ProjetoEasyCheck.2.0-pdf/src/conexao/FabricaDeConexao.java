package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDeConexao {

	private static final String servidor = "jdbc:mysql://localhost:3306/easyCheck?useSSL=false";
	private static final String usuario = "root";
	private static final String senha = "ma322712";
	//private static final String senha = "16setembro";
	private static final String driver = "com.mysql.jdbc.Driver";

	public static Connection criarConexao() {

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(servidor, usuario, senha);
			return con;

		} catch (Exception e) {
			System.out.println("Erro: "+ e.getMessage());
		}

		return null;
	}
}
