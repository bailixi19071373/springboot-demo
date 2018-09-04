package com.sc.controller;

import java.io.File;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.sc.common.Code;
import com.sc.common.JSONResult;
import com.sc.json.CourseDataTableJSON;
import com.sc.pojo.Course;
import com.sc.service.CourseServiceIn;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Value("${upload.course.location}")
	private String uploadLocation;

	@Inject
	private CourseServiceIn couService;

	@RequestMapping("/toquery")
	public String toquery() {
		return "course/cou_mng";
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public CourseDataTableJSON query(int draw,String start,String length,
			@RequestParam(name="order[0][column]")String orderColumn, 
			@RequestParam(name="order[0][dir]")String orderDir,
			@RequestParam(name="search[value]")String searchValue,
			Model model) {
		try {
			System.out.println("orderColumn="+orderColumn);
			System.out.println("start="+start);
			System.out.println("length="+length);
			System.out.println("searchValue="+searchValue);
			 //定义列名
		    String[] cols = {null, "cno", "cname", "points", null};
		    orderColumn = null == orderColumn ? cols[0]: cols[Integer.parseInt(orderColumn)];
		    Page<Course> page = couService.selectCourses(start, length, orderColumn,
		    		orderDir, searchValue);
		    CourseDataTableJSON json = new CourseDataTableJSON();
		    json.setDraw(draw);
		    json.setData(page.getResult());
//		    json.setRecordsTotal(page.getTotal());
		    json.setRecordsFiltered(page.getTotal());
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public JSONResult add(@RequestPart("imgfile") Part img,Course cou, Model model,HttpServletRequest request) {
		try {
//			String realPath = request.getServletContext().getRealPath("");
			String filename = img.getSubmittedFileName();
			System.out.println(img);
			if(filename != null) {
				filename = filename.substring(0, filename.lastIndexOf("."))+
						System.currentTimeMillis()+filename.substring(filename.lastIndexOf("."));
			System.out.println(filename);
			img.write(uploadLocation+ File.separator+filename);
			cou.setImg(uploadLocation+filename);;
			}
			couService.addOne(cou);
			JSONResult jr = new JSONResult();
			jr.setOk(true);
			jr.setInfo("添加成功");
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
			JSONResult jr = new JSONResult();
			jr.setOk(false);
			jr.setInfo("添加失败");
			return jr;
		}
	}
	
	@RequestMapping("/mod")
	@ResponseBody
	public JSONResult mod(@RequestPart("imgfile") Part img,Course cou, Model model,HttpServletRequest request) {
		try {
//			String realPath = request.getServletContext().getRealPath("");
			String filename = img.getSubmittedFileName();
			if(filename != null) {
				filename = filename.substring(0, filename.lastIndexOf("."))+
						System.currentTimeMillis()+filename.substring(filename.lastIndexOf("."));
			System.out.println(filename);
			img.write(uploadLocation+ File.separator+filename);
			cou.setImg(uploadLocation+ File.separator+filename);
			couService.updateOne(cou);
			}
			else {
			//不需要更新img，否则会变成null
			couService.updateOneNoImg(cou);
			}
			JSONResult jr = new JSONResult();
			jr.setOk(true);
			jr.setInfo("修改成功");
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
			JSONResult jr = new JSONResult();
			jr.setOk(false);
			jr.setInfo("修改失败");
			return jr;
		}
	}
	

	@RequestMapping("/detail")
	@ResponseBody
	public Course detail(Integer id) {
		try {
			Course course = couService.getById(id);
			return course;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@RequestMapping("/del")
	public JSONResult del(Integer[] id, Model model) {
		try {
			for (Integer i : id) {
				couService.delete(i);
			}
			JSONResult jr = new JSONResult();
			jr.setOk(true);
			jr.setInfo("删除成功");
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
			JSONResult jr = new JSONResult();
			jr.setOk(false);
			jr.setInfo("删除失败");
			return jr;
		}
	}

	/**
	 * 校验学号是否已存在，返回json格式
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkcno", method = { RequestMethod.POST, RequestMethod.GET })
	public JSONResult checkcno(Course course) {
		System.out.println(course);
		JSONResult jr = new JSONResult();
		try {
			String result = null;
			if(course.getId() == null) {
			 result = couService.checkCnoExist(course.getCno());
			}
			else {
			result = couService.checkCnoExistForUpdate(course);	
			}
			if (result.equals(Code.SUCCESS)) {
				// 传到页面的json格式为{success:true}
				jr.setValid(true);
			}
			else {
				jr.setValid(false);
			}
		} catch (Exception e) {
			jr.setValid(false);			
		}
		return jr;
	}
}
