package org.strongme.commtech;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.strongme.commtech.bean.StatisticBean;
import org.strongme.commtech.bean.StudentCommCountBean;
import org.strongme.commtech.bean.TeacherScoreStatisticBean;
import org.strongme.commtech.service.StatisticService;
import org.strongme.commtech.service.TeacherScoreStatisticService;

@Controller
@RequestMapping("statistic")
public class StatisticController {
	
	@Resource
	private StatisticService statisticService;
	@Resource
	private TeacherScoreStatisticService teacherScoreStatisticService;
	
	
	@RequestMapping(value="/census",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody List<StatisticBean> census() {
		List<StatisticBean> result = statisticService.census();
		return result;
	}
	
	@RequestMapping(value="/censusTeacher",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody List<TeacherScoreStatisticBean> censusTeacher(@RequestParam String courseid) {
		List<TeacherScoreStatisticBean> result = teacherScoreStatisticService.census(courseid);
		return result;
	}
	
	@RequestMapping(value="/censusCommCount",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody List<StudentCommCountBean> censusCommCount() {
		List<StudentCommCountBean> result = teacherScoreStatisticService.cencusCommCount();
		return result;
	}
	

}
