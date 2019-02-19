package main;

import static tools.SystemSolver.solve;
import static tools.CodeSimplifiers.fromDoubleToBD;
import java.math.BigDecimal;

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

	public BigDecimal[][] createMatrix(BigDecimal x[], BigDecimal y[]) {
		int N = x.length;
		BigDecimal[][] S = new BigDecimal[N][N + 1]; //System to solve
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) 
				S[i][j] = new BigDecimal(Math.pow(x[i].doubleValue(), N - 1 - j));
				S[i][N] = new BigDecimal(y[i].doubleValue());
		}
		return S;
	}
	
	public BigDecimal[] makeInterpolation(double xx[], double yy[]) {
		BigDecimal[] x = fromDoubleToBD(xx);
		BigDecimal[] y = fromDoubleToBD(yy);
		return solve(createMatrix(x,y));
	}
	public BigDecimal[] makeInterpolationBIG(BigDecimal x[], BigDecimal y[]) {
		return solve(createMatrix(x,y));
	}
}
