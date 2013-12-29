package org.strongme.commtech.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sheetName = (String) model.get("sheetName");
		String[] headers = (String[]) model.get("headers");
		List<Object[]> data = (List<Object[]>) model.get("data"); 
		HSSFSheet excelSheet = workbook.createSheet(sheetName);
	    setExcelHeader(excelSheet,headers);
	    setExcelRows(excelSheet, data);
	}
	
	 public void setExcelHeader(HSSFSheet excelSheet,String[] headerName) {
		    HSSFRow excelHeader = excelSheet.createRow(0);
		    for(int i=0;i<headerName.length;i++) {
		    	excelHeader.createCell(i).setCellValue(headerName[i]);
		    }
	  }
	 
	public void setExcelRows(HSSFSheet excelSheet, List<Object[]> data) {
		int record = 1;
		for (Object[] objs : data) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			int j = 0;
			for (Object o : objs) {
				Cell c = excelRow.createCell(j++);
				if (o instanceof Integer) {
					c.setCellValue((Integer) o);
				} else if (o instanceof Double) {
					c.setCellValue((Double) o);
				} else if (o instanceof Float) {
					c.setCellValue((Float) o);
				} else if (o instanceof Long) {
					c.setCellValue((Long) o);
				} else if (o instanceof String) {
					c.setCellValue((String) o);
				}
			}
		}
	}
}
