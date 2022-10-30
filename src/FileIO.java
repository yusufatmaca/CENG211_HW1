import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
	public String[] elements;
	private String fileName;

	public FileIO(String fileName) {
		setFileName(fileName);
		elements = new String[setCountLine()];
		inputStream();
	}

	int counter = 0;
	private int setCountLine() { // ismi değiştirilmeli... tüm elemanları sayıyor
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
}
