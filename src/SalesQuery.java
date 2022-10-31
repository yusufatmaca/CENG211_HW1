public class SalesQuery {

	Product mostProfitableProduct;
	Product mostExpensiveProduct;
	String leastProfitOfS1;
	Customer mostPurchasedOne;
	//String totalProfit;
	Double totalProfit1 = 0.0;

	SalesManagement salesManagement = new SalesManagement(new FileIO("S1_Sales.csv"), new FileIO("S2_Sales.csv"),
			new FileIO("S3_Sales.csv"));

	public SalesQuery() {
		System.out.println(mostProfitableProduct(salesManagement).getTitle());
		System.out.println(mostExpensiveProduct(salesManagement).getTitle());
		System.out.println("Total Profit:   " + totalProfit());
		
	}

	public Product mostProfitableProduct(SalesManagement salesManagement) { // Among three supplier

		Product product1 = profitEach(salesManagement.getSales()[0], salesManagement.supplier1); // most profitable
		Product product2 = profitEach(salesManagement.getSales()[1], salesManagement.supplier2); // most profitable
		Product product3 = profitEach(salesManagement.getSales()[2], salesManagement.supplier3); // most profitable
																									// product of S3

		Sales salesOfProduct1 = salesOfProduct(salesManagement.getSales()[0], product1);
		Sales salesOfProduct2 = salesOfProduct(salesManagement.getSales()[1], product2);
		Sales salesOfProduct3 = salesOfProduct(salesManagement.getSales()[2], product3);

		Double profit1 = Double.parseDouble(salesOfProduct1.getSalesPrice()) - Double.parseDouble(product1.getPrice());
		Double profit2 = Double.parseDouble(salesOfProduct2.getSalesPrice()) - Double.parseDouble(product2.getPrice());
		Double profit3 = Double.parseDouble(salesOfProduct3.getSalesPrice()) - Double.parseDouble(product3.getPrice());
		/*
		System.out.println(profit1);
		System.out.println(profit2);
		System.out.println(profit3);
*/
		if (profit1 > profit2 && profit1 > profit3) {
			return product1;
		} else if (profit2 > profit1 && profit2 > profit3) {
			return product2;
		} else {
			return product3;
		}
	}

	public Product mostExpensiveProduct(SalesManagement salesManagement) {	// in term of SalesPrice
		
		Sales maxOne = maxSalesPrice(salesManagement.getSales()[0], salesManagement.getSales()[1], salesManagement.getSales()[2]);
		Product mostExpensiveProduct = null;
		Product[] allProduct = getAllProduct(salesManagement.supplier1.getProducts(), salesManagement.supplier2.getProducts(), salesManagement.supplier3.getProducts());		
		
		for(int i = 0; i < allProduct.length; i++ ) {
			if(maxOne.getProduct().equals(allProduct[i].getId())) {
				mostExpensiveProduct =  allProduct[i];
			}
		}
		return mostExpensiveProduct;
		
	}

	public void leastProfitOfS1() {

	}

	public void mostPurchasedOne() { // the customer

	}

	public Double totalProfit() { // From all sales

		return totalProfit1;
	}

	

	public Product profitEach(Sales[] sales, Supplier supplier) {     //calculates the max profitable product for each sales.

		Product maxProfitableProduct = null;
	
		Double profit = 0.0;
		Double maxProfit = 0.0;

		for (int i = 0; i < sales.length; i++) {
			for (int j = 0; j < supplier.getProducts().length; j++) {
				if (sales[i].getProduct().equals(supplier.getProducts()[j].getId())) {

					Double salesPrice = Double.parseDouble(sales[i].getSalesPrice());
					Double price = Double.parseDouble(supplier.getProducts()[j].getPrice());
					profit = salesPrice - price;
					totalProfit1 += profit;
					// System.out.println(salesManagement.supplier1.getProducts()[j].getId());
					// System.out.println(profit);
					if (profit > maxProfit) {
						maxProfit = profit;
						maxProfitableProduct = salesManagement.supplier1.getProducts()[j];
					}
				}
			}
		}
		
		return maxProfitableProduct;

	}

	public Sales salesOfProduct(Sales[] sales, Product product) {	// gives the sale of the given product

		Sales sales1 = null;
		// double max = 0.0; // another method

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < sales.length; j++) {
				if (salesManagement.getSales()[0][j].getProduct().equals(product.getId())) {
					sales1 = sales[j];
					// max = Double.parseDouble(sales1.getSalesPrice()) -
					// Double.parseDouble(product.getPrice());

				}
			}
		}
		// System.out.println(max);
		return sales1;

	}

	public Sales maxSalesPrice(Sales[] sales1, Sales[] sales2, Sales[] sales3) {	// Gives the sale that is most expensive in term of salesPrice
		int index = sales1.length + sales2.length + sales3.length;
		Sales[] sales = new Sales[index];

		for (int i = 0; i < sales1.length; i++) {
			sales[i] = sales1[i];
		}

		for (int i = 0; i < sales2.length; i++) {
			sales[i + sales1.length] = sales2[i];
		}

		for (int i = 0; i < sales3.length; i++) {
			sales[i + sales1.length + sales2.length] = sales3[i];
		}

		Double max = 0.0; // temporary
		Sales maxSalesPriceSale = null;

		for (int i = 0; i < sales.length; i++) {
			// System.out.println(sales[i].getSalesPrice());
			Double salesPrice = Double.parseDouble(sales[i].getSalesPrice());
			//System.out.println(salesPrice + "                 " + sales[i].getProduct());
			if (salesPrice > max) {
				max = salesPrice;
				maxSalesPriceSale = sales[i];
			}

		}
		return maxSalesPriceSale;
	}

	public Product[] getAllProduct(Product[] product1, Product[] product2, Product[] product3) { 	// Collects all products in an array

		int index = product1.length + product2.length + product3.length;
		Product[] allProducts = new Product[index];

		for (int i = 0; i < product1.length; i++) {
			allProducts[i] = product1[i];
		}

		for (int i = 0; i < product2.length; i++) {
			allProducts[i + product1.length] = product2[i];
		}

		for (int i = 0; i < product3.length; i++) {
			allProducts[i + product1.length + product2.length] = product3[i];
		}

		return allProducts;
	}

}
