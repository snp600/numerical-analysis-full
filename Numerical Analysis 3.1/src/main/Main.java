package main;

/*
 * Numerical Analysis 3.1
 * x in (1, 2)
 * eps = 1e-4
 * 2x*x*y*(dy/dx) + y*y = 2x*x*x + x*x
 * y(1) = 1
 * 
 */
public class Main {

	public static void main(String[] args) {
		(new RungeKutta(10, 1.0, 2.0, 1.0)).solveThenShow();
		(new RungeKutta(10, 1.0, 20.0, 1.0)).solveThenShow();
	}

}
