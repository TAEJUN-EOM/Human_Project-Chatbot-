package lab.spring.tx.model;

/*Controller���� ������ �ֹ����� Command��ü */
public class OrderInfoRequest {
	private Integer item_id;
	private String  address;
	private String  costomer_id;
	
	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
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
}
