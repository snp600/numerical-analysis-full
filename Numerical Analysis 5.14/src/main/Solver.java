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
						/ h
						/ 6.0
						* (Math.exp(tau * n) - tau / 2 * (1.0 - tau / 3.0) * h
								* l + h * l)
						* (2.0 * u[l - 3][n] - 9.0 * u[l - 2][n] + 18.0
								* u[l - 1][n] - 11.0 * u[l][n])
						+ tau
						* tau
						/ h
						/ h
						/ 2.0
						* (h * l + Math.exp(tau * n))
						* (h * l + Math.exp(tau * n) - tau * h * l)
						* (-u[l - 3][n] + 4.0 * u[l - 2][n] - 5.0 * u[l - 1][n] + 2.0 * u[l][n])
						+ tau
						* tau
						* tau
						/ 6.0
						/ h
						/ h
						/ h
						* (h * l + Math.exp(tau * n))
						* (h * l + Math.exp(tau * n))
						* (h * l + Math.exp(tau * n))
						* (u[l - 3][n] - 3.0 * u[l - 2][n] + 3.0 * u[l - 1][n] - 1.0 * u[l][n])
						+ tau
						* (h
								* l
								* (h * l + Math.exp(tau * n))
								- tau
								/ 2.0
								* (Math.exp(2.0 * n * tau) + 2.0 * h * l
										* Math.exp(tau * n) + 2.0 * h * h * l
										* l) + tau * tau * h * l / 6.0
								* (4.0 * h * l + 3.0 * Math.exp(tau * n)));
		return u;
	}

	private double[] u0_l() {
		double[] ret = new double[L];
		for (int l = 0; l < L; l++)
			ret[l] = h * l + 0.5 * h * h * l * l;
		return ret;
	}

	private double[] un_0() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++)
			ret[n] = -tau * n;
		return ret;
	}

	private double[] un_1() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++) {
			double u = -tau * n;
			double ux = Math.exp(-tau * n);
			double uxx = 1.0;
			ret[n] = u + ux * h + uxx * h * h / 2.0;
		}
		return ret;
	}

	private double[] un_2() {
		double[] ret = new double[N];
		for (int n = 0; n < N; n++) {
			double u = -tau * n;
			double ux = Math.exp(-tau * n);
			double uxx = 1.0;
			ret[n] = u + ux * 2.0 * h + uxx * h * h * 2.0;
		}
		return ret;
	}
}
