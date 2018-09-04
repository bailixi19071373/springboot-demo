package com.sc.json;

import java.util.List;

import com.sc.pojo.Course;

public class CourseDataTableJSON {
   private List<Course> data;
   
   private long recordsTotal;
   
   private long recordsFiltered;
   
   private int draw;
   
   private String error;

public List<Course> getData() {
	return data;
}

public void setData(List<Course> data) {
	this.data = data;
}

public long getRecordsTotal() {
	return recordsTotal;
}

public void setRecordsTotal(long recordsTotal) {
	this.recordsTotal = recordsTotal;
}

public long getRecordsFiltered() {
	return recordsFiltered;
}

public void setRecordsFiltered(long recordsFiltered) {
	this.recordsFiltered = recordsFiltered;
}

public int getDraw() {
	return draw;
}

public void setDraw(int draw) {
	this.draw = draw;
}

public String getError() {
	return error;
}

public void setError(String error) {
	this.error = error;
}


public CourseDataTableJSON() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "CourseDataTableJSON [data=" + data + ", recordsTotal=" + recordsTotal + ", recordsFiltered="
			+ recordsFiltered + ", draw=" + draw + ", error=" + error + "]";
}
   
   
}
