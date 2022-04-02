package lab.spring.service;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import lab.spring.model.UserDAO;

@Service("loginService")
public class UserServiceImpl implements UserService, ApplicationContextAware {
	private UserDAO dao;
	private ApplicationContext context;
		
		@Autowired
		public void setDao(UserDAO dao) {
		this.dao = dao;
	}


// 로그인 서공 및 실패시에 메시지파일에서 출력 메시지 읽기 위해 스프링 컨테이너 객체가 필요하므로 
//		스프링 컨테이너 객체를 주입 받기 위해 ApplicationContextAware를 implements함
	@Autowired	
	public void setApplicationContext(ApplicationContext context) throws BeansException{
		this.context = context;
	}
		
	@Override
	public String[] login(String userid, String userpwd) {
		String messages[] =  new String[2];
		Object[] args = new String[ ] {userid};
		Locale locale = Locale.getDefault();		
		Locale locale_en =  Locale.ENGLISH;
		
		if(dao.loginProc(userid, userpwd)) {
			messages[0] = context.getMessage("login.success",args, locale);
			messages[1] = context.getMessage("login.success",args, locale_en);
				
		} else {
			messages[0] = context.getMessage("login.fail",args, locale);
			messages[1] = context.getMessage("login.fail",args, locale_en);
		}
				
		return messages;
	}

}
