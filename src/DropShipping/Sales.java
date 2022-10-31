package DropShipping;

public class Sales {
	
	private String id;
	private String customer;
	private String productID;//id
	private String salesDate;
	private String salesPrice;
	
	
	public Sales(String id, String customer, String product, String salesDate, String salesPrice) {
		setId(id);
		setCustomer(customer);
		setProduct(product);
		setSalesDate(salesDate);
		setSalesPrice(salesPrice);
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getProduct() {
		return productID;
	}
	public void setProduct(String product) {
		this.productID = product;
	}
	public String getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
	public String getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}
}
