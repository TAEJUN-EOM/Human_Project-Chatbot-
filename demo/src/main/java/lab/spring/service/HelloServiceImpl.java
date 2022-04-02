package lab.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lab.spring.model.SimpleMessage;
//service bean

@Service("hello")
public class HelloServiceImpl implements HelloService{
//	@Autowired //Ÿ���� ��ġ�ϰų� ������Ƽ�� ��ġ�ϴ� bean�� ã�Ƽ� ����
//	private SimpleMessage msg;
	
	private SimpleMessage msg;
	
	public void setMsg(SimpleMessage msg) { //���� ���� �޼���
		this.msg = msg;
	}

	@Override
	public void sayHello() {
		System.out.println(msg.geMessage());
		
	}
	
}
