package main;

import java.util.Locale;

public class HeatEquationNumerical {
	double c = 7.0;
	double mu = 1.0;
//	double delta = 0.0;

	int N = 6; // t
	int L = 6; // r //K
	int M = 6; // fi //L

	double hR = 1.0 / (L - 1);
	double hF = Math.PI / (M - 1) / 2;
	// double tau = (c * mu / 2 / (mu + 2)) / N;
	double tau = 1.0 / (N - 1);

	double[][][] u;

	public HeatEquationNumerical() {
		this.u = new double[N][L][M];
	}

	public void showSolution() {
		this.solve();
	}

	private void solve() {
		setBoundary();
		for (int m = 0; m < M; m++) {
			System.out.println();
			for (int l = 0; l < L; l++)
				System.out.format(Locale.ENGLISH, "%.4f  ", u[N - 1][l][m]);
		}
	}

	private void setBoundary() {
		// initial
		for (int l = 0; l < L; l++)
			for (int m = 0; m < M; m++) {
				double r = hR * l;
				double fi = hF * m;
				u[0][l][m] = r * r * Math.sin(fi) * Math.sin(fi)
						* Math.pow(c, -1 / mu);
			}
		// boundary for R
		for (int n = 0; n < N; n++)
			for (int m = 0; m < M; m++) {
				double fi = hF * m;
				double bracket = c - 2 * (mu + 2) / mu * tau * n;
				u[n][0][m] = 0.0;
				u[n][L - 1][m] = Math.sin(fi) * Math.sin(fi)
						* Math.pow(bracket, -1 / mu);
			}
		// boundary for FI
		for (int n = 0; n < N; n++)
			for (int l = 0; l < M; l++) {
				double r = hR * l;
				double bracket = c - 2 * (mu + 2) / mu * tau * n;
				u[n][l][0] = 0.0;
				u[n][l][M - 1] = r * r * Math.pow(bracket, -1 / mu);
			}
	}
}
