package com.sc.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sc.common.Code;
import com.sc.mapper.StudentMapper;
import com.sc.pojo.Student;
import com.sc.service.StudentServiceIn;
@Service//Bean的名称默认为类名首字母小写
public class StudentService implements StudentServiceIn{
	//slf4j和log4j日志系统
	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
	@Inject
	private StudentMapper studentMapper;
	
	@Override
	public Student addOne(Student stu) {
		// TODO Auto-generated method stub
		logger.debug("stu="+stu);
		studentMapper.insertOne(stu);
		stu.setId(studentMapper.selectByStuno(stu.getStuno()));
		return stu;
	}

	@Override
	public Student updateOne(Student stu) {
		studentMapper.updateOne(stu);
		return stu;
	}
	

	@Override
	public Student getById(Integer id) {
		// TODO Auto-generated method stub
		return studentMapper.selectById(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		studentMapper.deleteOne(id);
	}
   
	 /**
     * 多表连接查询
     * @return
     */
    public List<Student> selectAllStudentCourseScore(){
    	List<Student> list = studentMapper.selectAllStudentCourseScore();
    	return list;
    }

    /**
     * 校验学号是否已存在
     * @return
     */
    public String checkStunoExist(String stuno) {
	   int count = studentMapper.getCountByStuno(stuno);
	   if(count > 0) {
		   return Code.ERROR_STUNO_EXIST;
	   }
	   else 
	      return Code.SUCCESS;
   }
}
