package com.sc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sc.pojo.Course;

public interface CourseMapper {
	@Insert("insert into course values(se_course.nextval,#{cno},#{cname},#{points},"
			+ "#{img,javaType=String,jdbcType=VARCHAR})")
	 void insertOne(Course course);
	
	@Update("update course set cno=#{cno},cname=#{cname},points=#{points},"
			+ "img=#{img,javaType=String,jdbcType=VARCHAR} "
			+ "where id=#{id}")
	 void updateOne(Course course);
	
	@Update("update course set cno=#{cno},cname=#{cname},points=#{points} "
			+ "where id=#{id}")
	 void updateOneNoImg(Course course);
	 
     @Select("select * from course where id=#{id}")
     Course selectById(Integer id);
     
     @Delete("delete from course where id=#{id}")
     void deleteOne(Integer id);
     
     @Select("select id from course where cno=#{cno}")
     int selectByCno(String cno);
     
     @Select("select * from course")
     List<Course> selectAllCourses();
    
     @Select("select count(1) from course where cno = #{cno}")
     int getCountByCno(String cno);
     
     @Select("select count(1) from course where cno = #{cno} and id != #{id}")
     int getCountByCnoAndNotId(Course course);
     
     List<Course> selectCourses(String condition);
}
