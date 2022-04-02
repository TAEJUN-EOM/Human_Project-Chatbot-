package lab.spring.model;

import org.springframework.stereotype.Component;
// component bean
@Component("msg")
public class SimpleMessage {
	public String geMessage() {
		return "Hello Annoatation 설정 테스트";
	}
}
