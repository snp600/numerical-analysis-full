package main;

import java.util.Locale;

public class Main {
	// Variant 3 //Task 7
	private static int L = 130; // L % 10 == 0
	// k = tau / h
	private static double k = 1.5;
	/*
	 * stability area k >= 1 && k <= 2
	 */
	private static int N = (int) Math.ceil(((L + 1) / k));

	public static void main(String[] args) {
		double[] asol = aSol();
		double[] nsol = (new Solver(N, L)).getSolution();
		showResults(asol, nsol);
		getMaxDiff(asol, nsol, true);
		approximationCheck();
	}

	public static void showResults(double[] asol, double[] nsol) {
		int c = nsol.length / 10;
		System.out.println("         analytical    numerical          diff");
		for (int i = 0; i < 11; i++)
			System.out.format(Locale.ENGLISH, "x=%.2f   %.8f   %.8f   %.9f\n",
					i * 0.1, asol[i], nsol[i * c],
					Math.abs(asol[i] - nsol[i * c]));
	}

	public static double getMaxDiff(double[] asol, double[] nsol, boolean show) {
		int index = 0;
		double max = 0;
		int c = nsol.length / 10;
		for (int i = 0; i < asol.length; i++) {
			double difference = Math.abs(asol[i] - nsol[i * c]);
			if (difference > max) {
				index = i;
				max = difference;
			}
		}
		if (show)
			System.out.println("maxDiff = " + max + " (index = " + index + ")"
					+ " (x = " + 0.1 * index + ")");
		return max;
	}

	public static double[] aSol() {
		double[] sol = new double[11];
		for (int i = 0; i < 11; i++)
			// (i * 0.1) == xl
			sol[i] = (i * 0.1 + 1.0) * (i * 0.1 + 1.0) + Math.cos(0.1 * i);
		return sol;
	}

	public static void approximationCheck() {
		System.out.println("approximation:");
		final int r = 10;
		int LL = 10;
		double[][] solutions = new double[r][11];
		double[] asol = aSol();
		double[] diff = new double[r - 1];
		for (int i = 0; i < r; i++) {
			int NN = (int) Math.ceil(((LL + 1) / k));
			double[] buf = (new Solver(NN, LL)).getSolution();
			for (int j = 0; j < 11; j++)
				solutions[i][j] = buf[(int) (j * Math.pow(2, i))];
			LL *= 2;
		}
		for (int i = 0; i < r - 1; i++) {
			diff[i] = getMaxDiff(asol, solutions[i], false)
					/ getMaxDiff(asol, solutions[i + 1], false);
			System.out.format(Locale.ENGLISH,
					"L = %4d/%4d |  ratio = %.4f | log2(ratio) = %.2f\n",
					(int) (10 * Math.pow(2, i)),
					(int) (10 * Math.pow(2, i + 1)), diff[i], Math.log(diff[i])
							/ Math.log(2.0));
		}
	}
}
