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
import com.sc.mapper.CourseMapper;
import com.sc.pojo.Course;
import com.sc.service.CourseServiceIn;
@Service//Bean的名称默认为类名首字母小写
public class CourseService implements CourseServiceIn{
	//slf4j和log4j日志系统
	private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
	@Inject
	private CourseMapper couMapper;
	
	@Override
	public Course addOne(Course cou) {
		// TODO Auto-generated method coub
		logger.debug("cou="+cou);
		couMapper.insertOne(cou);
		cou.setId(couMapper.selectByCno(cou.getCno()));
		return cou;
	}

	@Override
	public Course updateOne(Course cou) {
		couMapper.updateOne(cou);
		return cou;
	}
	
	@Override
	public Course updateOneNoImg(Course cou) {
		couMapper.updateOneNoImg(cou);
		return cou;
	}

	@Override
	public Course getById(Integer id) {
		// TODO Auto-generated method coub
		return couMapper.selectById(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method coub
		couMapper.deleteOne(id);
	}
   
    /**
     * 校验课程编号是否已存在
     * @return
     */
    public String checkCnoExist(String cno) {
	   int count = couMapper.getCountByCno(cno);
	   if(count > 0) {
		   return Code.ERROR_CNO_EXIST;
	   }
	   else 
	      return Code.SUCCESS;
   }

	@Override
	public Page<Course> selectCourses(String start, String pageSize, String orderColumn, String orderDir,
			String searchValue) {
		// TODO Auto-generated method stub
		Page<Course> page = PageHelper.startPage(Integer.parseInt(start)/Integer.parseInt(pageSize)+1,Integer.parseInt(pageSize));
		if(null != orderColumn) {
			page.setOrderBy(orderColumn+" "+orderDir);
		}
		couMapper.selectCourses(searchValue);
		return page;
	}

	@Override
	public String checkCnoExistForUpdate(Course course) {
		 int count = couMapper.getCountByCnoAndNotId(course);
		   if(count > 0) {
			   return Code.ERROR_CNO_EXIST;
		   }
		   else 
		      return Code.SUCCESS;
	}

}
