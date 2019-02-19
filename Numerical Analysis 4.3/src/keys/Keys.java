package keys;

import solutions.Eleven;
import solutions.Eleven2;
import solutions.Thirteen;
import solutions.Thirteen2;
import solutions.Twelve;
import solutions.Twelve2;
import solutions.Two;
import solutions.Two2;

public enum Keys {
	TWO(new Two()),
	TWO2(new Two2()),
	ELEVEN (new Eleven()),
	ELEVEN2 (new Eleven2()),
	TWELVE (new Twelve()),
	TWELVE2 (new Twelve2()),
	THIRTEEN(new Thirteen()),
	THIRTEEN2(new Thirteen2());
	
	private Key k;
	
	private Keys(Key k) {
		this.k = k;
	}
	
	 public double[] getSolution() {
		 return k.getSolution();
	 }
	
}
