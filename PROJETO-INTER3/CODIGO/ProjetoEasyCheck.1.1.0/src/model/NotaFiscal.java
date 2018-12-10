package model;

public class NotaFiscal {

	private int id_notaF;
    private String dataPedido;
    private int idEscola;
    private int idFornecedor;
    
	public int getId_notaF() {
		return id_notaF;
	}
	public void setId_notaF(int id_notaF) {
		this.id_notaF = id_notaF;
	}
	public String getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	public int getIdEscola() {
		return idEscola;
	}
	public void setIdEscola(int idEscola) {
		this.idEscola = idEscola;
	}
	public int getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
}
