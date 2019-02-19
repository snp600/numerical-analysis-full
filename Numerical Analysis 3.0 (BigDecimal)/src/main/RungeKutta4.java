package main;

import java.math.BigDecimal;
import java.math.MathContext;

/* 
 * this class implements Runge-Kutta's fourth-order method
 * for task dy/dx = x / y + 1 / 2y - y / 2x*x = f(x,y)
 */

/*
 * Numerical Analysis 3.1
 * x in (1, 2)
 * eps = 1e-4
 * 2x*x*y*(dy/dx) + y*y = 2x*x*x + x*x
 * y(1) = 1
 * 
 */
public class RungeKutta4 {

	private int N; // dimension of grid
	private int eps = 30; // amount of significant signs after comma
	private BigDecimal h;
	private BigDecimal[] x;
	private BigDecimal[] y;

	public RungeKutta4(int dimension, BigDecimal x0, BigDecimal xN,
			BigDecimal y0) {
		N = dimension + 1;
		x = new BigDecimal[N];
		y = new BigDecimal[N];
		h = xN.subtract(x0).divide(new BigDecimal(dimension),
				new MathContext(20));
		x[0] = x0;
		for (int i = 1; i < N; i++)
			x[i] = x[i - 1].add(h);
		y[0] = y0;
	}

	public void solve(int task) {
		MathContext mc = new MathContext(eps);
		BigDecimal k1;
		BigDecimal k2;
		BigDecimal k3;
		BigDecimal k4;
		for (int i = 0; i < N - 1; i++) {
			k1 = f(x[i], y[i], task);
			k2 = f(x[i].add(h.divide(new BigDecimal(2.0))),
					y[i].add(h.divide(new BigDecimal(2.0)).multiply(k1)), task);
			k3 = f(x[i].add(h.divide(new BigDecimal(2.0))),
					y[i].add(h.divide(new BigDecimal(2.0)).multiply(k2)), task);
			k4 = f(x[i].add(h), y[i].add(h.multiply(k3)), task);
			y[i + 1] = y[i].add(h.divide(new BigDecimal(6.0), mc).multiply(
					k1.add(k2.multiply(new BigDecimal(2.0))
							.add(k3.multiply(new BigDecimal(2.0))).add(k4))));
		}
	}

	public void showGrid() {
		System.out.println(h);
		System.out.println("N = " + (N - 1));
		System.out.println("x0 = " + x[0]);
		System.out.println("xN = " + x[N - 1]);
		System.out.println("y0 = " + y[0]);
		for (int i = 0; i < N; i++) {
			System.out.print("x[" + i + "] = " + x[i] + " | ");
			System.out.print("y[" + i + "] = " + y[i] + "\n");
		}
		System.out.println();
	}

	public void solveThenShow(int task) {
		solve(task);
		showGrid();
	}

	public BigDecimal[] getYGrid(int task) {
		this.solve(task);
		return this.y;
	}

	private BigDecimal f(BigDecimal x, BigDecimal y, int task) { // returns
																	// f(x,y)
		MathContext mc = new MathContext(eps);
		switch (task) {
		case 1:
			return (x.divide(y, mc)).add(
					new BigDecimal(1.0).divide(y.multiply(new BigDecimal(2.0)),
							mc)).subtract(
					y.divide(x.multiply(x).multiply(new BigDecimal(2.0)), mc),
					mc);
		case 8:
			return y.subtract((x.multiply(y.multiply(y)))).divide(x, mc);
		case 10:
			// (2yy + 3xy - 2x)/2xx
			return y.multiply(y).multiply(new BigDecimal(2.0))
					.add(x.multiply(y.multiply(new BigDecimal(3.0))))
					.subtract(x.multiply(new BigDecimal(2.0)))
					.divide(x.multiply(x.multiply(new BigDecimal(2.0))), mc);
		}
		return null;
	}
}
