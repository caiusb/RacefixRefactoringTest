package dummies;

import racefix.PrivatizerSubject.Particle;
import extra166y.Ops;
import extra166y.ParallelArray;

public interface Bla() {
	public void m();
}

public class Dummy {
	
	public static class Particle {
		public double coordX, coordY, middle;
		
		public Particle next;

		public void moveTo(double x, double y) {
			this.coordX = x;
			this.coordY = y;
		}
	}

	public void m() {
		ParallelArray<Particle> particles = ParallelArray
				.createUsingHandoff(new Particle[10],
						ParallelArray.defaultExecutor());

		final Particle s = new Particle();

		particles.apply(new Ops.Procedure<Particle>() {
			@Override
			public void op(Particle b) {
				s.coordX = 10;
			}
		});
	}
}