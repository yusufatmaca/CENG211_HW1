public class DropShippingApp {

	public static void main(String[] args) {
	
		SalesQuery s = new SalesQuery();
		s.calculateProfit(s.salesManagement.getSales()[0], s.salesManagement.supplier1);
	}

}
