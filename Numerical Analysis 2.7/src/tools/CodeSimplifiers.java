package tools;

import java.math.BigDecimal;

public class CodeSimplifiers {

	public static BigDecimal[] fromDoubleToBD(double[] a) {
		BigDecimal[] BD = new BigDecimal[a.length];
		for (int i = 0; i < a.length; i++)
			BD[i] = new BigDecimal(a[i]);
		return BD;
	}
	
	public static void showVector(BigDecimal[] vector) {
		for (int i = 0; i < vector.length; i++)
			System.out.print(vector[i] + " ");
		System.out.println();
	}
	
	public static void showVector(double[] vector) {
		for (int i = 0; i < vector.length; i++)
			System.out.print(vector[i] + " ");
		System.out.println();
	}
	
	public static double[] derivative(BigDecimal[] f) {
		int N = f.length;
		double[] dfdx = new double[N - 1];
		for (int i = 1; i < N; i++) 
			dfdx[i - 1] = f[i].doubleValue() * i;
		return dfdx;
	}
}
