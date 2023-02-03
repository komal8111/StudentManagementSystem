package com.example.demo.sms.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.example.demo.sms.entity.Student;



public class ExcelUserListReportView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		
		String newPath="Studentlist_"+fileName+".xls";
		
		response.setHeader("Content-Disposition","attachment; filename="+newPath);
		List<Student> list=(List<Student>) model.get("studentlist");
		
		Sheet sheet=workbook.createSheet("Student List");
		
		Row header=sheet.createRow(0);
		
		header.createCell(0).setCellValue("Name");
		header.createCell(1).setCellValue("Email");
		header.createCell(2).setCellValue("Phoneno");
		
		int rowNum=1;
		
		for(Student student:list)
		{
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(student.getName());
			row.createCell(1).setCellValue(student.getEmail());
			row.createCell(2).setCellValue(student.getPhoneno());
		}
		
		
		
	}
	
	

}
