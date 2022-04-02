package lab.spring.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.spring.tx.dao.OrderDAO;
import lab.spring.tx.model.Item;
import lab.spring.tx.model.ItemNotFoundException;
import lab.spring.tx.model.OrderInfo;
import lab.spring.tx.model.OrderInfoRequest;
import lab.spring.tx.model.OrderInfoResult;
import lab.spring.tx.model.Payment;

@Service("decTx")
public class OrderServiceImpl  implements OrderService{
  /* 빈 설정파일에 XML선언 기반으로 TX을 AOP로 적용시키는...  */ 
	@Autowired
	private OrderDAO dao;
	
	@Override
	public OrderInfoResult order(OrderInfoRequest orderRequest)
	{
		Item item = dao.getItem(orderRequest.getItem_id());
		if (item ==null)
			throw new ItemNotFoundException(orderRequest.getItem_id());
		
		Payment paymentInfo = new Payment();
		paymentInfo.setPrice(item.getPrice());
		paymentInfo.setItem_id(item.getItem_id());
		paymentInfo.setCostomer_id(orderRequest.getCostomer_id());
		dao.addPay(paymentInfo);  //Tx의 DML1
		
		//int num = 5/0;
		
		OrderInfo order = new OrderInfo();
		order.setItem_id(item.getItem_id());
		order.setPay_id(paymentInfo.getPay_id());
		order.setCostomer_id(orderRequest.getCostomer_id());
		order.setAddress(orderRequest.getAddress());
		dao.addOrderInfo(order); ////Tx의 DML2
		
		return new OrderInfoResult(item, paymentInfo, order);
	}
	
}
