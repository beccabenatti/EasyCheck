package model;

public class Fornecedor {
	
	private int idFornecedor;
	private String cpf_cnpj;
	private String nome;
	private String endereco;
    private String cep;
    
    public int getIdFornecedor() {
    	return idFornecedor;
    }
    public void setIdFornecedor(int idFornecedor) {
    	this.idFornecedor = idFornecedor;
    }
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

}
