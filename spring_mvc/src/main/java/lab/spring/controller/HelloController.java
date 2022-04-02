package lab.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	 
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Model model, 
			@RequestParam(value="name", required=false)String name) {
    	model.addAttribute("greeting", "안녕하세요,"+name);//요청 처리 결과를 뷰에 전달
    	return "hello"; //뷰이름
    }
	
}
