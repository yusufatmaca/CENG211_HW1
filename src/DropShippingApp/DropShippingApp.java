package DropShippingApp;

import DropShipping.SalesQuery;

/* @author 270201084 Yusuf Atmaca
 * @author 270201085 Selim Tıkız
 * @author 280201052 Murat Küçük
 * @author 280201072 Kübra Holt
 * The codes in this homework are also available on GitHub. 
 * https://github.com/yusufatmaca/CENG211_HW1.git
 * 
 * 
 * In the main method, we ONLY create salesQuery object whose type is SalesQuery. 
 * After creating this object, every piece of code is executed like a chain.
 * 
 */

public class DropShippingApp {

	public static void main(String[] args) {

		SalesQuery salesQuery = new SalesQuery();
		salesQuery.print();
	}

}
