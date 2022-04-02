package lab.spring.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lab.spring.model.SimpleMessage;
import lab.spring.service.HelloService;
import lab.spring.service.HelloServiceImpl;

//3. 자바클래스로 연결하는 방법 클래스에 어노테이션

//설정정보 저장하는 어노테이션
@Configuration
public class AppConfig {
	
	@Bean
	public HelloService hello() {
		SimpleMessage msg = new SimpleMessage();
		HelloServiceImpl service= new HelloServiceImpl();
		service.setMsg(msg);
		return service;
	}	
}


