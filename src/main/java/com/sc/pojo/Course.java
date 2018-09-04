package com.sc.pojo;

import java.io.Serializable;

public class Course implements Serializable{

	private Integer id;
	
	private String cno;
	
	private String cname;
	
	private Integer points;
	
	//某个人某门课对应的成绩数据
    private Integer score;
    
    private String img;
    
    
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Course(Integer id, String cno, String cname, Integer points, Integer score) {
		super();
		this.id = id;
		this.cno = cno;
		this.cname = cname;
		this.points = points;
		this.score = score;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", cno=" + cno + ", cname=" + cname + ", points=" + points + ", score=" + score
				+ "]";
	}

	public Course(Integer id, String cno, String cname, Integer points) {
		super();
		this.id = id;
		this.cno = cno;
		this.cname = cname;
		this.points = points;
	}

	public Course(String cno, String cname, Integer points) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.points = points;
	}

	public Course() {
		super();
	}
	
	
}
