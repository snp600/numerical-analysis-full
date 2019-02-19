package main;

/* 
 * this class implements Runge-Kutta's fourth-order method
 * for task dy/dx = x / y + 1 / 2y - y / 2x*x = f(x,y)
 */

public class RungeKutta {

	private int N; //dimension of grid
	private double h;
	private double[] x;
	private double[] y;
	
	public RungeKutta(int dimension, double x0,  double xN, double y0) {
		N = dimension + 1;
		x = new double[N];
		y = new double[N];
		h = (xN - x0) / (N - 1);
		x[0] = x0;
		for (int i = 1; i < N; i++)
			x[i] = x[i-1] + h;
		y[0] = y0;
	}
	public void solve() {
		double k1;
		double k2;
		double k3;
		double k4;
		for (int i = 0; i < N - 1; i++) {
			k1 = f(x[i], y[i]);
			k2 = f(x[i] + h/2.0, y[i] + h/2.0 * k1);
			k3 = f(x[i] + h/2.0, y[i] + h/2.0 * k2);
			k4 = f(x[i] + h, y[i] + h * k3);
			y[i+1] = y[i] + h / 6.0 * (k1 + 2.0 * k2 + 2.0 * k3 + k4);
		}
	}
	
	public void showGrid() {
		System.out.println("N = " + (N - 1));
		System.out.println("x0 = " + x[0]);
		System.out.println("xN = " + x[N-1]);
		System.out.println("y0 = " + y[0]);
		for (int i = 0; i < N; i++) {
			System.out.print("x[" + i + "] = " + x[i] + " | ");
			System.out.print("y[" + i + "] = " + y[i] + "\n");
		}
		System.out.println();
	}
	
	public void solveThenShow() {
		solve();
		showGrid();
	}
	private double f(double x, double y) { //returns f(x,y)
		return x / y + 1 / (2.0 * y) - y / (2.0 * x * x);
	}
}
