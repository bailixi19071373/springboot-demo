package com.sc.controller;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.common.Code;
import com.sc.common.JSONResult;
import com.sc.pojo.Student;
import com.sc.service.StudentServiceIn;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

	@Inject
	private StudentServiceIn stuService;

	@RequestMapping("/query")
	public String query(Student condition, Model model) {
		log.info("--------------查询学生信息-----------");
		try {
			List<Student> list = stuService.selectAllStudentCourseScore();
			model.addAttribute("isOk", true);
			model.addAttribute("list", list);
			return "student/stu_mng";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("isOk", false);
			model.addAttribute("info", "系统异常");
			return "student/stu_mng";
		}
	}

	@RequestMapping("/preadd")
	public String preadd() {
		return "student/stu_add";
	}

	@RequestMapping("/add")
	public String add(Student stu, Model model) {
		try {
			stuService.addOne(stu);
			model.addAttribute("isOk", true);
			model.addAttribute("info", "添加成功");
			return "student/stu_add";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("isOk", false);
			model.addAttribute("info", "系统异常");
			return "student/stu_add";
		}
	}

	@RequestMapping("/premod")
	public String premod(Integer id, Model model) {
		try {
			Student stu = stuService.getById(id);
			model.addAttribute("s", stu);
			return "student/stu_mod";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("info", "系统异常");
			return "student/stu_mod";
		}
	}

	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		try {
			Student stu = stuService.getById(id);
			model.addAttribute("s", stu);
			return "student/stu_detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("info", "系统异常");
			return "student/stu_mng";
		}
	}

	@RequestMapping("/mod")
	public String mod(Student stu, Model model) {
		try {
			stu = stuService.updateOne(stu);
			model.addAttribute("s", stu);
			model.addAttribute("isOk", true);
			model.addAttribute("info", "修改成功");
			return "student/stu_mod";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("info", "系统异常");
			return "student/stu_mod";
		}
	}

	@RequestMapping("/del")
	public String del(Integer[] id, Model model) {
		System.out.println(Arrays.toString(id));
		try {
			for (Integer i : id) {
				stuService.delete(i);
			}
			return "redirect:/student/query";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("info", "系统异常");
			return "redirect:/student/query";
		}
	}

	/**
	 * 校验学号是否已存在，返回json格式
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkstuno", method = { RequestMethod.POST, RequestMethod.GET })
	public JSONResult checkstuno(String stuno) {
		JSONResult jr = new JSONResult();
		try {
			String result = stuService.checkStunoExist(stuno);
			if (result.equals(Code.SUCCESS)) {
				// 传到页面的json格式为{success:true}
				jr.setOk(true);
			    jr.setInfo("该学号可用");
			}
			else {
				jr.setOk(false);
				jr.setInfo("该学号已被占用");
			}
		} catch (Exception e) {
			jr.setOk(false);
			jr.setInfo("系统异常");
		}
		return jr;
	}
}
