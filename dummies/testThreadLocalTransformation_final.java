package dummy;

import privatization.ThreadPrivate;

public class Dummy {

	private ThreadPrivate<Integer> x = new ThreadPrivate<Integer>();
	
	public void aMethod() {
		x.set(3);
		int y = x.get() - 7;
	}
}