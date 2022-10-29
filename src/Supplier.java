public class Supplier {
	private Product[] products;
	FileIO fileIO;

	public Supplier(FileIO fileIO) { // kontrol yok, ya sales gelirse????
		setProducts(fileIO);
	}

	public Product[] getProducts() {

		return products;
	}

	private void setProducts(FileIO fileIO) {
		this.fileIO = fileIO;
		products = new Product[fileIO.counter];
		int divideElements = 0;
		for (int i = 0; i < fileIO.counter; i++) { // divideElements yerine yeni bir algoritma düşünülebilir...
			products[i] = new Product(fileIO.elements[i + divideElements], fileIO.elements[i + divideElements + 1],
					fileIO.elements[i + divideElements + 2], fileIO.elements[i + divideElements + 3],
					fileIO.elements[i + divideElements + 4]);
			divideElements+=4;
		}
	}

}
