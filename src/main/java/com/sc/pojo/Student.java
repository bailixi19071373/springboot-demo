package com.sc.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
//@Alias("Student")//别名
@Data
@EqualsAndHashCode
public class Student implements Serializable{
	
	private Integer id;

	private String stuno;
	
	private String name;
	
	private Integer sex;
	
	private java.sql.Date birthday;
	
	private Integer country;
	
	private String[] hobby;
	
	private List<Course> courses;
	
	private List<Score> scores;
	

	@Override
	public String toString() {
		return "Student [id=" + id + ", stuno=" + stuno + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday
				+ ", country=" + country + ", hobby=" + Arrays.toString(hobby) + "]";
	}
}
