package lab.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lab.spring.model.SimpleMessage;
//service bean

@Service("hello")
public class HelloServiceImpl implements HelloService{
//	@Autowired //타입이 일치하거나 프로퍼티가 일치하는 bean을 찾아서 주입
//	private SimpleMessage msg;
	
	private SimpleMessage msg;
	
	public void setMsg(SimpleMessage msg) { //주입 받을 메서드
		this.msg = msg;
	}

	@Override
	public void sayHello() {
		System.out.println(msg.geMessage());
		
	}
	
}
