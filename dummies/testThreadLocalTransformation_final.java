package dummy;

public class Dummy {

	private ThreadLocal<Integer> x = new ThreadLocal<Integer>();
	
	public void aMethod() {
		x.set(3);
		int y = x.get() - 7;
	}
}