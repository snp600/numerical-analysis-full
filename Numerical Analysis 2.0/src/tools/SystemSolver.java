package tools;

public class SystemSolver {

	private static double eps = 1e-10;

	public static double[] solve(double[][] a) {
		if (a[0].length != a.length + 1)
			throw new IllegalArgumentException();
		int N = a.length;
		double[] x = new double[N];
		for (int p = 0; p < N; p++) {
			if (Math.abs(a[p][p]) < eps) {
				for (int z = p + 1; z < N; z++)
					if (Math.abs(a[z][p]) > eps) {
						double B = a[p][N];
						a[p][N] = a[z][N];
						a[z][N] = B;
						for (int q = p; q < N; q++) {
							double A = a[p][q];
							a[p][q] = a[z][q];
							a[z][q] = A;
						}
						break;
					}
			}
			if (Math.abs(a[p][p]) < eps) {
				System.out.println("Determinant == 0");
				throw new IllegalArgumentException();
			}
			for (int i = p + 1; i < N; i++) {
				double dif = a[i][p] / a[p][p];
				for (int j = p; j < N; j++)
					a[i][j] = a[i][j] - a[p][j] * dif;
				a[i][N] = a[i][N] - a[p][N] * dif;
			}
		}
		int v = 1;
		x[0] = a[N - 1][N] / a[N - 1][N - 1];
		for (int i = N - 1; i > 0; i--, v++) {
			x[N - i] = a[i - 1][N];
			for (int q = 1; q <= v; q++)
				x[N - i] = x[N - i] - x[q - 1] * a[i - 1][N - q];
			x[N - i] = x[N - i] / a[N - v - 1][N - v - 1];
		}
		return reverse(x);
	}
	
	private static double[] reverse(double[] x) {
		int N = x.length;
		double[] reversedX = new double[N];
		for (int i = 0; i < N; i++)
			reversedX[i] = x[N - i - 1];
		return reversedX;
	}
}
