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
		//�ʼ��Ӽ�
		UserVO vo = (UserVO)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userid", "required");
		//errors�� ���� �޽��� ������ ��ü
		//errorCode�� �ش��ϴ� �޽����� ���ҽ� ���Ͽ��� �о�ͼ� ����
		//errorCode�� �ش��ϴ� �޽����� �������� ������ �⺻errorCode �޽����� �о�ͼ� ����
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userpwd", "required");
		
	}

}
