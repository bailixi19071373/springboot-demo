package com.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.Page;
import com.sc.pojo.Student;

public interface StudentServiceIn {
	
	@CachePut(value="student",key="#result.id")
	Student addOne(Student stu);
	
	//用id做key
	@CachePut(value="student",key="#result.id")
	 Student updateOne(Student stu);
	
	@Cacheable("student")
	 Student getById(Integer id);
	
	@CacheEvict(value="student")
	 void delete(Integer id);

	@Cacheable(value="student",key="#root.methodName ")
	List<Student> selectAllStudentCourseScore();
	
	String checkStunoExist(String stuno);
	
}
