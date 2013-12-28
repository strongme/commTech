package org.strongme.commtech;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DownloadUtil {
	
	 public static void download(HttpServletRequest request,  
	            HttpServletResponse response, String contentType,  
	            String realName,File file) throws Exception {  
	        request.setCharacterEncoding("UTF-8");  
	        BufferedOutputStream bos = null;  
	        response.setContentType(contentType);  
	        response.setHeader("Content-disposition", "attachment; filename="  
	                + new String(realName.getBytes("utf-8"), "ISO8859-1"));  
	        
	        response.setHeader("Content-Length", ""+file.length());  
	        bos = new BufferedOutputStream(response.getOutputStream());  
	        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
	        byte[] buf = new byte[1024];
	        while(in.read(buf)!=-1) {
	        	bos.write(buf);
	        	buf = null;
	        	buf = new byte[1024];
	        }
	        in.close();
	        bos.close();  
	        if(file.exists())file.delete();
	    }  
	 
	 public static File generateExcel(List<Object[]> data,String[] head,String sheetName) {
		 HSSFWorkbook workbook = new HSSFWorkbook();
		 HSSFSheet sheet = workbook.createSheet(sheetName);
		 int i=0;
		 Row headRow = sheet.createRow(i++);
		 int iHead = 0;
		 for(String s : head) {
			 Cell hc = headRow.createCell(iHead++);
			 hc.setCellValue(s);
		 }	
		 for(Object[] d : data) {
			 Row row = sheet.createRow(i++);
			 int j = 0;
			 for(Object o : d) {
				 Cell c = row.createCell(j++);
				 if( o instanceof Integer) {c.setCellValue((Integer)o);}
				 else if(o instanceof Double) {c.setCellValue((Double)o);}
				 else if(o instanceof Float) {c.setCellValue((Float)o);}
				 else if(o instanceof Long) {c.setCellValue((Long)o);}
				 else if(o instanceof String){c.setCellValue((String)o);}
			 }
		 }
		 String time = new Date().getTime()+"";
		 File out = new File("C:/"+time+".xls");
		 FileOutputStream outStream = null;
		 try {
			 outStream = new FileOutputStream(out);
			workbook.write(outStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
				try {
					if(outStream!=null)
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		 return out;
	 }
	 

}
