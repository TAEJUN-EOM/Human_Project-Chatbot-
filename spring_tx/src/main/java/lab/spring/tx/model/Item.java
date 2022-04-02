package lab.spring.tx.model;

public class Item {
	private int item_id  ;
	private String  item_name ;
	private int  price ;
	
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name
				+ ", price=" + price + "]";
	}
}
