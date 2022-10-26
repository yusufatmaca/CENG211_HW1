public class SalesQuery {
	double result = 0.0;
	SalesManagement salesManagement = new SalesManagement(new FileIO("S1_Sales.csv"), new FileIO("S2_Sales.csv"),
			new FileIO("S3_Sales.csv"));

	public SalesQuery() {
		result = Double.parseDouble(salesManagement.getSales()[0][1].getSalesPrice())
				- Double.parseDouble(salesManagement.supplier1.getProducts()[1].getPrice());
		
	}
	

}
