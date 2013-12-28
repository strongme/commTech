package org.strongme.commtech;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.strongme.commtech.bean.CourseBean;
import org.strongme.commtech.service.CourseService;

@Controller
@RequestMapping("course")
public class CourseController {
	
	@Resource
	private CourseService courseService;
	
	@RequestMapping(value="/query",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody List<CourseBean> query() {
		List<CourseBean> result = courseService.getAll();
		return result;
	}

}
