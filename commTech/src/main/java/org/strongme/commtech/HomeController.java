package org.strongme.commtech;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.strongme.commtech.bean.StudentBean;
import org.strongme.commtech.service.StudentService;

@Controller
public class HomeController {
	
	@Resource
	private StudentService studentService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "signin";
	}
	
	@RequestMapping(value = "/sign/{page}", method = RequestMethod.GET)
	public String sign(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping(value="/isExist",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody Boolean isExist(@RequestParam String studentid) {
		Boolean result = false;
		result = studentService.isExist(studentid);
		return result;
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String signup(@ModelAttribute("from") StudentBean form
			,BindingResult results,HttpServletRequest request) {
		int result = studentService.save(form);
		if(result<0) {
			return "redirect:/sign/signup";
		}else {
			request.setAttribute("student", form);
			return "index";
		}
	}

	@RequestMapping(value="/signin",method=RequestMethod.POST)
	public String signin(@ModelAttribute("from") StudentBean form
			,BindingResult results,HttpServletRequest request) {
		StudentBean bean = studentService.get(form.getStudentid());
		if(bean==null) {
			request.setAttribute("tips", "该用户不存在");
			return "signin";
		}else if(bean!=null&&!form.getStudentpassword().equals(bean.getStudentpassword())) {
			request.setAttribute("tips", "密码错误请确认后重新输入");
			return "signin";
		}else {
			if("admin".equals(form.getStudentid())&&bean.getStudentpassword().equals(form.getStudentpassword())) {
				return "statistic";
			}
			request.setAttribute("student", bean);
			return "index";
		}
	}
	
}
