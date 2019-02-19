package main;

import java.math.BigDecimal;
import java.math.MathContext;

/* 
 * this class implements Runge-Kutta's third-order method
 * for task dy/dx = (1 - xy^2) / (yx^2 - 1) = f(x,y)
 * f1 = f(xn, yn)
 * f2 = f(xn + h/2, yn + f1h/2)
 * f3 = f(xn + h/, yn - f1h + 2hf2)
 * y(n+1) = yn + h(f1 + f2 + f3) / 6
 */

/*
 * Task '2'
 * x in (1, 2)
 * eps = 1e-4
 * 2x*x*y*(dy/dx) + y*y = 2x*x*x + x*x
 * y(1) = 1
 * 
 * Task '9'
 * x in (1, 2)
 * eps = 1e-4
 * dy/dx = ((4x+1)y - 4x - y*y) / ((2x-1)x)
 */
public class RungeKutta3 {

	private int N; // dimension of grid
	private int eps = 30; // amount of significant signs after comma
	private BigDecimal h;
	private BigDecimal[] x;
	private BigDecimal[] y;

	public RungeKutta3(int dimension, BigDecimal x0, BigDecimal xN,
			BigDecimal y0) {
		N = dimension + 1;
		x = new BigDecimal[N];
		y = new BigDecimal[N];
		h = xN.subtract(x0).divide(new BigDecimal(dimension));
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
		for (int i = 0; i < N - 1; i++) {
			k1 = f(x[i], y[i], task);
			k2 = f(x[i].add(h.divide(new BigDecimal(2.0), mc)),
					y[i].add(h.divide(new BigDecimal(2.0), mc).multiply(k1)),
					task);
			k3 = f(x[i].add(h), (y[i].subtract(h.multiply(k1)).add(h
					.multiply(new BigDecimal(2.0).multiply(k2)))), task);
			y[i + 1] = y[i].add(h.divide(new BigDecimal(6.0), mc).multiply(
					k1.add(k2.multiply(new BigDecimal(4.0)).add(k3))));
		}
	}

	public void showGrid() {
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
		case 2:
			// 2x*x*y*(dy/dx) + y*y = 2x*x*x + x*x
			return ((new BigDecimal(1.0)).subtract(x.multiply(y.multiply(y))))
					.divide((new BigDecimal(-1.0))
							.add(x.multiply(x.multiply(y))),
							mc);
		case 7:
			// (dy/dx) = (yy - 5x) / 2xy
			return y.multiply(y).subtract(x.multiply(new BigDecimal(5.0)))
					.divide(x.multiply(y.multiply(new BigDecimal(2.0))), mc);
		case 9:
			// (dy/dx) = ((4x+1)y - 4x - y*y) / ((2x-1)x)
			return (x.multiply(new BigDecimal(4.0)).add(new BigDecimal(1.0))
					.multiply(y).subtract(x.multiply(new BigDecimal(4.0)))
					.subtract(y.multiply(y))).divide(
					x.multiply(x.multiply(new BigDecimal(2.0)).subtract(
							new BigDecimal(1.0))), mc);
		case 13:
			// (dy/dx) = x / (y - xx)
			return x.divide(y.subtract(x.multiply(x)), mc);
		default:
			throw new IllegalArgumentException();
		}
	}
}
