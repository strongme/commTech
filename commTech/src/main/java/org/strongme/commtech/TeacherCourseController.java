package org.strongme.commtech;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.strongme.commtech.bean.TeacherCourseBean;
import org.strongme.commtech.service.TeacherService;

@Controller
@RequestMapping("teacherCourse")
public class TeacherCourseController {
	
	@Resource
	private TeacherService teacherService;
	
	@RequestMapping(value="/query",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody List<TeacherCourseBean> queryTc(@RequestParam String teacherid,@RequestParam String teachername,@RequestParam String courseid) {
		List<TeacherCourseBean> result = teacherService.queryByIdAndName(teacherid, teachername,courseid);
		return result;
	}
	
	@RequestMapping(value="/updateTc",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody  Boolean updateTc(@ModelAttribute("form") TeacherCourseBean form,BindingResult results) {
		boolean result = false;
		int  tmp = teacherService.updateTeacherCourse(form);
		if(tmp>0)result=true;
		return result;
	}
	
	@RequestMapping(value="/deleteTc",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody  Boolean updateTc(@RequestParam int id) {
		boolean result = false;
		int  tmp = teacherService.deleteTeacherCourse(id);
		if(tmp>0)result=true;
		return result;
	}
	
	
	@RequestMapping(value="/isExist",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody  Boolean isExist(@RequestParam String courseid,@RequestParam String classid) {
		boolean result = teacherService.isAlreadyExist(courseid, classid);
		return result;
	}
	

}
