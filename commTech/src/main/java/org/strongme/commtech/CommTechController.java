package org.strongme.commtech;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.strongme.commtech.bean.ComboBean;
import org.strongme.commtech.bean.StudentCommBean;
import org.strongme.commtech.service.ExtraCommService;
import org.strongme.commtech.service.StudentCommService;

@Controller
@RequestMapping("comm")
public class CommTechController {
	
	@Resource
	private StudentCommService studentCommService;
	@Resource 
	private ExtraCommService extraCommService;
	
	@RequestMapping(value="/save",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody String save(@RequestBody ComboBean param) {
		String result = "";
		List<StudentCommBean> data = Arrays.asList(param.getScs());
		boolean r1 = studentCommService.save(data);
		int r2=-1;
		if(param.getExtra()!=null&&(param.getExtra().getComment()==null||"".equals(param.getExtra().getComment()))) {
			r2 = 1;
		}else {
			r2 = extraCommService.save(param.getExtra());
		}
		if(r1&&r2>0) {
			result = "保存成功";
		}else if(!r1&&r2<0) {
			result = "保存失败";
		}else if(!r1){
			result = "评分保存失败";
		}else if(r2<0) {
			result = "文字评价建议保存失败";
		}
		return result;
	}
	
	@RequestMapping(value="/ifComm",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody Boolean ifComm(@RequestParam String studentid) {
		Boolean result = false;
		result = studentCommService.ifComm(studentid);
		return result;
	}
	
	@RequestMapping(value="/query",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody List<StudentCommBean> query(@RequestParam String studentid) {
		List<StudentCommBean> result = studentCommService.get(studentid);
		return result;
	}

}
