public class SalesQuery {
	double profit = 0.0;
	Double[] allProfits;
	SalesManagement salesManagement = new SalesManagement(new FileIO("S1_Sales.csv"), new FileIO("S2_Sales.csv"),
			new FileIO("S3_Sales.csv"));

	public SalesQuery() {
		
	}

	public void calculateProfit(Sales[] sales, Supplier supplier) {
		allProfits = new Double[sales.length];

		for (int i = 0; i < sales.length; i++) {
			for (int j = 0; j < supplier.getProducts().length; j++) {
				if (sales[i].getProduct() == supplier.getProducts()[j].getId()) {
					profit = Double.parseDouble(sales[i].getSalesPrice())
							- Double.parseDouble(supplier.getProducts()[j].getPrice());
					allProfits[i] = profit;
					System.out.println(profit);
				}
			}
		}
		// return allProfits;
	}

}
