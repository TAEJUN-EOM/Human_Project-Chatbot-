package lab.spring.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import lab.spring.model.ReportCommand;

@Controller
public class ReportController {

	@RequestMapping("/report/report.do") 
	public String form() {
		return "report/reportForm"; 
	}
	
	@RequestMapping("/report/submitReport1.do")
	public ModelAndView submitReport1(
			@RequestParam("studentNumber") String studentNumber,
			@RequestParam("report") MultipartFile report) {		    
		   if(report.getSize()==0) {
				throw new NullPointerException("업로드된 파일 없음");
			}
		   uploadProcess(studentNumber, report );
		   ModelAndView mav = new ModelAndView();
		   mav.addObject("student",  studentNumber);
		   mav.addObject("uploadfile", report.getOriginalFilename());
		   mav.setViewName("report/reportComplete");
		 return mav;
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
	
	
	
	@RequestMapping("/report/submitReport2.do")
	public ModelAndView submitReport2(MultipartHttpServletRequest request ) {	
		  String sno = request.getParameter("studentNumber");
		  MultipartFile report=request.getFile("report");
		   if(report.getSize()==0) {
				throw new NullPointerException("업로드된 파일 없음");
			}
		   uploadProcess(sno, report );
		   ModelAndView mav = new ModelAndView();
		   mav.addObject("student",  sno);
		   mav.addObject("uploadfile", report.getOriginalFilename());
		   mav.setViewName("report/reportComplete");
		 return mav;
	}
	
	@RequestMapping("/report/submitReport3.do")
	public ModelAndView submitReport3(ReportCommand reportCommand ) {	
		   
		   if(reportCommand.getReport().getSize()==0) {
				throw new NullPointerException("업로드된 파일 없음");
			}
		   uploadProcess(reportCommand.getStudentNumber(), reportCommand.getReport() );
		   ModelAndView mav = new ModelAndView();
		   mav.addObject("student",  reportCommand.getStudentNumber());
		   mav.addObject("uploadfile", reportCommand.getReport().getOriginalFilename());
		   mav.setViewName("report/reportComplete");
		 return mav;
	}
	
	
	
	
}
