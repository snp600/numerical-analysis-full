package tools;

public class CodeSimplifiers {
	
	public static void showVector(double[] vector) {
		for (int i = 0; i < vector.length; i++)
			System.out.print(round(vector[i]) + " ");
		System.out.println();
	}
	
	public static double[] derivative(double[] f) {
		int N = f.length;
		double[] dfdx = new double[N - 1];
		for (int i = 1; i < N; i++) 
			dfdx[i - 1] = f[i] * i;
		return dfdx;
	}
	
	public static double round(double x) {
		double eps = 1e-8;
		if (Math.abs(x - Math.round(x)) < eps)
			return Math.round(x);
		return x;
	}
}
