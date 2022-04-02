package lab.spring.tx.model;

/* �ֹ� ó���� ����� View�� �����ϱ� ���� Command ��ü */
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
