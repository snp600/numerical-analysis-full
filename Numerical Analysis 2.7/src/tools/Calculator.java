package tools;

public class Calculator {

	public static double calculateValue(double[] f, double x) {
		double value = 0.0;
		for (int i = 0; i < f.length; i++)
			value += f[i] * Math.pow(x, i);
		return value;
	}
	
	public static double[] calculateValue(double[] f, double[] x) {
		double[] value = new double[x.length];
		for (int i = 0; i < x.length; i++)
			value[i] = calculateValue(f, x[i]);
		return value;
	}
	
}
