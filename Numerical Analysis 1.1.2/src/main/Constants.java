package main;

public class Constants {

	public static double gamma0 = 7.0 / 5.0;
	public static double ro0 = 7.9;
	public static double U0 = 0.0;
	public static double P0 = 3.04 * 1e+9;
	public static double gamma3 = 7.0 / 5.0;
	public static double ro3 = 11.37;
	public static double U3 = 5.0 * 1e+4;
	public static double P3 = 1.17928 * 1e+9;

	public static double C0 = Math.sqrt(gamma0 * P0 / ro0);
	public static double C3 = Math.sqrt(gamma3 * P3 / ro3);

	public static double e0 = (2.0 * C0 * C0)
			/ (gamma0 * (gamma0 - 1.0) * Math.pow(U3 - U0, 2));
	public static double e3 = (2.0 * C3 * C3)
			/ (gamma3 * (gamma3 - 1.0) * Math.pow(U3 - U0, 2));

	public static double alpha0 = (gamma0 + 1) / (gamma0 - 1);
	public static double alpha3 = (gamma3 + 1) / (gamma3 - 1);
	public static double X = P3 / P0;

	public static double x1 = 0.007020312190055847;
	public static double x2 = 4.000837915794071;
	public static double x3 = 321.74344753414306;

	public static double eps0 = P0 / (gamma0 - 1) / ro0;
	public static double eps3 = P3 / (gamma0 - 1) / ro3;
	
	public static double P11 = P0 * x(1);
	public static double U11 = U0 - C0 * Math.sqrt(2.0) * (x(1) - 1.0)
			/ (Math.sqrt(gamma0 * (gamma0 - 1.0) * (1.0 + alpha0 * x(1))));
	public static double D01 = (P11 - P0) / (U11 * ro0);
	public static double D31 = (P3 - P11 - U11 * U3 * ro3 * U3 * U3) / ((U3 - U11) * ro3);
	
	public static double P12 = P0 * x(2);
	public static double U12 = U0 + C0 * Math.sqrt(2.0) * (x(2) - 1.0)
			/ (Math.sqrt(gamma0 * (gamma0 - 1.0) * (1.0 + alpha0 * x(2))));
	public static double D02 = (P12 - P0) / (U12 * ro0);
	public static double D32 = (P3 - P12 - U12 * U3 * ro3 * U3 * U3) / ((U3 - U12) * ro3);
	
	public static double P13 = P0 * x(3);
	public static double U13 = U0 + C0 * Math.sqrt(2.0) * (x(3) - 1.0)
			/ (Math.sqrt(gamma0 * (gamma0 - 1.0) * (1.0 + alpha0 * x(3))));
	public static double D03 = (P13 - P0) / (U13 * ro0);
	public static double D33 = (P3 - P13 - U13 * U3 * ro3 * U3 * U3) / ((U3 - U13) * ro3);

	public static double x(int key) {
		if (key == 1)
			return x1;
		else if (key == 2)
			return x2;
		else if (key == 3)
			return x3;
		return 0;
	}

	public static double a0() {
		return Math.pow(alpha0 * e3 - alpha3 * X * e0, 2);
	}

	public static double a1() {
		double token1 = alpha0 * e3 - alpha3 * X * e0;
		double token2 = e3 * (1.0 - 2.0 * alpha0 * X) - e0 * X
				* (X - 2.0 * alpha3);
		double token3 = alpha0 * alpha3 * X * (alpha0 * e3 + alpha3 * X * e0);
		return 2.0 * (token1 * token2 - token3);
	}

	public static double a2() {
		double token1 = 6.0 * alpha0 * alpha0 * X * X - 8.0 * alpha0 * X + 1;
		double token2 = alpha0 * alpha3 * (X * X + 4.0 * X + 1.0) - 2.0
				* (X + 1.0) * (alpha3 + alpha0 * X) + X;
		double token3 = 6.0 * alpha3 * alpha3 - 8.0 * alpha3 * X + X * X;
		double token4 = alpha0 * alpha0 * alpha3 * alpha3 * X * X;
		double token5 = alpha0 * X - 2.0 * alpha3 * alpha3 * X + 2.0 * alpha3;
		double token6 = alpha3 + 2.0 * alpha0 * X - 2.0 * alpha0 * alpha3;
		return (e3 * e3 * token1) - (2.0 * e0 * e3 * X * token2)
				+ (e0 * e0 * X * X * token3) + (token4)
				- (2.0 * alpha0 * X * e3 * token5)
				- (2.0 * alpha3 * X * X * e0 * token6);
	}

	public static double a3() {
		double token1 = 2.0 * e3 * e3
				* (alpha0 * alpha0 * X * X - 3.0 * alpha0 * X + 1.0);
		double token2 = (alpha3 + alpha0 * X) * (X * X + 4.0 * X + 1.0) - 2.0
				* alpha0 * alpha3 * X * (X + 1.0) - 2.0 * X * (X + 1.0);
		double token3 = 2.0 * e0 * e0 * X
				* (X * X - 3.0 * alpha3 * X + alpha3 * alpha3);
		double token4 = alpha0 * alpha3 * X * (alpha0 * X + alpha3);
		double token5 = alpha0 * alpha0 * alpha3 * X * X - 2.0 * X
				* (2.0 * alpha0 * alpha3 + alpha0 * alpha0 * X)
				+ (2.0 * alpha0 * X + alpha3);
		double token6 = alpha0 * alpha3 * alpha3 - 2.0 * alpha3
				* (alpha3 + 2.0 * alpha0 * X) + 2.0 * alpha3 * X + alpha0 * X
				* X;
		return -2.0
				* X
				* (token1 + e0 * e3 * token2 + token3 - token4 + e3 * token5 + e0
						* X * token6);
	}

	public static double a4() {
		double token1 = e3 * e3
				* (alpha0 * alpha0 * X * X - 8.0 * alpha0 * X + 6.0);
		double token2 = alpha0 * alpha3 * X - 2.0 * (X + 1.0)
				* (alpha3 + alpha0 * X) + X * X + 4.0 * X + 1.0;
		double token3 = alpha3 * alpha3 - 8.0 * alpha3 * X + 6.0 * X * X;
		double token4 = alpha3 * alpha3 + 4.0 * alpha0 * alpha3 * X + alpha0
				* alpha0 * X * X;
		double token5 = (alpha0 * alpha0 * X + 2.0 * alpha0 * alpha3) * X - 2.0
				* (2.0 * alpha0 * X + alpha3) + 1.0;
		double token6 = alpha3 * (2.0 * alpha0 * X + alpha3) - 2.0 * X
				* (2.0 * alpha3 + alpha0 * X) + X * X;
		return X
				* X
				* ((token1 - 2.0 * e0 * e3 * token2) + e0 * e0 * token3
						+ token4 - 2.0 * e3 * token5 - 2.0 * e0 * token6);
	}

	public static double a5() {
		double token1 = e3 * e3 * (alpha0 * X - 2.0) - e0 * e3
				* (alpha0 * X - 2 + alpha3 - 2 * X);
		double token2 = e0 * e0 * (alpha3 - 2.0 * X) + (alpha3 + alpha0 * X);
		double token3 = e3 * (2.0 * alpha0 * X + alpha3 - 2.0) + e0
				* (2.0 * alpha3 + alpha0 * X - 2.0 * X);
		return 2.0 * X * X * X * (token1 + token2 - token3);
	}

	public static double a6() {
		return Math.pow(X, 4) * ((e3 - e0) * (e3 - e0) + 1.0 - 2.0 * (e3 + e0));
	}
}
