package com.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.Page;
import com.sc.pojo.Course;

public interface CourseServiceIn {
	
	@CachePut(value="course",key="#result.id")
	Course addOne(Course cou);
	
	//用id做key
	@CachePut(value="course",key="#result.id")
	 Course updateOne(Course cou);
	
    @CachePut(value="course",key="#result.id")
	public Course updateOneNoImg(Course cou);
	
	@Cacheable("course")
	 Course getById(Integer id);
	
	@CacheEvict(value="course")
	 void delete(Integer id);
	
	Page<Course> selectCourses(String start, String pageSize,
                               String orderColumn, String orderDir, String searchValue);
	
	String checkCnoExist(String cno);
	
	String checkCnoExistForUpdate(Course course);
}
