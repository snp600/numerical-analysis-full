package main;

public class Solutions {
	// page 82
	public double kA;
	public double qA;
	public double fA;
	public double kB;
	public double qB;
	public double fB;
	
	public double x0;
	public double u0;
	public double uN;
	public double h;
	public int N;
	public int left;
	public int right;

	public Solutions (double x0, double u0, double uN, int nN) {
//		//3
//		this.kA = Math.sin(x0 * x0) + 1.0;
//		this.qA = x0;
//		this.fA = 1.0;
//		this.kB = Math.sin(x0 * x0) + 1.0;
//		this.qB = x0 * x0 * x0;
//		this.fB = x0 * x0 - 1.0;
		//2
		
		this.kA = x0 * x0 + 0.5;
		this.qA = 1.0;
		this.fA = 1.0;
		this.kB = x0 * x0 + 0.5;
		this.qB = Math.exp(-x0 * x0);
		this.fB = Math.cos(x0);		
		
		
		this.x0 = x0;
		this.u0 = u0;
		this.uN = uN;
		this.N = nN - 1;
		this.h = 1.0 / N;
		this.left = (int)Math.floor(x0 * N);
		this.right = this.left + 1;
	}
	
	/* returns array of 'C': C[0] == C1, C[1] == C2, C[2] == C3, C[3] == C4; */
	public double[] C() {
		/* analytical solution */
		double lambdaA = Math.sqrt(qA/kA);
		double lambdaB = Math.sqrt(qB/kB);
		double muA = fA/qA;
		double muB = fB/qB;
		// page 85
		double A11 = Math.exp(-lambdaA * x0) - Math.exp(lambdaA * x0);
		double A12 = Math.exp(lambdaB * (2.0 - x0)) - Math.exp(lambdaB * x0);
		double A21 = kA * lambdaA * (Math.exp(lambdaA * x0) + Math.exp(-lambdaA * x0));
		double A22 = kB * lambdaB * (Math.exp(lambdaB * (2.0 - x0)) + Math.exp(lambdaB * x0));
		
		double B1 = muB - muA + (muA - u0) * Math.exp(lambdaA * x0) 
								  -(muB - uN) * Math.exp(lambdaB * (1.0 - x0));
		double B2 = kA * lambdaA * (u0 - muA) * Math.exp(lambdaA * x0)
				                  + kB * lambdaB * (uN - muB) * Math.exp(lambdaB * (1.0 - x0));
		
		double[] C = new double[4];
		C[0] = (((u0 - muA) * A11 - B1) * A22 - ((u0 - muA) * A21 - B2) * A12) 
					/ (A11*A22 - A12 * A21);
		C[1] = (B1 * A22 - B2 * A12) / (A11 * A22 - A12 * A21);
		C[2] = (B2 * A11 - B1 * A21) / (A11 * A22 - A12 * A21);
		C[3] = (uN - muB) * Math.exp(lambdaB) - C[2] * Math.exp(2.0 * lambdaB);
		return C;
	}
	
	// returns Model-task' numerical solution u[]
	public double[] solveModel() {
	/* numerical solution */
	// page 88
		double aA = kA;
		double bA = -2.0 * kA - qA * h * h;
		double cA = kA;
		double dA = -fA * h * h;
		
		double aB = kB;
		double bB = -2.0 * kB - qB * h * h;
		double cB = kB;
		double dB = -fB * h * h;
		
		double[] alpha = new double[N + 1];
		double[] beta  = new double[N + 1];
		double[] u     = new double[N + 1];
		
		alpha[0] = -aA / bA;
		alpha[1] = -aA / bA;
		alpha[N-1] = -aB / bB;
		alpha[N] = -aB / bB;
		
		beta[0]  = (dA - cA * u0) / bA;
		beta[1]  = (dA - cA * u0) / bA;
		beta[N-1]  = (dB - cB * uN) / bB;
		beta[N]  = (dB - cB * uN) / bB;
		
		u[0] = u0;
		u[N] = uN;
		/* straight algorithm */
		for (int i = 2; i <= left; i++) {
			alpha[i] = -aA / (bA + cA * alpha[i-1]);
			beta[i]  = (dA - cA * beta[i-1]) / (bA + cA * alpha[i-1]);
		}
		for (int i = N - 2; i >= right + 1; i--) {
			alpha[i] = -aB / (bB + cB * alpha[i+1]);
			beta[i]  = (dB - cB * beta[i+1]) / (bB + cB * alpha[i+1]);
		}
		u[left] = (kA * beta[left-1] + kB * beta[right+1]) / 
				  (kA * (1.0 - alpha[left-1]) + kB * (1.0 - alpha[right+1]));
		u[right] = u[left];
		/* reversed algorithm */
		for (int i = left - 1; i >= 1; i--) 
			u[i] = alpha[i] * u[i+1] + beta[i];
		for (int i = right + 1; i < N; i++) 
			u[i] = alpha[i] * u[i-1] + beta[i];
		return u;
	}
	
	/* variable coefficients */
	public double[] solve() {
		//page 93
		double[] a = new double[N + 1];
		double[] b = new double[N + 1];
		double[] c = new double[N + 1];
		double[] d = new double[N + 1];
		
		for (int i = 1; i <= left; i++) {
			a[i] = kA(i * h + h * 0.5);
			b[i] = - (kA(i * h + h * 0.5) + kA(i * h - h * 0.5) + qA(i * h) * h * h);
			c[i] = kA(i * h - h * 0.5);
			d[i] = - fA(i * h) * h * h;
		}
		
		for (int i = right + 1; i <= N; i++) {
			a[i] = kB(i * h + h * 0.5);
			b[i] = - (kB(i * h + h * 0.5) + kB(i * h - h * 0.5) + qB(i * h) * h * h);
			c[i] = kB(i * h - h * 0.5);
			d[i] = - fB(i * h) * h * h;
		}
		double[] alpha = new double[N + 1];
		double[] beta  = new double[N + 1];
		double[] u = new double[N + 1];
		alpha[0] = -a[1] / b[1];
		alpha[1] = -a[1] / b[1];
		alpha[N-1] = -c[N-1] / b[N-1];
		alpha[N] = -c[N-1] / b[N-1];
		
		beta[0]  = (d[1] - c[1] * u0) / b[1];
		beta[1]  = (d[1] - c[1] * u0) / b[1];
		beta[N-1]  = (d[N-1] - c[N-1] * uN) / b[N-1];
		beta[N]  = (d[N-1] - c[N-1] * uN) / b[N-1];
		u[0] = u0;
		u[N] = uN;
		/* straight algorithm */
		for (int i = 2; i <= left; i++) {
			alpha[i] = -a[i] / (b[i] + c[i] * alpha[i-1]);
			beta[i]  = (d[i] - c[i] * beta[i-1]) / (b[i] + c[i] * alpha[i-1]);
		}
		for (int i = N - 2; i >= right + 1; i--) {
			alpha[i] = -c[i] / (b[i] + a[i] * alpha[i+1]);
			beta[i]  = (d[i] - a[i] * beta[i+1]) / (b[i] + a[i] * alpha[i+1]);
		}
		u[left] = (kA(left * h) * beta[left-1] + kB(right * h) * beta[right+1]) / 
				  (kA(left * h) * (1.0 - alpha[left-1]) + kB(right * h) * (1.0 - alpha[right+1]));
		u[right] = u[left];
		/* reversed algorithm */
		for (int i = left - 1; i >= 1; i--) 
			u[i] = alpha[i] * u[i+1] + beta[i];
		for (int i = right + 1; i < N; i++) 
			u[i] = alpha[i] * u[i-1] + beta[i];
		return u;
	}
	// 3
//	private double kA(double value) {
//		return Math.sin(value * value) + 1.0;
//	}
//	private double qA(double value) {
//		return value;
//	}
//	private double fA(double value) {
//		return 1.0;
//	}
//	private double kB(double value) {
//		return Math.sin(value * value) + 1.0;
//	}
//	private double qB(double value) {
//		return value * value * value;
//	}
//	private double fB(double value) {
//		return value * value - 1.0;
//	}
	
	//2
	private double kA(double value) {
		return value * value + 0.5;
	}
	private double qA(double value) {
		return 1.0;
	}
	private double fA(double value) {
		return 1.0;
	}
	private double kB(double value) {
		return value * value + 0.5;
	}
	private double qB(double value) {
		return Math.exp(-value * value);
	}
	private double fB(double value) {
		return Math.cos(value);
	}
}
