package main;

import java.util.Locale;

//import com.xeiam.xchart.Chart;
//import com.xeiam.xchart.StyleManager.LegendPosition;
//import com.xeiam.xchart.SwingWrapper;

public class Main {
	// Variant 3 //Task 12
	private static int L = 10;
	//k = h / tau
	private static double k = 3.0;
	
/*
 * 4.23 >= k >= 2.5 
 */
	private static int N = (int) Math.ceil(((L + 1) * k));

	public static void main(String[] args) {
		double[] asol = aSol();
		double[] nsol = (new Solver(N, L)).getSolution();
		System.out.println("          analytical     numerical        diff");
		showResults(asol, nsol);
		getMaxDiff(asol, nsol, true);
		approximation();
//		plot(asol, nsol);
	}

	public static void showResults(double[] asol, double[] nsol) {
		int c = nsol.length / 10;
		for (int i = 0; i < 11; i++)
			System.out.format(Locale.ENGLISH, "x=%.2f   %.7f   %.7f   %.7f\n",
					i * 0.1, asol[i], nsol[i * c],
					Math.abs(asol[i] - nsol[i * c]));
	}

	public static double getMaxDiff(double[] asol, double[] nsol, boolean show) {
		int index = 0;
		double max = 0;
		int c = nsol.length / 10;
		for (int i = 1; i < asol.length; i++) {
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
			sol[i] = (0.1 * i + 4) * (0.1 * i + 4) * Math.exp(2.0);
		return sol;
	}

//	public static void plot(double[] asol, double[] numerical) {
//		double[] nsol = new double[11];
//		double[] x = new double[11];
//		int c = numerical.length / 10;
//		for (int i = 0; i < 11; i++) {
//			nsol[i] = numerical[i * c];
//			x[i] = i * 0.1;
//		}
//		Chart chart = new Chart(600, 500);
//		chart.setChartTitle("Model task");
//		chart.setXAxisTitle("X");
//		chart.setYAxisTitle("Y");
//		chart.addSeries("analytical", x, asol);
//		chart.addSeries("numerical", x, nsol);
//		chart.getStyleManager().setLegendPosition(LegendPosition.InsideNW);
//		new SwingWrapper(chart).displayChart();
//	}

	public static void approximation() {
		System.out.println("approximation:");
		int r = 9;
		int LL = 10;
		double[][] solutions = new double[r][11];
		double[] asol = aSol();
		double[] diff = new double[r - 1];
		for (int i = 0; i < r; i++) {
			int NN = (int) Math.ceil(((LL + 1) * k));
			double[] buf = (new Solver(NN, LL)).getSolution();
			for (int j = 0; j < 11; j++)
				solutions[i][j] = buf[(int) (j * Math.pow(2, i))];
			LL *= 2;
		}
		for (int i = 0; i < r - 1; i++) {
			diff[i] = getMaxDiff(asol, solutions[i], false)
					/ getMaxDiff(asol, solutions[i + 1], false);
			System.out.format(Locale.ENGLISH, "L = %4d/%4d |  ratio = %.4f | log2(ratio) = %.2f\n",
					(int) (10 * Math.pow(2, i)), (int) (10 * Math.pow(2, i + 1)), diff[i], Math.log(diff[i]) / Math.log(2));
		}
	}
}
