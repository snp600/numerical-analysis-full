package main;

public class Solver {
	private int N;
	private double tau;
	private int L;
	private double h;

	public Solver(int N, int L) {
		this.N = N;
		this.tau = 1.0 / (N - 1);
		this.L = L + 1;
		this.h = 1.0 / L;
	}

	public double[] getSolution() {
		double[][] u = solve();
		//t == 1.0
		double[] r = new double[L];
		for (int l = 0; l < L; l++)
			r[l] = u[l][N - 1];
		return r;
	}

	private double[][] solve() {
		double[][] u = new double[L][N];
		/* set boundaries */
		for (int l = 0; l < L; l++)
			u[l][0] = Math.log(1.0 + h * l * h * l);
		for (int n = 0; n < N; n++) {
			double t = tau * n;
			double A = t * t + 3.0 * t + 1.0;
			double B = t * t * t * t + 6.0 * t * t * t + 11.0 * t * t + 6.0 * t + 2.0;
			u[L - 1][n] = Math.log(1.0 + A * A);
			double ux = 2.0 * A / (A * A + 1.0);
			double uxx = 2.0 * (1.0 - A * A) / (A * A + 1.0) / (A * A + 1.0);
			double uxxx = 4 * A * (B - 4) / B / B / B;
			u[L - 2][n] = u[L - 1][n] - ux * h + uxx / 2.0 * h * h - uxxx / 6.0 * h * h * h;
			u[L - 3][n] = u[L - 1][n] - ux * 2.0 * h + uxx * 2.0 * h * h - uxxx / 3.0 * 4.0
					* h * h * h;	
		}
		/*                */
		double k = tau / h;
		for (int l = L - 4; l >= 0; l--)
			for (int n = 0; n < N - 1; n++)
				u[l][n + 1] = u[l][n]
						+ k
						/ 6.0
						* (2 * tau * n + 3.0 + tau)
						* (2.0 * u[l + 3][n] - 9.0 * u[l + 2][n] + 18.0
								* u[l + 1][n] - 11.0 * u[l][n])
						+ k
						* k
						/ 2.0
						* (2.0 * tau * n + 3.0)
						* (2.0 * tau * n + 3.0 + 2.0 * tau)
						* (-u[l + 3][n] + 4.0 * u[l + 2][n] - 5.0 * u[l + 1][n] + 2.0 * u[l][n])
						+ k
						* k
						* k
						/ 6.0
						* (2.0 * tau * n + 3.0)
						* (2.0 * tau * n + 3.0)
						* (2.0 * tau * n + 3.0)
						* (u[l + 3][n] - 3.0 * u[l + 2][n] + 3.0 * u[l + 1][n] - u[l][n]);
		return u;
	}
}
