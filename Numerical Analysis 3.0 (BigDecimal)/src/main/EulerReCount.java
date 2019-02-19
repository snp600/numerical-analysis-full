package main;

import java.math.BigDecimal;
import java.math.MathContext;

/* 
 * this class implements Euler's Method with re-count
 */

public class EulerReCount {

	private int N; // dimension of grid
	private int eps = 5; // amount of significant signs after comma
	private BigDecimal h;
	private BigDecimal[] x;
	private BigDecimal[] y;

	public EulerReCount(int dimension, BigDecimal x0, BigDecimal xN,
			BigDecimal y0) {
		N = dimension + 1;
		x = new BigDecimal[N];
		y = new BigDecimal[N];
		for (int i = 0; i < N; i++) {
			y[i] = new BigDecimal(0.0);
			y[i] = y[i].setScale(20);
		}
		h = xN.subtract(x0).divide(new BigDecimal(dimension));
		x[0] = x0;
		for (int i = 1; i < N; i++) 
			x[i] = x[i - 1].add(h);
		y[0] = y0;
	}

	public void solve(int task) {
		MathContext mc = new MathContext(eps);
		BigDecimal k1 = new BigDecimal(0.0);
		BigDecimal k2 = new BigDecimal(0.0);
		k1 = k1.setScale(10);
		k2 = k2.setScale(10);
		for (int i = 0; i < N - 1; i++) {
			k1 = f(x[i], y[i], task);
			k2 = f(x[i].add(h), y[i].add(h.multiply(k1)), task);
			y[i + 1] = y[i].add(h.divide(new BigDecimal(2.0), mc).multiply(
					k1.add(k2)));
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
		x = x.setScale(20, BigDecimal.ROUND_CEILING);
		y = y.setScale(20, BigDecimal.ROUND_CEILING);
		switch (task) {
		case 4:
			// (dy/dx) = (3xy + 2xx - yy) / (xy - xx)
			return (x.multiply(y.multiply(new BigDecimal(3.0))).add(
					x.multiply(x.multiply(new BigDecimal(2.0)))).subtract(y
					.multiply(y))).divide(
					x.multiply(y).subtract(x.multiply(x)), mc);
		case 5:
			// (dy/dx) = 2y / (x*(2xxyln(y) + 1)
			return y.multiply(new BigDecimal(2.0)).divide(
					x.multiply(x.multiply(
							x.multiply(y.multiply(new BigDecimal(2.0)))
									.multiply(
											new BigDecimal(Math.log(y
													.doubleValue())))).add(
							new BigDecimal(1.0))), mc);
		case 6:
			// (dy/dx) = xyy + 3xy
			return x.multiply(y.multiply(y)).add(
					x.multiply(y.multiply(new BigDecimal(3.0))));
		case 12:
			// (dy/dx) = (-2 - 4xy) / xx - yy
			return x.multiply(y.multiply(new BigDecimal(-4.0)))
					.subtract(new BigDecimal(2.0)).divide(x.multiply(x), mc)
					.subtract(y.multiply(y));
		case 16:
			// (dy/dx) = (y*y + xy(x-2)) / (x*x*(x-1))
			return y.multiply(y)
					.add(x.multiply(y.multiply(x.subtract(new BigDecimal(2.0)))))
					.divide(x.multiply(x.multiply(x
							.subtract(new BigDecimal(1.0)))), mc);
		default:
			throw new IllegalArgumentException();
		}
	}
}