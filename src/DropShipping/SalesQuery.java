package DropShipping;

import FileAccess.FileIO;

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
	private Object[] customerAndNumberofPurchases;
	private String tempSalesPrice;
	private SalesManagement salesManagement = new SalesManagement(new FileIO("S1_Sales.csv"),
			new FileIO("S2_Sales.csv"), new FileIO("S3_Sales.csv"));

	// The some variable types are Objects, therefore the type must be converted to
	// the type which is needed.
	public SalesQuery() {
		setCustomers(new FileIO("Customers.csv"));
		mostProfitableProduct(salesManagement);
		mostProfitableProduct = (Product) productAndProfit[0];
		mostExpensiveProduct = mostExpensiveProduct(salesManagement);
		tempSalesPrice = salesOfProduct(mostExpensiveProduct).getSalesPrice();
		mostPurchasedCustomer(allSales, allCustomers);
		mostPurchasedOne = (Customer) customerAndNumberofPurchases[0];
		leastProfitEach(salesManagement.getSales()[0], salesManagement.getSupplier1());
		leastProfitOfS1 = (Product) leastProfitS1ProductAndProfit[0];

	}

	// This method finds the most profitable product among all products.
	// With if-else statements, returns an array which contains this product and its
	// profit.
	public void mostProfitableProduct(SalesManagement salesManagement) {

		productAndProfit = new Object[2];

		Product product1 = maxProfitEach(salesManagement.getSales()[0], salesManagement.getSupplier1());
		Product product2 = maxProfitEach(salesManagement.getSales()[1], salesManagement.getSupplier2());
		Product product3 = maxProfitEach(salesManagement.getSales()[2], salesManagement.getSupplier3());

		Sales salesOfProduct1 = salesOfProduct(product1);
		Sales salesOfProduct2 = salesOfProduct(product2);
		Sales salesOfProduct3 = salesOfProduct(product3);

		Double profit1 = Double.parseDouble(salesOfProduct1.getSalesPrice()) - Double.parseDouble(product1.getPrice());
		Double profit2 = Double.parseDouble(salesOfProduct2.getSalesPrice()) - Double.parseDouble(product2.getPrice());
		Double profit3 = Double.parseDouble(salesOfProduct3.getSalesPrice()) - Double.parseDouble(product3.getPrice());

		if (profit1 > profit2 && profit1 > profit3) {
			productAndProfit[0] = product1;
			productAndProfit[1] = profit1;
		} else if (profit2 > profit1 && profit2 > profit3) {
			productAndProfit[0] = product2;
			productAndProfit[1] = profit2;
		} else {
			productAndProfit[0] = product3;
			productAndProfit[1] = profit3;
		}
	}

	// in maxSalesPrice method, it connects 3 sales arrays into 1 array and finds to
	// the product which has the max sales price. ID comparison is done with equals
	// method in if-else statement.
	public Product mostExpensiveProduct(SalesManagement salesManagement) { 
		Product mostExpensiveProduct = null;
		Sales maxOne = maxSalesPrice(salesManagement.getSales()[0], salesManagement.getSales()[1],
				salesManagement.getSales()[2]);
		Product[] allProduct = getAllProduct(salesManagement.getSupplier1().getProducts(),
				salesManagement.getSupplier2().getProducts(), salesManagement.getSupplier3().getProducts());
		//
		for (int i = 0; i < allProduct.length; i++) {
			if (maxOne.getProduct().equals(allProduct[i].getId())) {
				mostExpensiveProduct = allProduct[i];
			}
		}
		return mostExpensiveProduct;

	}

	// The total profit is calculated in the method maxProfitEach
	public Double totalProfit() {

		return totalProfit1;
	}

	// Calculates the max profitable product for each sales.
	public Product maxProfitEach(Sales[] sales, Supplier supplier) {
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
					if (profit > maxProfit) {
						maxProfit = profit;
						maxProfitableProduct = salesManagement.getSupplier1().getProducts()[j];
					}
				}
			}
		}

		return maxProfitableProduct;

	}

	// Finds the place of product in the sales.
	private Sales salesOfProduct(Product product) {

		Sales sales1 = null;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < salesManagement.getSales()[i].length; j++) {
				if (salesManagement.getSales()[i][j].getProduct().equals(product.getId())) {
					sales1 = salesManagement.getSales()[i][j];

				}
			}
		}
		return sales1;

	}

	// Gives the product that is most expensive in term of salesPrice
	public Sales maxSalesPrice(Sales[] sales1, Sales[] sales2, Sales[] sales3) {

		int index = sales1.length + sales2.length + sales3.length;
		allSales = new Sales[index];
		Double max = 0.0; // temporary
		Sales maxSalesPriceSale = null;

		for (int i = 0; i < sales1.length; i++) {
			allSales[i] = sales1[i];
		}

		for (int i = 0; i < sales2.length; i++) {
			allSales[i + sales1.length] = sales2[i];
		}

		for (int i = 0; i < sales3.length; i++) {
			allSales[i + sales1.length + sales2.length] = sales3[i];
		}

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

	// Collects all products in an array.
	public Product[] getAllProduct(Product[] product1, Product[] product2, Product[] product3) {
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

	// The least-profit product of S1 is calculated with this method.
	// calculates the least profitable product for given sales.
	public Object[] leastProfitEach(Sales[] sales, Supplier supplier) {

		leastProfitS1ProductAndProfit = new Object[2];
		Product leastProfitableProduct = null;
		Double profit = 0.0;
		Double tempPrice = Double.parseDouble(supplier.getProducts()[0].getPrice());
		Double localTempSalesPrice = Double.parseDouble(salesOfProduct(supplier.getProducts()[0]).getSalesPrice());
		Double leastProfit = localTempSalesPrice - tempPrice;

		for (int i = 0; i < sales.length; i++) {
			for (int j = 0; j < supplier.getProducts().length; j++) {
				if (sales[i].getProduct().equals(supplier.getProducts()[j].getId())) {

					Double salesPrice = Double.parseDouble(sales[i].getSalesPrice());
					Double price = Double.parseDouble(supplier.getProducts()[j].getPrice());
					profit = salesPrice - price;


					if (profit < leastProfit) {
						leastProfit = profit;
						leastProfitableProduct = salesManagement.getSupplier1().getProducts()[j];
						leastProfitS1ProductAndProfit[0] = leastProfitableProduct;
						leastProfitS1ProductAndProfit[1] = profit;
					}
				}
			}
		}

		return leastProfitS1ProductAndProfit;

	}

	// Creates customer objects and stores them in an array.
	public void setCustomers(FileIO fileName) {
		customersFileName = fileName;
		allCustomers = new Customer[customersFileName.getCounter() - 1];
		int divideElements = 0;
		for (int i = 0; i < allCustomers.length; i++) {
			allCustomers[i] = new Customer(customersFileName.getElements()[i + divideElements],
					customersFileName.getElements()[i + divideElements + 1],
					customersFileName.getElements()[i + divideElements + 2],
					customersFileName.getElements()[i + divideElements + 3],
					customersFileName.getElements()[i + divideElements + 4]);
			divideElements += 4;
		}
	}

	public Customer[] getCustomers() {

		return allCustomers;
	}

	// This method fills the customerAndNumberofPurchases field with customer object at index 0 
	// and the number of its purchases at index 1.
	private void mostPurchasedCustomer(Sales[] sales, Customer[] customer) {
		int max = 0;
		Customer mostPurchasedOne = null;
		customerAndNumberofPurchases = new Object[2];

		for (int i = 0; i < customer.length; i++) {
			int counter = 0;
			for (int j = 0; j < sales.length; j++) {
				if (customer[i].getId().equals(sales[j].getCustomer())) {
					counter++;
				}
			}
			if (max < counter) {
				max = counter;
				mostPurchasedOne = customer[i];
				customerAndNumberofPurchases[0] = mostPurchasedOne;
				customerAndNumberofPurchases[1] = max;
			}
		}
	}

	// This method prints all outputs that are required.
	public void print() {
		System.out.println(mostProfitableProduct.getId() + " " + mostProfitableProduct.getTitle() + " "
				+ mostProfitableProduct.getRate() + " " + mostProfitableProduct.getNumberOfReviews() + " "
				+ mostProfitableProduct.getPrice() + " -> " + (Double) productAndProfit[1] + " TL profit");

		System.out.println(mostExpensiveProduct.getId() + " " + mostExpensiveProduct.getTitle() + " "
				+ mostExpensiveProduct.getRate() + " " + mostExpensiveProduct.getNumberOfReviews() + " "
				+ mostExpensiveProduct.getPrice() + " -> " + "with sales price " + tempSalesPrice);

		System.out.println(mostPurchasedOne.getId() + " " + mostPurchasedOne.getName() + " "
				+ mostPurchasedOne.getEmail() + " " + mostPurchasedOne.getCountry() + " "
				+ mostPurchasedOne.getAddress() + " -> " + customerAndNumberofPurchases[1] + " purchases");

		System.out.println("Total Profit: " + totalProfit());

		System.out.println(leastProfitOfS1.getId() + " " + leastProfitOfS1.getTitle() + " " + leastProfitOfS1.getRate()
				+ " " + leastProfitOfS1.getNumberOfReviews() + " " + leastProfitOfS1.getPrice() + " -> "
				+ (Double) leastProfitS1ProductAndProfit[1] + " TL profit");

	}

}
