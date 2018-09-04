package com.sc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sc.pojo.Student;

public interface StudentMapper {
	//新增一个学生
//	 @InsertProvider(type=StudentSqlProvider.class,method="insertOne")
	@Insert("insert into student values(se_student.nextval,#{stuno},#{name},#{birthday,javaType=java.sql.Date,jdbcType=DATE},"
			+ "#{country},#{hobby,typeHandler=com.sc.common.MyBatisArrayStringTypeHandler},#{sex})")
//	 @Insert("insert into student(id,stuno) values(12,'9999')")
	 void insertOne(Student student);
	
	 @Update("update student set stuno=#{stuno},name=#{name},birthday=#{birthday,javaType=java.sql.Date,jdbcType=DATE},"
	 		+ "country=#{country},hobby=#{hobby,typeHandler=com.sc.common.MyBatisArrayStringTypeHandler},sex=#{sex} "
	 		+ "where id=#{id}") 
	 void updateOne(Student student);
	 
	@Results(@Result(property="hobby",column="hobby",typeHandler=com.sc.common.MyBatisArrayStringTypeHandler.class))
     @Select("select * from student where id=#{id}")
     Student selectById(Integer id);
     
     @Delete("delete from student where id=#{id}")
     void deleteOne(Integer id);
     
     @Select("select id from student where stuno=#{stuno}")
     int selectByStuno(String stuno);
     
     /**
      * 多表连接查询
      * @return
      */
     List<Student> selectAllStudentCourseScore();
    
     @Select("select count(1) from student where stuno = #{stuno}")
     int getCountByStuno(String stuno);
}
