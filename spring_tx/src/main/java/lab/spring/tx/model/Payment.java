package lab.spring.tx.model;

public class Payment {
	private int pay_id;
	private int item_id;
	private int price;
	private String costomer_id;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCostomer_id() {
		return costomer_id;
	}

	public void setCostomer_id(String costomer_id) {
		this.costomer_id = costomer_id;
	}

	@Override
	public String toString() {
		return "Payment [pay_id=" + pay_id + ", item_id=" + item_id
				+ ", price=" + price + ", costomer_id=" + costomer_id + "]";
	}
}
