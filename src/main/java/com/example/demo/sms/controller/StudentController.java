package com.example.demo.sms.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.example.demo.sms.entity.Student;
import com.example.demo.sms.report.CsvUserListReportView;
import com.example.demo.sms.report.ExcelUserListReportView;
import com.example.demo.sms.report.PdfUserListReportView;
import com.example.demo.sms.repository.StudentRepository;

@Controller
@RequestMapping("/students/")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private PdfUserListReportView pdfExport;

	@Autowired
	private CsvUserListReportView csvExport;

	

	@GetMapping("signup")
	public String showSignUpForm(Student student) {
		System.out.println("inside signup");
		return "add-student";

	}

	@PostMapping("add")
	public String addStudent(@Valid Student student, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "add-student";
		}

		studentRepository.save(student);
		return "redirect:list";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("students", studentRepository.findAll());
		return "index";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {

		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student id:" + id));
		model.addAttribute("student", student);
		return "update-student";
	}

	@GetMapping("/excelExport")
	public ModelAndView showExcelReport(Model model) {
		// model.addAttribute("students",studentRepository.findAll());
		ModelAndView mav = new ModelAndView();
		List<Student> findAll = (List<Student>) studentRepository.findAll();
		mav.setView(new ExcelUserListReportView());
		mav.addObject("studentlist", findAll);
		return mav;

	}

	@GetMapping("/pdfExport")
	public void showPdfReport(HttpServletResponse response) throws IOException {
		List<Student> student = (List<Student>) studentRepository.findAll();
		pdfExport.exportToPdf(student, response);
	}

	@GetMapping("/csvExport")
	public void showCsvReport(HttpServletResponse response) throws IOException {
		List<Student> student = (List<Student>) studentRepository.findAll();
		csvExport.exportToCsv(student, response);
	}

	@GetMapping(value="/dbExport",consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public void uploadFile() throws IOException 
	{
		
			}

	@PostMapping("update/{id}")
	public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			student.setId(id);
			return "update-student";
		}

		studentRepository.save(student);
		model.addAttribute("students", studentRepository.findAll());
		return "index";

	}

	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student id:" + id));
		studentRepository.delete(student);
		model.addAttribute("students", studentRepository.findAll());

		return "index";

	}

}
