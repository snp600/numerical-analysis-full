package main;

import java.math.BigDecimal;
import tools.Convergence;

public class Main {

	public static void main(String[] args) {
//		(new RungeKutta4(320, new BigDecimal(1.0), new BigDecimal(2.0),
//				new BigDecimal(0.02))).solveThenShow(1);
//		(new RungeKutta4(20, new BigDecimal(1.0), new BigDecimal(2.0),
//				new BigDecimal(2.0))).solveThenShow(8);
		(new RungeKutta4(20, new BigDecimal(1.0), new BigDecimal(2.0),
				new BigDecimal(0.5))).solveThenShow(10);

//		(new RungeKutta3(10, new BigDecimal(1e-7), new BigDecimal(1.0),
//				new BigDecimal(1e-7))).solveThenShow(2);
//		(new RungeKutta3(20, new BigDecimal(1.0), new BigDecimal(1.2),
//				new BigDecimal(1.0))).solveThenShow(7);
//		(new RungeKutta3(20, new BigDecimal(1.0), new BigDecimal(2.0),
//				new BigDecimal(2.0))).solveThenShow(9);
//		(new RungeKutta3(40, new BigDecimal(1.0), new BigDecimal(2.0),
//				new BigDecimal(1.5))).solveThenShow(13);
//
//		(new EulerReCount(1280, new BigDecimal(2.0), new BigDecimal(3.0),
//				new BigDecimal(4.0))).solveThenShow(16);
//		(new EulerReCount(1280, new BigDecimal(1.0), new BigDecimal(1.2),
//				new BigDecimal(1.0))).solveThenShow(5);
//		(new EulerReCount(10, new BigDecimal(1.0), new BigDecimal(2.0),
//				new BigDecimal(0.0))).solveThenShow(6);
//		(new EulerReCount(5120, new BigDecimal(1.0), new BigDecimal(2.0),
//				new BigDecimal(-1.0))).solveThenShow(12);
//		(new EulerReCount(1280, new BigDecimal(1.0), new BigDecimal(2),
//				new BigDecimal(1.0 + Math.sqrt(2.0)))).solveThenShow(4);
		convergenceTest();
	}

	public static void convergenceTest() {
		final int N = 10;
		Convergence c = new Convergence(N);
		for (int i = 0; i < N; i++)
			c.addElement((new RungeKutta4((int) (40 * Math.pow(2, i + 1)),
					new BigDecimal(1.0), new BigDecimal(2.0), new BigDecimal(
							0.5))).getYGrid(10));
		System.out.println();
		c.composeDiffs();
		c.showDivisionOfDiffs();
	}

}
