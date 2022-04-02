package lab.spring.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import lab.spring.model.ReportCommand;

@Controller
public class UploadController {

	@RequestMapping(value ="/upload.do" , method=RequestMethod.GET) 
	public String form() {
		return "upload"; 
	}
	
	@PostMapping("/upload.do" ) 
	@ResponseBody
	public ResponseEntity<?> submitReport1(
			@RequestParam("studentNumber") String studentNumber,
			@RequestParam("report") MultipartFile report) {		    
		   if(report.getSize()==0) {
				return new ResponseEntity("파일을 선택하세요.", HttpStatus.OK);
			}
		   uploadProcess(studentNumber, report );
		    
		 return  new ResponseEntity("업로드 성공"+report.getOriginalFilename(),
				 new HttpHeaders(), HttpStatus.OK);
	}
	
	private void uploadProcess(String studentNumber,MultipartFile report) {
		if (!report.isEmpty()) {
			String filename = report.getOriginalFilename();
			String imgExt = filename.substring(filename.lastIndexOf(".") 
					+ 1, filename.length());
			try {
				// upload 처리
				if (imgExt.equalsIgnoreCase("JPG")
						|| imgExt.equalsIgnoreCase("JPEG")
						|| imgExt.equalsIgnoreCase("GIF")
						|| imgExt.equalsIgnoreCase("PNG")) {
					byte[] bytes = report.getBytes();
					File outFile = new File("d://upload/" + "_" + filename);
					FileOutputStream fos = new FileOutputStream(outFile);
					fos.write(bytes);
			        fos.close();	
				} else {
					System.err.println("File type error! ");
				}
				System.out.println(studentNumber + " 제출된 보고서: "
						+ report.getOriginalFilename());
			}catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public String  handleException(NullPointerException exception){
		return "common/error";	//뷰 이름 리턴	
	}
	
	
	
	 
	
	
}
