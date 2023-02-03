package com.example.demo.sms.report;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.example.demo.sms.entity.Student;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



@Component
public  class PdfUserListReportView{

	

	public void exportToPdf(List<Student> student, HttpServletResponse response) throws DocumentException, IOException {
		String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		String newPath="Studentlist_"+fileName+".pdf";
		response.setHeader("Content-Disposition","attachment; filename="+newPath);
		Document document =new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.black);
		Paragraph para=new Paragraph("List of Students",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(para);
		
		
		PdfPTable table=new PdfPTable(4);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
		
		PdfPCell cell=new PdfPCell();
		cell.setBackgroundColor(Color.orange);
		cell.setPadding(5);
		Font cellfont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		cellfont.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("ID",cellfont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Name",cellfont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email",cellfont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Phoneno",cellfont));
		table.addCell(cell);
		
		
		for(Student stud:student)
		{
			table.addCell(String.valueOf(stud.getId()));
			table.addCell(stud.getName());
			table.addCell(stud.getEmail());
			table.addCell(String.valueOf(stud.getPhoneno()));
		}
		
		document.add(table);
		document.close();
		
	}
	
	
}

	


