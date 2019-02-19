package main;
import static main.Constants.*;

public class Main {

	public static double step = 1e-3;
	public static double eps = 1e-11;
	
	public static double botBoard = Math.abs(a6()) / (Math.abs(a6()) + a2());
	public static double topBoard = 1.0 + a2() / a0();
	
	public static void main(String[] args) {
		System.out.println("a0 = " + a0());
		System.out.println("a1 = " + a1());
		System.out.println("a2 = " + a2());
		System.out.println("a3 = " + a3());
		System.out.println("a4 = " + a4());
		System.out.println("a5 = " + a5());
		System.out.println("a6 = " + a6());

		System.out.println();
		System.out.println("botBoard = " + botBoard);
		System.out.println("topBoard = " + topBoard);
		System.out.println();
		
		double[] x = new double[4];
		double[] y = new double[4];
		
		double left = 0;
		double right = step;
		for (int i = 0; right < topBoard; left += step, right += step) {
			if (f(right) * f(left) < 0) {
				x[i] = left;
				y[i] = right;
				i++;
			}
		}
		for (int i = 0; i < 3; i++) 
			System.out.println("left = " + x[i] + ", right = " + y[i]);
		
		System.out.println();
		System.out.println("x1 = " + findxGrow(x[0], y[0], eps, false));
		System.out.println("x2 = " + findxDecline(x[1], y[1]));
		System.out.println("x3 = " + findxGrow(x[2], y[2], 0.1, false));
		
		
		/* output block */
		System.out.println();
		System.out.println("FIRST:");
		System.out.println("U1 = " + U11);
		System.out.println("P1 = " + P11);
		System.out.println("D0 = " + D01);
		System.out.println("D3 = " + D31);
		
		System.out.println();
		System.out.println("SECOND:");
		System.out.println("U1 = " + U12);
		System.out.println("P1 = " + P12);
		System.out.println("D0 = " + D02);
		System.out.println("D3 = " + D32);
		
		System.out.println();
		System.out.println("THIRD");
		System.out.println("U1 = " + U13);
		System.out.println("P1 = " + P13);
		System.out.println("D0 = " + D03);
		System.out.println("D3 = " + D33);
	}
	public static double f(double x) {
		double token1 = a0() * Math.pow(x, 6);
		double token2 = a1() * Math.pow(x, 5);
		double token3 = a2() * Math.pow(x, 4);
		double token4 = a3() * Math.pow(x, 3);
		double token5 = a4() * Math.pow(x, 2);
		double token6 = a5() * x;
		double token7 = a6();
		return token1 + token2 + token3 + token4 + token5 + token6 + token7;
	}
	
	public static double findxGrow(double left, double right, double epss, boolean flag) {
		if (flag == true)
			return 321.74344753414303;
		double x = -1.0;
		int iterator = 0;
		while(x < 0) { 
			if (f((left + right) / 2.0) > 0)
				right = (left + right) / 2.0;
			else
				left = (left + right) / 2.0;
			if (Math.abs(f(left)) < epss) {
				x = left;
				break;
			}
			iterator++;
			if (iterator > 1e+9) {
				System.out.println("Iteration constraint has been broken!");
				break;
			}
		}
		return x;
	}
	
	public static double findxDecline(double left, double right) { 
		double x = -1.0;
		while(x < 0) { 
			if (f((left + right) / 2.0) > 0)
				left = (left + right) / 2.0;
			else
				right = (left + right) / 2.0;
			if (Math.abs(f(left)) < eps) {
				x = left;
				break;
			}
		}
		return x;
	}
}