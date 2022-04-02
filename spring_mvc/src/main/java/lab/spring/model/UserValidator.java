package lab.spring.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {		 
		return UserVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//필수속성
		UserVO vo = (UserVO)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userid", "required");
		//errors은 에러 메시지 저장할 객체
		//errorCode에 해당하는 메시지를 리소스 파일에서 읽어와서 저장
		//errorCode에 해당하는 메시지가 존재하지 않으면 기본errorCode 메시지를 읽어와서 저장
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userpwd", "required");
		
	}

}
