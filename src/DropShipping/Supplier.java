/* In the setProducts method, it is used an algorithm that produces product objects and ensures to hold in products objects.
 * the variable named divideElements acts as a modulus operator. Product object has five fields 
 * therefore every five elements should be hold in ONE product object. Therefore in every step, divideElements
 * is increased by 4.
 */

package DropShipping;

import FileAccess.FileIO;

public class Supplier {
	private Product[] products;
	FileIO fileIO;

	public Supplier(FileIO fileIO) {
		setProducts(fileIO);
	}

	public Product[] getProducts() {

		return products;
	}

	private void setProducts(FileIO fileIO) {
		this.fileIO = fileIO;
		products = new Product[fileIO.getCounter() - 1];
		int divideElements = 0;
		for (int i = 0; i < products.length; i++) {
			products[i] = new Product(fileIO.getElements()[i + divideElements],
					fileIO.getElements()[i + divideElements + 1], fileIO.getElements()[i + divideElements + 2],
					fileIO.getElements()[i + divideElements + 3], fileIO.getElements()[i + divideElements + 4]);
			divideElements += 4;
		}
	}

}
