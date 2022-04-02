package lab.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lab.spring.model.UserVO;
import lab.spring.service.UserService;

@Controller
public class LoginController {
   @Autowired
   UserService service;
   
//   @RequestMapping(value = "/login.do", method=RequestMethod.GET)
   public  String form(){
      return "login";
   }
//   @RequestMapping(value = "/login.do", method=RequestMethod.POST)
   public ModelAndView login(@RequestParam ("userid")String uid, @RequestParam ("userpwd")String upwd ) {
      
      ModelAndView mav = new ModelAndView();
      UserVO vo = null;
      vo = service.login(uid, upwd);
      if(vo != null) {
         mav.addObject("user", vo);
         mav.setViewName("loginSuccess");
      }else {
         mav.addObject("userid", uid);
         mav.setViewName("loginFail");
      }
      return mav;
      
   }
}

// 이거 필요없는 서블렛이라 주석처리 해두긴 했는데 정말 필요없어욬ㅋㅋㅋㅋㅋ