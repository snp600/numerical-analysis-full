package main;

import static main.Task.X;
import static main.Task.Y;
import static tools.Calculator.calculateValue;
import static tools.CodeSimplifiers.showVector;
import static tools.CodeSimplifiers.derivative;
import static tools.CodeSimplifiers.round;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Interpolating polynomial coefficients:");
		Interpolator I = new Interpolator();
		double[] interpolation = I.makeInterpolation(X(0), Y(0));
		showVector(interpolation);
		
		System.out.println("\nSpline:");
		double[] dydxCoeffs = derivative(interpolation);
		double[] dydx = calculateValue(dydxCoeffs, X(0));

		Spline s = new Spline(X(0), Y(0), dydx);
		double x0 = 0.5; 
		s.makeAndShow(x0);
		showResult(x0, interpolation);
	}

	public static void showResult(double x, double[] interpolation) {
		double f = 0;
		for (int i = 0; i < interpolation.length; i++)
			f += interpolation[i] * Math.pow(x, i);
		System.out.println("f(x0) = " + round(f));
	}
	

}
