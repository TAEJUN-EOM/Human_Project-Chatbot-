package lab.spring.tx.model;

public class ItemNotFoundException extends RuntimeException {
	 
		private Integer item_id;

		public ItemNotFoundException(Integer itemId) {
			super("not found item: id=" + itemId);
			this.item_id = itemId;
		}

		public Integer getItemId() {
			return item_id;
		}
}
