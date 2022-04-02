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
  /* �� �������Ͽ� XML���� ������� TX�� AOP�� �����Ű��...  */ 
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
		dao.addPay(paymentInfo);  //Tx�� DML1
		
		//int num = 5/0;
		
		OrderInfo order = new OrderInfo();
		order.setItem_id(item.getItem_id());
		order.setPay_id(paymentInfo.getPay_id());
		order.setCostomer_id(orderRequest.getCostomer_id());
		order.setAddress(orderRequest.getAddress());
		dao.addOrderInfo(order); ////Tx�� DML2
		
		return new OrderInfoResult(item, paymentInfo, order);
	}
	
}
