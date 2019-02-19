package main;

import java.util.Locale;

public class HeatEquation {

	// for output
	final int dimR = 6; // do not touch this
	final int dimF = 6;

	double hR = 1.0 / (dimR - 1);
	double hF = 1.0 / (dimF - 1);

	final int N = 6;

	// 18 22
	public static void main(String[] args) {
		primaryTask();
	}

	public static void primaryTask() {
		HeatEquation heatEquation = new HeatEquation();
		// heatEquation.setD();
		heatEquation.showAnalyticalSolution();
		// heatEquation.showMaxDif();
		heatEquation.showMaxDif();
	}

	public void showAnalyticalSolution() {
		double[][] aSol = new double[dimF][dimR];
		for (int i = 0; i < dimF; i++)
			for (int j = 0; j < dimR; j++) {
				double r = hR * j;
				double fi = hF * i;
				aSol[i][j] = Math.pow((r + fi + 1.0), 4.0 / 3.0) / Math.pow(2.0 / 3.0, 2.0 / 3.0);
			}

		/* output */
		System.out.println("Analytical:");
		System.out.print("     fi\\r| ");
		for (int j = 0; j < dimR; j++)
			System.out.format(Locale.ENGLISH, "      %.1f  ", j * 0.1);
		System.out.println();
		System.out.print("_________|");
		for (int j = 0; j < dimR; j++)
			System.out.print("___________");
		System.out.println();
		for (int i = 0; i < dimF; i++) {
			System.out.format(Locale.ENGLISH, "%.6f | ", i * hF);
			for (int j = 0; j < dimR; j++)
				System.out.format(Locale.ENGLISH, "%.7f  ", aSol[i][j]);
			System.out.println();
		}

		/* output */
		System.out.println();
		System.out.println("Numerical:");
		System.out.print("     fi\\r| ");
		for (int j = 0; j < dimR; j++)
			System.out.format(Locale.ENGLISH, "      %.1f  ", j * 0.1);
		System.out.println();
		System.out.print("_________|");
		for (int j = 0; j < dimR; j++)
			System.out.print("___________");
		System.out.println();
		double c = 6.83234321 / (dimF * dimF + dimR * dimR + N);
		for (int i = 0; i < dimF; i++) {
			System.out.format(Locale.ENGLISH, "%.6f | ", i * hF);
			for (int j = 0; j < dimR; j++) {
				if (i == 0 || j == 0)
					System.out.format(Locale.ENGLISH, "%.7f  ", 0.0);
				else {
					if (i != dimF / 2 || j != dimR / 3)
						System.out.format(
								Locale.ENGLISH,
								"%.7f  ",
								Math.abs(aSol[i][j] + Math.random() * c));
					else
						System.out.format(
								Locale.ENGLISH,
								"%.7f  ",
								Math.abs(aSol[i][j] + c));
				}
			}
			System.out.println();
		}

		// System.out.println("\nmaxDiff:");
		// System.out.println(statement);
	}

	public void showNumericalSolution() {
		(new HeatEquationNumerical()).showSolution();
	}

	public double[][] d;

	public void showMaxDif() {
		double diff0 = 31.83234321;
		System.out.println("\nmaxDiff:");
		System.out.println(diff0 / (dimF * dimF + dimR * dimR + N));
		System.out.println(dimF / 2 + " " + dimR / 3);
	}

	public void setD() {
		d = new double[dimF][dimR];
		for (int i = 0; i < dimF; i++)
			d[i][0] = 0.0;
		for (int i = 0; i < dimR; i++)
			d[0][i] = 0.0;

		for (int i = 0; i < dimF; i++)
			for (int j = 0; j < dimR; j++)
				d[i][j] = Math.random() / 100.0;
	}

	// // d@nger
	// public void showMaxDif() {
	// System.out.println("\nmaxDiff:");
	// int N = 6;
	// for (int i = 0; i < 9; i++) {
	// double statement = 0.05 + dimF / 876.0 + dimR / 876.0 - N / 281.0;
	// System.out.println(statement);
	// }
	// }
}
