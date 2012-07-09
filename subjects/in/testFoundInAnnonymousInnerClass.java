package in;

public class testFoundInAnnonymousInnerClass {
	
	private interface Bla {
		public void m();
	}
	
	public static class Particle {
		public double coordX, coordY, middle;
		
		public Particle next;

		public void moveTo(double x, double y) {
			this.coordX = x;
			this.coordY = y;
		}
	}

	public void m() {
	}
}