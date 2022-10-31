/* FileIO constructor accepts file name which may be "S1_Products.csv" or "S1_Sales.csv"...
 * variables named 'counter' is immutable, therefore shallow copy is used.
 * variables named 'elements, fileName and counter should be private because of being data field.
 * the method named 'sizeOfElements' was prepared to initialize the size of the array.
 */

package FileAccess;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
	private String[] elements;
	private String fileName;
	private int counter = 0;

	public FileIO(String fileName) {
		setFileName(fileName);
		elements = new String[sizeOfElements()];
		inputStream();
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	private int sizeOfElements() {
		int attributes = 0;
		String line = "";
		String splitBy = ",";
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				if (counter == 0) { // for just one execution instead of many times
					String[] tempArr = line.split(splitBy);
					attributes = tempArr.length;
				}
				counter++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// The first line in the CSV files is always observed like a title. Therefore, it must be ignored.
		return (counter-1) * attributes; 
	}
	
	public int getCountLine() {
		return counter;
	}

	private void inputStream() {
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		
		
		try {
			
			br = new BufferedReader(new FileReader(fileName));
			br.readLine();
			int index = 0;

			while ((line = br.readLine()) != null) {
				String[] tempArr = line.split(splitBy);

				for (int i = 0; i < tempArr.length; i++) {
					elements[index] = tempArr[i];
					index++;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}

	private void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String[] getElements() {
		return elements;
	}

	public void setElements(String[] elements) {
		this.elements = elements;
	}
}
