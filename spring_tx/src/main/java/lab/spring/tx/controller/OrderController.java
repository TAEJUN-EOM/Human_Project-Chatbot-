package lab.spring.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lab.spring.tx.model.ItemNotFoundException;
import lab.spring.tx.model.OrderInfoRequest;
import lab.spring.tx.model.OrderInfoResult;
import lab.spring.tx.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService service;	

	@RequestMapping(value="/order.do", method=RequestMethod.GET)
	protected String form() {
		return "orderForm";
	}
	
	@RequestMapping(value="/order.do", method=RequestMethod.POST)
	protected ModelAndView submit(OrderInfoRequest command) {
		ModelAndView mav = new ModelAndView();
		OrderInfoResult orderResult = null;
		orderResult = service.order(command);
		mav.setViewName("orderComplete");
		mav.addObject("orderResult", orderResult);
		return mav;
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	public String  handleException(NullPointerException exception){
		return "common/error";	//∫‰ ¿Ã∏ß ∏Æ≈œ	
	}
	
	
	
			
}
