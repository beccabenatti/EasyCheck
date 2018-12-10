package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dao.NotaFiscalDao;
import dao.ProdutoDao;

public class Pdf {
	
	ProdutoDao produtoDao = new ProdutoDao();

	public void gerarDocumento(String nomeEscola, String nomeFornecedor, int idNotaFiscal, List<Produto> produtosNF, int itensNF) throws SQLException {
		Document documento = new Document(PageSize.A4, 30, 20, 30, 20);
		
		try{			
			NotaFiscalDao nfDao = new NotaFiscalDao();
			
			int i = 1;

			PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\Mariana\\Desktop\\PDF_Relatorio"+ i++ +".pdf"));
			documento.open();
			
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
			documento.add(new Paragraph("	Nome da Escola: "+nomeEscola+"                                                                            	"+nfDao.getData(idNotaFiscal)));
			documento.add(new Paragraph("                                                                                                            Número NF: "+String.valueOf(idNotaFiscal)));
			documento.add(new Paragraph("	Nome do Fornecedor: " +nomeFornecedor));
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
			documento.add(new Paragraph("|  Quantidade              |                   Preço Unitário                       |                           Descrição              |"));
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
			for(int j = 0; j < produtosNF.size(); j++) {
				
				documento.add(new Paragraph("    "+produtosNF.get(j).getQtd()+"                              |                              "+produtosNF.get(j).getPreco_uni()+"                              |                          "+produtosNF.get(j).getDescricao()+"           "));
				
			}
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
			documento.add(new Paragraph(".																														Número de itens da NF: "+itensNF));
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
		}catch(DocumentException de) {
			System.err.println(de.getMessage());
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		documento.close();			
	}
}
