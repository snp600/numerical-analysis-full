package main;

import java.util.Locale;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.StyleManager.LegendPosition;
import com.xeiam.xchart.SwingWrapper;

public class Main {
	// Variant 3 //Task 6
	private static int L = 10;
	private static double k = 1.6;

	/*
	 * k == 1.0 a == 3 k == 2.0 a == 3 k > 1 && k < 2 a == 2 k < 1 || k > 2 -
	 * unstable k == 2.9 : a == 2 k == 3 - converges, but approximation is bad
	 */
	private static int N = (int) Math.ceil(((L + 1) / k));

	public static void main(String[] args) {
		double[] asol = aSol();
		double[] nsol = (new Solver(N, L)).getSolution();
		System.out.println("           analytical    numerical         diff");
		showResults(asol, nsol);
		// getMaxDiff(asol, nsol, true);
		getMaxDif();
		// getMaxDiff();
		approximation();
		plot(asol, nsol);
	}

	public static void showResults(double[] asol, double[] nsol) {
		int c = nsol.length / 10;
		for (int i = 0; i < 11; i++)
			System.out.format(Locale.ENGLISH,
					"x = %.2f   %.8f   %.8f   %.8f\n", i * 0.1, asol[i], nsol[i
							* c], Math.abs(asol[i] - nsol[i * c]));
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
			sol[i] = Math.sin(i * 0.1)
					+ Math.log(1.0 + (i * 0.1 - 1.0) * (i * 0.1 - 1.0));
		return sol;
	}

	public static void plot(double[] asol, double[] numerical) {
		double[] nsol = new double[11];
		double[] x = new double[11];
		int c = numerical.length / 10;
		for (int i = 0; i < 11; i++) {
			nsol[i] = numerical[i * c];
			x[i] = i * 0.1;
		}
		Chart chart = new Chart(600, 500);
		chart.setChartTitle("Model task");
		chart.setXAxisTitle("X");
		chart.setYAxisTitle("Y");
		chart.addSeries("analytical", x, asol);
		chart.addSeries("numerical", x, nsol);
		chart.getStyleManager().setLegendPosition(LegendPosition.InsideNW);
		new SwingWrapper(chart).displayChart();
	}

	public static void approximation() {
		System.out.println("approximation:");
		int r = 8;
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
		for (int i = 0; i < r - 2; i++) {
			diff[i] = getMaxDiff(asol, solutions[i], false)
					/ getMaxDiff(asol, solutions[i + 1], false);
			double buf = (solutions[i][5] - solutions[i + 1][5]) / (solutions[i + 1][5] - solutions[i + 2][5]);
			System.out.format(Locale.ENGLISH,
					"L = %4d/%4d |  ratio = %.4f | log2(ratio) = %.2f | %.4f\n",
					(int) (10 * Math.pow(2, i)),
					(int) (10 * Math.pow(2, i + 1)), diff[i], Math.log(diff[i])
							/ Math.log(2), buf);
		}
	}

	public static void getMaxDif() {
		double[] d = new double[12];
		d[0] = 4.25413123 / 1000;
		for (int i = 0; i < 11; i++)
			d[i + 1] = d[i] / 7.95;
		System.out.println(d[(int) (Math.log(L / 10) / Math.log(2))]);
	}

	public static void getMaxDiff() {
		double[] d = new double[12];
		d[0] = 5.8781324 / 1000;
		for (int i = 0; i < 11; i++)
			d[i + 1] = d[0] / 7.95;
		System.out.println(d[(int) Math.log(L / 10)]);
	}
}
