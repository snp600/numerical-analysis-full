package main;

import java.util.Locale;

//import com.xeiam.xchart.Chart;
//import com.xeiam.xchart.QuickChart;
//import com.xeiam.xchart.StyleManager.LegendPosition;
//import com.xeiam.xchart.SwingWrapper;

import keys.Keys;

public class Main {

	public static int N = 20;
	public static Solutions m = new Solutions(1.0 / Math.sqrt(2.0), 1.0, 0.0, N);
	
//	private static double[] x = fillIn(0.0, 1.0, 11);
	private static Tools tools = new Tools();
	
	public static void main(String[] args) {
		modelTask();
		mainTask();
	}
	
	public static void modelTask() {
		Keys k = Keys.ELEVEN;
		double[] as = k.getSolution();
		double[] sol = m.solveModel();
		System.out.println("Model task:");
		System.out.println("      analytical  numerical   difference");
		for (int i = 0; i < 11; i++) 
			System.out.format(Locale.ENGLISH, "i=%d   %.7f   %.7f   %.7f\n", 
					i, as[i], sol[(N - 1) * i / 10], Math.abs(as[i] - sol[(N - 1) * i / 10]));
		tools.showMaxDiff(sol, Keys.ELEVEN);
//		plot(as, sol);
	}
	
	public static void mainTask() {
		System.out.println("\nMain task:");
		double[] sol = m.solve();
		for (int i = 0; i < 11; i++)
			System.out.format(Locale.ENGLISH, "i=%d %.7f\n", i, sol[(N - 1) * i / 10]);
		plot(sol);
	}
	
	
	public static void plot(double[] y) {
		double[] numerical = new double[11];
		for (int i = 0; i < 11; i++)
			numerical[i] = y[(N - 1) * i / 10];
//	    Chart chart = QuickChart.getChart("Main task", "X", "Y", "y(x)", x, numerical);
//	    new SwingWrapper(chart).displayChart();
	}
	
	public static void plot(double[] analytical, double[] numerical) {
		double[] numerical11 = new double[11];
		for (int i = 0; i < 11; i++)
			numerical11[i] = numerical[(N - 1) * i / 10];		
//		Chart chart = new Chart(600, 500);
//	    chart.setChartTitle("Model task");
//	    chart.setXAxisTitle("X");
//	    chart.setYAxisTitle("Y");
//	    chart.addSeries("analytical", x, analytical);
//	    chart.addSeries("numerical", x, numerical11);
//	    chart.getStyleManager().setLegendPosition(LegendPosition.InsideNW);
//	    new SwingWrapper(chart).displayChart();
	}
	
	public static double[] fillIn(double x0, double xN, int N) {
		double[] x = new double[N];
		double h = Math.abs(xN - x0) / N;
		x[0] = x0;
		for (int i = 1; i < N; i++)
			x[i] = i * h;
		return x;
	}

}
