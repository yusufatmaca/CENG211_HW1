public class DropShippingApp {

	public static void main(String[] args) {
	
		SalesQuery s = new SalesQuery();
		s.calculateProfit(s.salesManagement.getSales()[0], s.salesManagement.supplier1);
		
		/*
		 * SalesManagement salesManagement = new SalesManagement(new FileIO("S1_Sales.csv"), new FileIO("S2_Sales.csv"),
				new FileIO("S3_Sales.csv"));
		System.out.println(salesManagement.getSales()[1][0].getSalesPrice() + salesManagement.getSales()[1][0].getSalesDate());
	
		 */
		}

}
