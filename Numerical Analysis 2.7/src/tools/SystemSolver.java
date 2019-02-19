package tools;

import java.math.BigDecimal;
import java.math.MathContext;

public class SystemSolver {

	private static double eps = 1e-8;

	public static BigDecimal[] solve(BigDecimal[][] a) {
		if (a[0].length != a.length + 1)
			throw new IllegalArgumentException();
		int N = a.length;
		BigDecimal[] x = new BigDecimal[N];
		for (int p = 0; p < N; p++) {
			if (a[p][p].abs().doubleValue() < eps) {
				for (int z = p + 1; z < N; z++)
					if (!a[z][p].equals(0.0)) {
						BigDecimal B = a[p][N];
						a[p][N] = a[z][N];
						a[z][N] = B;
						for (int q = p; q < N; q++) {
							BigDecimal A = a[p][q];
							a[p][q] = a[z][q];
							a[z][q] = A;
						}
						break;
					}
			}
			if (a[p][p].abs().doubleValue() < eps) {
				System.out.println("Determinant == 0");
				throw new IllegalArgumentException();
			}
			for (int i = p + 1; i < N; i++) {
				BigDecimal dif = a[i][p].divide(a[p][p], MathContext.DECIMAL32);
				for (int j = p; j < N; j++)
					a[i][j] = a[i][j].subtract(a[p][j].multiply(dif));
				a[i][N] = a[i][N].subtract(a[p][N].multiply(dif));
			}
		}
		int v = 1;
		x[0] = a[N - 1][N].divide(a[N - 1][N - 1], MathContext.DECIMAL32);
		for (int i = N - 1; i > 0; i--, v++) {
			x[N - i] = a[i - 1][N];
			for (int q = 1; q <= v; q++)
				x[N - i] = x[N - i]
						.subtract(x[q - 1].multiply(a[i - 1][N - q]));
			x[N - i] = x[N - i].divide(a[N - v - 1][N - v - 1],
					MathContext.DECIMAL32);
		}
		return x;
	}
}

