package lab.spring.tx.service;

import lab.spring.tx.model.ItemNotFoundException;
import lab.spring.tx.model.OrderInfoRequest;
import lab.spring.tx.model.OrderInfoResult;

public interface OrderService {
	public OrderInfoResult order(OrderInfoRequest buyRequest) 
            throws ItemNotFoundException;
}
