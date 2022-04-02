package lab.spring.tx.model;

/* 주문 처리된 결과를 View에 전달하기 위한 Command 객체 */
public class OrderInfoResult {
	private Item item;
	private Payment paymentInfo;
	private OrderInfo orderInfo;
	
	public OrderInfoResult(Item item, Payment paymentInfo,
			OrderInfo orderInfo) {
		this.item = item;
		this.paymentInfo = paymentInfo;
		this.orderInfo = orderInfo;
	}

	public Item getItem() {
		return item;
	}

	public Payment getPaymentInfo() {
		return paymentInfo;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
}
