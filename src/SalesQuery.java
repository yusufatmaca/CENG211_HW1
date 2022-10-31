public class SalesQuery {

	private Product mostProfitableProduct;
	private Product mostExpensiveProduct;
	private Product leastProfitOfS1;
	private Customer mostPurchasedOne;
	private Object[] leastProfitS1ProductAndProfit;
	private Double totalProfit1 = 0.0;
	private Customer[] allCustomers;
	private FileIO customersFileName;
	private Sales[] allSales;
	private Object[] productAndProfit;
	private Object[] customerAndNumberofPurchases = new Object[2];
	
	private String tempSalesPrice;

	SalesManagement salesManagement = new SalesManagement(new FileIO("S1_Sales.csv"), new FileIO("S2_Sales.csv"),
			new FileIO("S3_Sales.csv"));

	public SalesQuery() {
		setCustomers(new FileIO("Customers.csv"));
		// System.out.println(allCustomers[0].getAddress());
		mostProfitableProduct(salesManagement);
		mostProfitableProduct = (Product)productAndProfit[0];

		System.out.println(mostProfitableProduct.getId() + " " + mostProfitableProduct.getTitle()
				+ " " + mostProfitableProduct.getRate() + " " + mostProfitableProduct.getNumberOfReviews()
				+ " " + mostProfitableProduct.getPrice() + " -> " + (Double)productAndProfit[1] + " TL profit");
		mostExpensiveProduct = mostExpensiveProduct(salesManagement);
		tempSalesPrice = salesOfProduct(mostExpensiveProduct).getSalesPrice();
		
		System.out.println(mostExpensiveProduct.getId() + " " + mostExpensiveProduct.getTitle()
		+ " " + mostExpensiveProduct.getRate() + " " + mostExpensiveProduct.getNumberOfReviews()
		+ " " + mostExpensiveProduct.getPrice() + " -> " + "with sales price " + tempSalesPrice);
		
		mostPurchasedOne = (Customer)mostPurchasedCustomer(allSales, allCustomers)[0];
		
		System.out.println(mostPurchasedOne.getId() + " " + mostPurchasedOne.getName()
		+ " " + mostPurchasedOne.getEmail() + " " + mostPurchasedOne.getCountry()
		+ " " + mostPurchasedOne.getAddress() + " -> " + mostPurchasedCustomer(allSales, allCustomers)[1] + " purchases" );
		
		System.out.println("Total Profit: " + totalProfit());
		
		leastProfitEach(salesManagement.getSales()[0], salesManagement.supplier1);
		leastProfitOfS1 = (Product)leastProfitS1ProductAndProfit[0];
		
		System.out.println(leastProfitOfS1.getId() + " " + leastProfitOfS1.getTitle()
		+ " " + leastProfitOfS1.getRate() + " " + leastProfitOfS1.getNumberOfReviews()
		+ " " + leastProfitOfS1.getPrice() + " -> " + (Double)leastProfitS1ProductAndProfit[1] + " TL profit");
		mostPurchasedCustomer(allSales, allCustomers);
	}

	public Object[] mostProfitableProduct(SalesManagement salesManagement) { // Among three supplier

		productAndProfit = new Object[2];
		
		Product product1 = maxProfitEach(salesManagement.getSales()[0], salesManagement.supplier1); // most profitable
		Product product2 = maxProfitEach(salesManagement.getSales()[1], salesManagement.supplier2); // most profitable
		Product product3 = maxProfitEach(salesManagement.getSales()[2], salesManagement.supplier3); // most profitable
																									// product of S3

		Sales salesOfProduct1 = salesOfProduct(product1);
		Sales salesOfProduct2 = salesOfProduct(product2);
		Sales salesOfProduct3 = salesOfProduct(product3);

		Double profit1 = Double.parseDouble(salesOfProduct1.getSalesPrice()) - Double.parseDouble(product1.getPrice());
		Double profit2 = Double.parseDouble(salesOfProduct2.getSalesPrice()) - Double.parseDouble(product2.getPrice());
		Double profit3 = Double.parseDouble(salesOfProduct3.getSalesPrice()) - Double.parseDouble(product3.getPrice());
		/*
		 * System.out.println(profit1); System.out.println(profit2);
		 * System.out.println(profit3);
		 */
		if (profit1 > profit2 && profit1 > profit3) {
			productAndProfit[0] = product1;
			productAndProfit[1] = profit1;
			return productAndProfit;
		} else if (profit2 > profit1 && profit2 > profit3) {
			productAndProfit[0] = product2;
			productAndProfit[1] = profit2;
			return productAndProfit;
		} else {
			productAndProfit[0] = product3;
			productAndProfit[1] = profit3;
			return productAndProfit;
		}
	}

	public Product mostExpensiveProduct(SalesManagement salesManagement) { // in term of SalesPrice

		Sales maxOne = maxSalesPrice(salesManagement.getSales()[0], salesManagement.getSales()[1],
				salesManagement.getSales()[2]);
		Product mostExpensiveProduct = null;
		Product[] allProduct = getAllProduct(salesManagement.supplier1.getProducts(),
				salesManagement.supplier2.getProducts(), salesManagement.supplier3.getProducts());

		for (int i = 0; i < allProduct.length; i++) {
			if (maxOne.getProduct().equals(allProduct[i].getId())) {
				mostExpensiveProduct = allProduct[i];
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

	public Product maxProfitEach(Sales[] sales, Supplier supplier) { // calculates the max profitable product for each
																		// sales.

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

	private Sales salesOfProduct(Product product) { // gives the sale of the given product

		Sales sales1 = null;
		// double max = 0.0; // another method

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < salesManagement.getSales()[i].length; j++) {
				if (salesManagement.getSales()[i][j].getProduct().equals(product.getId())) {
					sales1 = salesManagement.getSales()[i][j];
					// max = Double.parseDouble(sales1.getSalesPrice()) -
					// Double.parseDouble(product.getPrice());

				}
			}
		}
		// System.out.println(max);
		return sales1;

	}

	// private Sales[] allSales;

	public Sales maxSalesPrice(Sales[] sales1, Sales[] sales2, Sales[] sales3) { // Gives the sale that is most
																					// expensive in term of salesPrice
		int index = sales1.length + sales2.length + sales3.length;
		allSales = new Sales[index];

		for (int i = 0; i < sales1.length; i++) {
			allSales[i] = sales1[i];
		}

		for (int i = 0; i < sales2.length; i++) {
			allSales[i + sales1.length] = sales2[i];
		}

		for (int i = 0; i < sales3.length; i++) {
			allSales[i + sales1.length + sales2.length] = sales3[i];
		}

		Double max = 0.0; // temporary
		Sales maxSalesPriceSale = null;

		for (int i = 0; i < allSales.length; i++) {
			// System.out.println(sales[i].getSalesPrice());
			Double salesPrice = Double.parseDouble(allSales[i].getSalesPrice());
			// System.out.println(salesPrice + " " + sales[i].getProduct());
			if (salesPrice > max) {
				max = salesPrice;
				maxSalesPriceSale = allSales[i];
			}

		}
		return maxSalesPriceSale;
	}

	public Product[] getAllProduct(Product[] product1, Product[] product2, Product[] product3) { // Collects all
																									// products in an
																									// array

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

	// The least-profit product of S1. (Please include the amount of profit to
	// output.)

	public Object[] leastProfitEach(Sales[] sales, Supplier supplier) { // calculates the least profitable product for
																		// given sales.
		leastProfitS1ProductAndProfit = new Object[2];
		Product leastProfitableProduct = null;

		Double profit = 0.0;
		Double tempPrice = Double.parseDouble(supplier.getProducts()[0].getPrice());
		Double tempSalesPrice = Double.parseDouble(salesOfProduct(supplier.getProducts()[0]).getSalesPrice());

		Double leastProfit = tempSalesPrice - tempPrice;

		for (int i = 0; i < sales.length; i++) {
			for (int j = 0; j < supplier.getProducts().length; j++) {
				if (sales[i].getProduct().equals(supplier.getProducts()[j].getId())) {

					Double salesPrice = Double.parseDouble(sales[i].getSalesPrice());
					Double price = Double.parseDouble(supplier.getProducts()[j].getPrice());
					profit = salesPrice - price;

					// System.out.println(salesManagement.supplier1.getProducts()[j].getId());
					// System.out.println(profit + supplier.getProducts()[j].getId());

					if (profit < leastProfit) {
						leastProfit = profit;
						leastProfitableProduct = salesManagement.supplier1.getProducts()[j];
						leastProfitS1ProductAndProfit[0] = leastProfitableProduct;
						leastProfitS1ProductAndProfit[1] = profit;
					}
				}
			}
		}

		return leastProfitS1ProductAndProfit;

	}

	public void setCustomers(FileIO fileName) {
		customersFileName = fileName;
		allCustomers = new Customer[customersFileName.counter - 1];
		int divideElements = 0;
		for (int i = 0; i < allCustomers.length; i++) {
			allCustomers[i] = new Customer(customersFileName.elements[i + divideElements],
					customersFileName.elements[i + divideElements + 1],
					customersFileName.elements[i + divideElements + 2],
					customersFileName.elements[i + divideElements + 3],
					customersFileName.elements[i + divideElements + 4]);
			divideElements += 4;
		}
	}

	public Customer[] getCustomers() {

		return allCustomers;
	}

	private Object[] mostPurchasedCustomer(Sales[] sales, Customer[] customer) { // allsales
		int max = 0;
		Customer mostPurchasedOne = null;
		
		
		for (int i = 0; i < customer.length; i++) {
			int counter = 0;
			for (int j = 0; j < sales.length; j++) {
				if (customer[i].getId().equals(sales[j].getCustomer())) {
					counter++;
				}
			}
			//System.out.println(customer[i].getName() + " " + customer[i].getId() + " " + counter);
			if (max < counter) {
				max = counter;
				mostPurchasedOne = customer[i];
				customerAndNumberofPurchases[0] = mostPurchasedOne;
				customerAndNumberofPurchases[1] = max;
			}
		}
		return customerAndNumberofPurchases;
	}

}
