package org.strongme.commtech;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.strongme.commtech.bean.ClassBean;
import org.strongme.commtech.service.ClassService;

@Controller
@RequestMapping("class")
public class ClassController {
	
	@Resource
	private ClassService classService;
	
	@RequestMapping(value="/query",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody List<ClassBean> query() {
		List<ClassBean> result = classService.get();
		return result;
	}

}
