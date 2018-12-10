package model;

public class Login {

	private String usuario;
	private String senha;
	private String tipo;

	public Login(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
