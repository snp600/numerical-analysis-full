package main;

import static tools.SystemSolver.solve;

/*
 * example for 'S' matrix:
 * if we have table:
 * x: 1 2 3
 * y: 1 4 9
 * so 'S' will be: 1^2 1^1 1 1
 * 				   2^2 2^1 1 4
 * 				   3^2 3^1 1 9
 */

public class Interpolator {

	public double[][] createMatrix(double x[], double y[]) {
		int N = x.length;
		double[][] S = new double[N][N + 1]; // System to solve
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				S[i][j] = Math.pow(x[i], N - 1 - j);
			S[i][N] = y[i];
		}
		return S;
	}

	public double[] makeInterpolation(double x[], double y[]) {
		return solve(createMatrix(x, y));
	}
}
