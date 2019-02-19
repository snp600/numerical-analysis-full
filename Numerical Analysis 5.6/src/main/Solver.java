package main;

public class Solver {
	private int N;
	private double tau;

	private int L;
	private double h;

	// k = tau / h;
	public Solver(int N, int L) {
		this.h = 1.0 / L;
		this.N = N;
		this.L = L + 1;
		this.tau = 1.0 / (N - 1);
	}

	public double[] getSolution() {
		double[][] u = mainAlgo();
		double[] r = new double[L];
		for (int l = 0; l < L; l++)
			r[l] = u[l][N - 1];
		return r;
	}

	private double[][] mainAlgo() {
		double[][] u = new double[L][N];
		u[0] = un_0();
		u[1] = un_1();
		u[2] = un_2();
		double[] buf = u0_l();
		for (int l = 0; l < L; l++)
			u[l][0] = buf[l];
		for (int l = 3; l < L; l++)
			for (int n = 0; n < N - 1; n++)
				u[l][n + 1] = u[l][n]
						+ tau
						/ 6.0
						/ h
						* (2.0 * u[l - 3][n] - 9.0 * u[l - 2][n] + 18.0
								* u[l - 1][n] - 11.0 * u[l][n])
						+ tau
						* tau
						/ 2.0
						/ h
						/ h
						* (-1.0 * u[l - 3][n] + 4.0 * u[l - 2][n] - 5.0
								* u[l - 1][n] + 2.0 * u[l][n])
						- tau
						* tau
						* tau
						/ 6.0
						/ h
						/ h 
						/ h
						* (-1.0 * u[l - 3][n] + 3.0 * u[l - 2][n] - 3.0
								* u[l - 1][n] + 1.0 * u[l][n]) + tau
						* Math.cos(h * l) + 0.5 * tau * tau * Math.sin(h * l)
						- tau * tau * tau / 6.0 * Math.cos(h * l);
		return u;
	}

	private double[] u0_l() {
		double[] ret = new double[L];
		for (int l = 0; l < L; l++)
			ret[l] = Math.log(1.0 + (h * l) * (h * l)) + Math.sin(h * l);
		return ret;
	}

	private double[] un_0() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++)
			ret[n] = Math.log(1.0 + (tau * n) * (tau * n));
		return ret;
	}

	private double[] un_1() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++) {
			double A = 1.0 + tau * tau * n * n;
			double u = Math.log(A);
			double ux = -2.0 * tau * n / A + 1.0;
			double uxx = 2.0 * (1.0 + tau * tau * n * n - tau * n) / A / A;
			double uxxx = -4.0 * tau * n * (-tau * tau * n * n + 3.0) / A / A
					/ A + 1.0;
			ret[n] = u + ux * h + uxx / 2.0 * h * h + uxxx / 6.0 * h * h * h;
		}
		return ret;
	}

	private double[] un_2() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++) {
			double A = 1.0 + tau * tau * n * n;
			double u = Math.log(A);
			double ux = -2.0 * tau * n / A + 1.0;
			double uxx = 2.0 * (1.0 - tau * tau * n * n) / A / A;
			double uxxx = -4.0 * tau * n * (-tau * tau * n * n + 3.0) / A / A
					/ A + 1.0;
			ret[n] = u + ux * 2.0 * h + uxx * 2.0 * h * h + uxxx * 4.0 / 3.0
					* h * h * h;
		}
		return ret;
	}
}
