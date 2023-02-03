package com.example.demo.sms.csv;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.opencsv.bean.CsvBindByName;

public class CsvPojo {
	
	@CsvBindByName(column = "Id")
	private long id;
	
	@CsvBindByName(column = "Name")
	private String name;
	
	@CsvBindByName(column = "Email")
	private String email;
	
	@CsvBindByName(column = "Phoneno")
	private long phoneno;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	
	
}
