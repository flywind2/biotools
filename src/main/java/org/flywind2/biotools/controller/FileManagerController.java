/**
 * 
 */
package org.flywind2.biotools.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SuFeng
 * 文件管理控制器
 */
@Controller
public class FileManagerController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public String upload(){
		return "upload";
	}
	
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file){
		try {
			log.info("File name is "+ file.getOriginalFilename());
			File dest = new File("D:\\"+file.getOriginalFilename());
			FileCopyUtils.copy(file.getBytes(), dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("exception " ,e);
			e.printStackTrace();
			
		}
		return "redirect:/";
	}
	
	
	
}
