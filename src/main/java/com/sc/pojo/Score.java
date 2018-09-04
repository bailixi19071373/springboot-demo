package com.sc.pojo;

import java.io.Serializable;

public class Score implements Serializable {

	private Integer id;
	
	private Integer sid;
	
	private Integer cid;
	
	private Student s;
	
	private Course c;
	
	private Integer score;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public Course getC() {
		return c;
	}

	public void setC(Course c) {
		this.c = c;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", s=" + s + ", c=" + c + ", score=" + score + "]";
	}

	public Score(Integer id, Student s, Course c, Integer score) {
		super();
		this.id = id;
		this.s = s;
		this.c = c;
		this.score = score;
	}

	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Score(Student s, Course c, Integer score) {
		super();
		this.s = s;
		this.c = c;
		this.score = score;
	}
	
	
}
