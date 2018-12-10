package model;

public class Produto {

	private int cod_produto;
	private String descricao;
	private String unid_medida;
	private double preco_uni;
	private int qtd;

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getPreco_uni() {
		return preco_uni;
	}

	public void setPreco_uni(double preco_uni) {
		this.preco_uni = preco_uni;
	}

	public int getCod_produto() {
		return cod_produto;
	}
	
	public void setCod_produto(int cod_produto) {
		this.cod_produto = cod_produto;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getUnid_medida() {
		return unid_medida;
	}
	
	public void setUnid_medida(String unid_medida) {
		this.unid_medida = unid_medida;
	}

}
