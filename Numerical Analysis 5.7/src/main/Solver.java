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
			u[l][0] = h * h * l * l + Math.cos(h * l);
		for (int n = 0; n < N; n++) {
			u[L - 1][n] = (1.0 + tau * n) * (1.0 + tau * n) + Math.cos(1.0);
			double ux = 2.0 + 2.0 * tau * n - Math.sin(1.0);
			double uxx = 2.0 - Math.cos(1.0);
			double uxxx = Math.sin(1.0);
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
						* (2.0 * u[l + 3][n] - 9.0 * u[l + 2][n] + 18.0
								* u[l + 1][n] - 11.0 * u[l][n])
						+ k
						* k
						/ 2.0
						* (-u[l + 3][n] + 4.0 * u[l + 2][n] - 5.0 * u[l + 1][n] + 2.0 * u[l][n])
						+ k
						* k
						* k
						/ 6.0
						* (u[l + 3][n] - 3.0 * u[l + 2][n] + 3.0 * u[l + 1][n] - u[l][n])
						+ tau * Math.sin(h * l) + 0.5 * tau * tau
						* Math.cos(h * l) - tau * tau * tau / 6
						* Math.sin(h * l);
		return u;
	}
}
