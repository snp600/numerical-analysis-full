package main;

import java.math.BigDecimal;
import static main.Task.X;
import static main.Task.Y;
import static tools.Calculator.calculateValue;
import static tools.CodeSimplifiers.showVector;
import static tools.CodeSimplifiers.derivative;;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Interpolating polynomial coefficients:");
		Interpolator I = new Interpolator();
		BigDecimal[] interpolation = I.makeInterpolation(X(0), Y(0));
		showVector(interpolation);
		
		System.out.println("\nSpline:");
		double[] dydxCoeffs = derivative(interpolation);
		double[] dydx = calculateValue(dydxCoeffs, X(0));

		Spline s = new Spline(X(0), Y(0), dydx);
		double value = 0.5; 
		s.makeAndShow(value);
		showResult(value, interpolation);
	}

	/*
	public static void check() {
		double value = 1.91986;
		double f = 0.9929804 -4.183347*value + 7.262533*value*value - 6.718908*value*value*value
				+3.548499*value*value*value*value - 1.043291*value*value*value*value*value +
				+0.1427030*value*value*value*value*value*value;
		System.out.println(f);
	}
	
	public static void checkTwo() { // i = 2 & i = 3
		double value = 1.57080;
		double f1 = -4.653118964236222 + 9.314031584066798*value 
				-6.332817060074516*value*value 
				+ 1.4754795498162316*value*value*value;
		double f2 = -0.8944429248551136 + 2.2164545899327917*value 
				-1.8659031811588063*value*value 
				+ 0.5385089035298976*value*value*value;
		System.out.println(f1);
		System.out.println(f2); //f1 == f2 
	}
	*/
	public static void showResult(double x, BigDecimal[] interpolation) {
		double f = 0;
		for (int i = 0; i < interpolation.length; i++)
			f += interpolation[i].doubleValue() * Math.pow(x, i);
		System.out.println("f(x0) = " + f);
	}
}
