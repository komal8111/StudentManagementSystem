package com.example.demo.sms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.sms.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{

	List<Student> findByName(String name);
}
