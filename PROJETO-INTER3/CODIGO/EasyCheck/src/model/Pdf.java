package model;

import java.awt.Desktop;
import java.io.File;
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
			double precoTotal, precoFinal = 0;

			PdfWriter.getInstance(documento, new FileOutputStream("src/documents/Relatorio_"+ idNotaFiscal +".pdf"));
			documento.open();
			
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
			documento.add(new Paragraph("	Nome da Escola: "+nomeEscola+"                                                                        Data: "+nfDao.getData(idNotaFiscal)));
			documento.add(new Paragraph("                                                                                                         Número Nota: "+String.valueOf(idNotaFiscal)));
			documento.add(new Paragraph("	Nome do Fornecedor: " +nomeFornecedor));
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
			for(int j = 0; j < produtosNF.size(); j++) {
				precoTotal = (produtosNF.get(j).getPreco_uni() * produtosNF.get(j).getQtd());
				precoFinal += precoTotal;
				
				documento.add(new Paragraph("            Descrição: "+produtosNF.get(j).getDescricao()));
				documento.add(new Paragraph("\t                                	Qtd :"+produtosNF.get(j).getQtd()+"\t                   Preço unit.: R$"+produtosNF.get(j).getPreco_uni() +"\t	               Preço Total R$"+precoTotal));   
				documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
				
			}
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
			documento.add(new Paragraph("\t								          		Número de itens da Relatório: "+itensNF+ "                                            Preço Total: R$"+ precoFinal));
			documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
		}catch(DocumentException de) {
			System.err.println(de.getMessage());
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		documento.close();			
	}
	
	public void abrirDocumento(int numNotaF) throws IOException, InterruptedException {
		
		if (Desktop.isDesktopSupported()) {
		    try {
		        Desktop.getDesktop().open(new File("src/documents/Relatorio_"+ numNotaF +".pdf"));
		    } catch (IOException ex) {
		    	ex.printStackTrace();
		    }
		}	
		
		//Desktop.getDesktop().open(new File("C:\\Users\\Geraldo\\Desktop\\PDF_Relatorio1.pdf"));
		
		
	}
}
