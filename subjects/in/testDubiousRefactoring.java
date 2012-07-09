package in;

public class testDubiousRefactoring {
	
	private Particle shared = new Particle();
	
	public static class Particle {
		public double coordX, coordY, middle;
		
		public Particle next;

		public void moveTo(double x, double y) {
			this.coordX = x;
			this.coordY = y;
		}
	}
	
	public void simpleRace() {
				shared.coordX = 10.;
	}
}