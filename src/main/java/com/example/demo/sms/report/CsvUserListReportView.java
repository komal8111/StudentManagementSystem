package com.example.demo.sms.report;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.demo.sms.entity.Student;
import com.lowagie.text.DocumentException;



@Component
public class CsvUserListReportView {
	
	public void exportToCsv(List<Student> student, HttpServletResponse response) throws IOException {
		String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		String newPath="Studentlist_"+fileName+".csv";
		response.setHeader("Content-Disposition","attachment; filename="+newPath);
		
		ICsvBeanWriter csvWriter =new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
		String[] csvHeader= {"Id","Name","Email","Phoneno"};
		String[] fieldMapping= {"id","name","email","phoneno"};
		
		csvWriter.writeHeader(csvHeader);
		for(Student stud:student)
		{
			csvWriter.write(stud, fieldMapping);
		}
		
		csvWriter.close();
		
		
	}

	
}
