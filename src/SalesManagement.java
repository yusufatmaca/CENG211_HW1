public class SalesManagement {

	private Sales[][] sales;

	Supplier supplier1 = new Supplier(new FileIO("S1_Products.csv")); /// private değil!!!
	Supplier supplier2 = new Supplier(new FileIO("S2_Products.csv"));
	Supplier supplier3 = new Supplier(new FileIO("S3_Products.csv"));
	
	
	FileIO fileName1, fileName2, fileName3;

	public SalesManagement(FileIO fileName1, FileIO fileName2, FileIO fileName3) {
		setSales(fileName1, fileName2, fileName3);

	}

	private void setSales(FileIO fileName1, FileIO fileName2, FileIO fileName3) {

		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
		this.fileName3 = fileName3;

		sales = new Sales[3][];

		sales[0] = readSales(fileName1, supplier1);
		sales[1] = readSales(fileName2, supplier2);
		sales[2] = readSales(fileName3, supplier3);

	}

	private Sales[] readSales(FileIO fileName, Supplier supplier) {

		Sales[] sales = new Sales[fileName.counter-1];
		int divideElements = 0;

		for (int i = 0; i < sales.length; i++) {

			String salesID = fileName.elements[i + divideElements + 2];
		

			sales[i] = new Sales(fileName.elements[i + divideElements], fileName.elements[i + divideElements + 1],
					fileName.elements[i + divideElements + 2], fileName.elements[i + divideElements + 3], calculateSalesPrice(salesID, supplier));
				
			divideElements += 3;

		}
		return sales;
	}

	private String calculateSalesPrice(String salesID, Supplier supplier) {

		double tempSalesPrice = 0;
		String salesPrice = "";
		//System.out.println(supplier.getProducts()[i].getId());
		for (int i = 0; i < supplier.getProducts().length; i++) {
			//System.out.println(salesID);
			//System.out.println(salesID.equals(supplier.getProducts()[i].getId()));
			
			if (salesID.equals(supplier.getProducts()[i].getId())) {
				double productPrice = Double.parseDouble(supplier.getProducts()[i].getPrice());
				double rate = Double.parseDouble(supplier.getProducts()[i].getRate());
				double numberOfReviews = Double.parseDouble(supplier.getProducts()[i].getNumberOfReviews());
				tempSalesPrice = productPrice + ((rate / 5.0) * 100 * numberOfReviews);
				salesPrice = Double.toString(tempSalesPrice);
				//System.out.println("Murat");
			}
		}
		return salesPrice;
	}

	public Sales[][] getSales() {
		return sales;
	}

	public void setSales(Sales[][] sales) {
		this.sales = sales;
	}

}
