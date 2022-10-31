/* The constructor method includes setSales method and setSales method includes readSales method like chains.
 * The formula  given by Dilek Hoca for sales price is calculated in calculateSalesPrice method.
 */

package DropShipping;

import FileAccess.FileIO;

public class SalesManagement {

	private Sales[][] sales;

	private Supplier supplier1 = new Supplier(new FileIO("S1_Products.csv"));
	private Supplier supplier2 = new Supplier(new FileIO("S2_Products.csv"));
	private Supplier supplier3 = new Supplier(new FileIO("S3_Products.csv"));

	private FileIO fileName1, fileName2, fileName3;

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

		Sales[] sales = new Sales[fileName.getCounter() - 1];
		int divideElements = 0;

		for (int i = 0; i < sales.length; i++) {

			String salesID = fileName.getElements()[i + divideElements + 2];

			sales[i] = new Sales(fileName.getElements()[i + divideElements],
					fileName.getElements()[i + divideElements + 1], fileName.getElements()[i + divideElements + 2],
					fileName.getElements()[i + divideElements + 3], calculateSalesPrice(salesID, supplier));

			divideElements += 3;

		}
		return sales;
	}

	private String calculateSalesPrice(String salesID, Supplier supplier) {

		double tempSalesPrice = 0;
		String salesPrice = "";
		for (int i = 0; i < supplier.getProducts().length; i++) {

			if (salesID.equals(supplier.getProducts()[i].getId())) {
				double productPrice = Double.parseDouble(supplier.getProducts()[i].getPrice());
				double rate = Double.parseDouble(supplier.getProducts()[i].getRate());
				double numberOfReviews = Double.parseDouble(supplier.getProducts()[i].getNumberOfReviews());
				tempSalesPrice = productPrice + ((rate / 5.0) * 100 * numberOfReviews);
				salesPrice = Double.toString(tempSalesPrice);
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

	public Supplier getSupplier1() {
		return supplier1;
	}

	public void setSupplier1(Supplier supplier1) {
		this.supplier1 = supplier1;
	}

	public Supplier getSupplier2() {
		return supplier2;
	}

	public void setSupplier2(Supplier supplier2) {
		this.supplier2 = supplier2;
	}

	public Supplier getSupplier3() {
		return supplier3;
	}

	public void setSupplier3(Supplier supplier3) {
		this.supplier3 = supplier3;
	}

	public FileIO getFileName1() {
		return fileName1;
	}

	public void setFileName1(FileIO fileName1) {
		this.fileName1 = fileName1;
	}

	public FileIO getFileName2() {
		return fileName2;
	}

	public void setFileName2(FileIO fileName2) {
		this.fileName2 = fileName2;
	}

	public FileIO getFileName3() {
		return fileName3;
	}

	public void setFileName3(FileIO fileName3) {
		this.fileName3 = fileName3;
	}
}
