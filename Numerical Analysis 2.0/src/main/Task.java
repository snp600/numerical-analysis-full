package main;

public class Task {

	public static double[] X(int task) {
		switch(task) {
		case 7: 
			double[] x7 = new double[6];
			x7[0] = 0.52360;
			x7[1] = 0.87267;
			x7[2] = 1.22173;
			x7[3] = 1.57080;
			x7[4] = 1.91986;
			x7[5] = 2.26893;
			return x7;
		case 9: 
			double[] x9 = new double[7];
			x9[0] = 0.87267;
			x9[1] = 1.22173;
			x9[2] = 1.57080;
			x9[3] = 1.91986;
			x9[4] = 2.26893;
			x9[5] = 2.61799;
			x9[6] = 2.96700;
			return x9;
		case 14: 
			double[] x14 = new double[7];
			x14[0] = 0.17453;
			x14[1] = 0.52360;
			x14[2] = 0.87267;
			x14[3] = 1.22173;
			x14[4] = 1.57080;
			x14[5] = 1.91986;
			x14[6] = 2.26893;
			return x14;
		case 0: 
			double[] x = new double[7];
			x[0] = 0;
			x[1] = 1.0;
			x[2] = 2.0;
			x[3] = 3.0;
			x[4] = 4.0;
			x[5] = 5.0;
			x[6] = 6.0;
			return x;
		}
		return null;
	}
	
	public static double[] Y(int task) {
		switch(task) {
		case 7: 
			double[] y7 = new double[6];
			y7[0] = 0.000014;
			y7[1] = 0.00037;
			y7[2] = 0.00352;
			y7[3] = 0.01971;
			y7[4] = 0.08033;
			y7[5] = 0.26380;
			return y7;
		case 9: 
			double[] y9 = new double[7];
			y9[0] = 0.00082;
			y9[1] = 0.01039;
			y9[2] = 0.07037;
			y9[3] = 0.32762;
			y9[4] = 1.18669;
			y9[5] = 3.59003;
			y9[6] = 9.48350;
			return y9;
		case 14: 
			double[] y14 = new double[7];
			y14[0] = 0.000012;
			y14[1] = 0.00026;
			y14[2] = 0.00250;
			y14[3] = 0.01815;
			y14[4] = 0.09763;
			y14[5] = 0.40593;
			y14[6] = 1.38035;
			return y14;
		case 0: 
			double[] y = new double[7];
			y[0] = -1.0;
			y[1] = 1.0;
			y[2] = 5.0;
			y[3] = 11.0;
			y[4] = 19.0;
			y[5] = 29.0;
			y[6] = 41.0;
			return y;
		}
		return null;
	}
}
