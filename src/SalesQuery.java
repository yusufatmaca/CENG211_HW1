public class SalesQuery {
	/*
	 * Product mostProfitableProduct; Product mostExpensiveProduct; Customer most;
	 * String Profit;
	 */

	Double[] allProfits;

	SalesManagement salesManagement;

	public SalesQuery() {
		salesManagement = new SalesManagement(new FileIO("S1_Sales.csv"), new FileIO("S2_Sales.csv"),
				new FileIO("S3_Sales.csv"));

	}

	public void calculateProfit(Sales[] sales, Supplier supplier) {
		allProfits = new Double[salesManagement.getSales()[0].length];
		// String dizi lazım
		int counter = 0;
		for (int i = 0; i < sales.length; i++) {
			for (int j = 0; j < supplier.getProducts().length; j++) {
				if (sales[i].getProduct().equals(supplier.getProducts()[j].getId())) {
					double salesPrice = Double.parseDouble(sales[i].getSalesPrice());
					double price = Double.parseDouble(supplier.getProducts()[j].getPrice());
					double profit = salesPrice - price;
					
					
				}

			}
		}
		System.out.println(counter);
		// return allProfits;
	}

}
