package org.strongme.commtech;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.strongme.commtech.bean.ExtraCommBean;
import org.strongme.commtech.bean.WrapExtraCommBean;
import org.strongme.commtech.service.ExtraCommService;

@Controller
@RequestMapping("extra")
public class ExtraCommController {
	
	@Resource
	private ExtraCommService extraCommService;
	
	@RequestMapping(value="/query/{start}/{total}",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody WrapExtraCommBean query(@PathVariable int start,@PathVariable long total) {
		if(total==-1) {
			total = extraCommService.countAll();
		}
		List<ExtraCommBean> result = extraCommService.getAll(start,15) ;
		WrapExtraCommBean data = new WrapExtraCommBean();
		data.setTotal(total);
		data.setData(result);
		return data;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "download",method=RequestMethod.GET)  
    public ModelAndView download(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
		List<ExtraCommBean> result = extraCommService.getAll() ;
		List<Object[]> dataInList = new ArrayList<Object[]>();
		for(ExtraCommBean d : result) {
			dataInList.add(new Object[]{d.getClassname(),d.getComment()});
		}
        String realName = "学生意见建议.xls";  
        @SuppressWarnings("rawtypes")
		Map modelData = new HashedMap();
        modelData.put("sheetName", realName);
        modelData.put("headers", new String[]{"班级","建议内容"});
        modelData.put("data", dataInList);
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
        return new ModelAndView("ExcelView", modelData);  
    }  
	
	@RequestMapping(value="/allcount",method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody Long queryCount() {
		long result = extraCommService.countAll();
		return result;
	}
	

}
