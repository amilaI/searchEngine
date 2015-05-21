package com.searchengine.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.searchengine.content.upload.FileValidator;
import com.searchengine.content.upload.UploadedFile;


/**
 * Engineering Dashboard (EDB) - Always Learning Module
 * Content Search Engine 
 * 
 * ContentRetrieveController will receive the incoming Content (Uploaded Files and content) and store
 * them in Centralized Document Repository 
 *  
 * @author Amila Iddamalgoda - amila.iddamalgoda@pearson.com
 * @version 1.0
 * @since 10/05/2014
 */

@Controller
@RequestMapping("/User")
public class ContentRetrieveController {
	
	@Autowired
	FileValidator fileValidator;	
	
	static final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;

	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionFramework(Exception e) {
		 ModelAndView modelAndView = new ModelAndView("error");
		 final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     e.printStackTrace(pw);
		 modelAndView.addObject("exception", e +  "  " + sw.getBuffer().toString());
		
		return modelAndView;
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public ModelAndView getUploadedFile(HttpServletRequest request , HttpServletResponse response,@ModelAttribute("uploadedFile") UploadedFile uploadedFile,  
			   BindingResult result) throws Exception {
	      		
		  String fileDescription = request.getParameter("description");
		 		  
		  InputStream inputStream = null;  
		  OutputStream outputStream = null;	  
		 
		  
		  MultipartFile file = uploadedFile.getFile();  
		  fileValidator.validate(uploadedFile, result);  
		  
		  String fileName = file.getOriginalFilename();
		  String fileExtension = FilenameUtils.getExtension(fileName);
		  
		  boolean isDoc  = fileExtension.equals("docx");
		  boolean isPdf  = fileExtension.equals("pdf");
		  
		  if(isDoc || isPdf){
			  
			  
			  try {  
			   inputStream = file.getInputStream();  
			  
			   File newFile = new File("/home/amilai/Lucene/Always Learning localhost repository/uploaded" + fileName);  
			   if (!newFile.exists()) {  
			    newFile.createNewFile();  
			   }  
			   outputStream = new FileOutputStream(newFile);  
			   int read = 0;  
			   byte[] bytes = new byte[1024];  
			  
			   while ((read = inputStream.read(bytes)) != -1) {  
			    outputStream.write(bytes, 0, read);  
			   }  
			  } catch (IOException e) {  
			   System.out.println("System encountered an error!"); 
			   e.printStackTrace();  
			  }  
			
			//Create a time based unique id for content
			String fileId=generateId();
			System.out.println(fileId);
			//Indexing data
			
			//Save content to document repository (db)
			
			
			
			ModelAndView modelAndView = new ModelAndView("upload-success");
			modelAndView.addObject("flag", "class=\"alert alert-success\" ");
			modelAndView.addObject("fileName", fileName);
			modelAndView.addObject("status", "Well done ! ");
			modelAndView.addObject("message", " uploaded successfully to the Central Document Repository");
			return modelAndView;
			  
			  
		  }else{
			  ModelAndView modelAndView = new ModelAndView("upload-success");
			  modelAndView.addObject("flag", "class=\"alert alert-danger\" ");
			  modelAndView.addObject("status", "Some thing went wrong ! ");
			  modelAndView.addObject("message", " There was a problem uploading your file. Please try again... ");
			  return modelAndView;  
		  }
		  
				
	}	
	
	public String generateId() {

		// Create a unique time id for the content
		EthernetAddress addr = EthernetAddress.fromInterface();
		TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(addr);
		UUID uuid = uuidGenerator.generate();

		long actualTime = (uuid.timestamp() - NUM_100NS_INTERVALS_SINCE_UUID_EPOCH) / 10000;
		Date date = new Date(actualTime);
		SimpleDateFormat ft = new SimpleDateFormat(
				"E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		System.out.println("Time UUID is " + uuid + "  Actual time "
				+ ft.format(date));
		String id = uuid.toString();
		return id;
	}

	/*	public static void main(String[] args) {
		 POIFSFileSystem fs = null;
	        try {
	           
	            if (fileName.endsWith(".xls")) { //if the file is excel file
	                ExcelExtractor ex = new ExcelExtractor(fs);
	                return ex.getText(); //returns text of the excel file
	            } else if (fileName.endsWith(".ppt")) { //if the file is power point file
	                PowerPointExtractor extractor = new PowerPointExtractor(fs);
	                return extractor.getText(); //returns text of the power point file

	            }
	            
	            //else for .doc file
	            fs = new POIFSFileSystem(new FileInputStream(new File("/home/amila/test")));
	            HWPFDocument doc = new HWPFDocument(fs);
	            WordExtractor we = new WordExtractor(doc);
	            System.out.println(we.getText());
	        } catch (Exception e) {
	            System.out.println("document file cant be indexed");
	        }
	}*/
}
