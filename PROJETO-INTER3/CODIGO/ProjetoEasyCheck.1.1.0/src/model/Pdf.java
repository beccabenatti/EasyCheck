package model;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Pdf {

	Document documento = new Document();

	public void gerarDocumento() {
		try {

			PdfWriter.getInstance(documento, new FileOutputStream("C:\\PDF_DevMedia.pdf"));
			documento.open();

			// adicionando um parágrafo no documento
			documento.add(new Paragraph("Gerando PDF - Java"));
		}catch(DocumentException de) {
			System.err.println(de.getMessage());
		}
		catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}finally {
			documento.close();			
		}
	}
}
