package lab.spring.tx.model;

public class OrderInfo {
	private int order_id ;
	private int pay_id  ;
	private int item_id   ;
	private String costomer_id ;
	private String  address ;
	
	
	public int getOrder_id() {
		return order_id;
	}


	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}


	public int getPay_id() {
		return pay_id;
	}


	public void setPay_id(int pay_id) {
		this.pay_id = pay_id;
	}


	public int getItem_id() {
		return item_id;
	}


	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}


	public String getCostomer_id() {
		return costomer_id;
	}


	public void setCostomer_id(String costomer_id) {
		this.costomer_id = costomer_id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "OrderInfo [order_id=" + order_id + ", pay_id=" + pay_id
				+ ", item_id=" + item_id + ", costomer_id=" + costomer_id
				+ ", address=" + address + "]";
	}
}
