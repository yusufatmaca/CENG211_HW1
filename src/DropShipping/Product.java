package DropShipping;

public class Product {
	
	private String id;
	private String title;
	private String rate;
	private String numberOfReviews;
	private String price;
	
	public Product(String id, String title, String rate, String numberOfReviews, String price) {
		setId(id);
		setTitle(title);
		setRate(rate);
		setNumberOfReviews(numberOfReviews);
		setPrice(price);
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getNumberOfReviews() {
		return numberOfReviews;
	}
	public void setNumberOfReviews(String numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}
