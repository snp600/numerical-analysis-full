package main;

public class Solver {
	private int N;
	private double tau;

	private int L;
	private double h;

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
		u[L - 1] = un_L();
		u[L - 2] = un_L1();
		u[L - 3] = un_L2();
		double[] buf = u0_l();
		for (int l = 0; l < L; l++)
			u[l][0] = buf[l];
		for (int l = L - 4; l >= 0; l--)
			for (int n = 0; n < N - 1; n++)
				u[l][n + 1] = u[l][n] + tau
						/ 6.0
						/ h
						* (1.0 + 0.5 * tau + tau * tau / 6)
						* (h * l + 4.0)
						* (2.0 * u[l + 3][n] - 9.0 * u[l + 2][n] + 18.0
								* u[l + 1][n] - 11.0 * u[l][n])
						+ tau
						* tau
						/ 2.0
						/ h
						/ h
						* (1.0 + tau)
						* (h * l + 4.0)
						* (h * l + 4.0)
						* (-u[l + 3][n] + 4.0 * u[l + 2][n] - 5.0 * u[l + 1][n] + 2.0 * u[l][n])
						+ tau
						* tau
						* tau
						/ 6.0
						/ h
						/ h
						/ h
						* (h * l + 4.0)
						* (h * l + 4.0)
						* (h * l + 4.0)
						* (u[l + 3][n] - 3.0 * u[l + 2][n] + 3.0 * u[l + 1][n] - 1.0 * u[l][n]);
		return u;
	}

	private double[] u0_l() {
		double[] ret = new double[L];
		for (int l = 0; l < L; l++)
			ret[l] = (4.0 + h * l) * (4.0 + h * l);
		return ret;
	}

	private double[] un_L() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++)
			ret[n] = 25.0 * Math.exp(2.0 * tau * n);
		return ret;
	}

	private double[] un_L1() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++) {
			double u = 25.0 * Math.exp(2.0 * tau * n);
			double ux = u / 2.5;
			double uxx = ux / 5;
			// uxxx == 0
			ret[n] = u - ux * h + uxx / 2.0 * h * h;
		}
		return ret;
	}

	private double[] un_L2() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++) {
			double u = 25.0 * Math.exp(2.0 * tau * n);
			double ux = u / 2.5;
			double uxx = ux / 5;
			// uxxx == 0
			ret[n] = u - ux * 2.0 * h + uxx * 2.0 * h * h;
		}
		return ret;
	}
}
