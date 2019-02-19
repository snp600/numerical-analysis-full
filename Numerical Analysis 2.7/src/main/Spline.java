package main;

public class Spline {
	
	private double[] x;
	private double[] f;
	private double[] dfdx;
	
	public Spline(double[] x, double[] f, double[] dfdx) {
		this.x = new double[x.length];
		this.f = new double[f.length];
		this.dfdx = new double[dfdx.length];
		for (int i = 0; i < x.length; i++) {
			this.x[i] = x[i];
			this.f[i] = f[i];
			this.dfdx[i] = dfdx[i];
		}
	}
	
	public void makeAndShow(double value) {
		for (int i = 0; i < x.length - 1; i++) {
			System.out.print("i = " + (i + 1) + " | ");
			double[] spline = makeCubicalNode(x[i], x[i+1], f[i], f[i+1], dfdx[i], dfdx[i+1]);
			System.out.printf("A0 = %.8f | ", spline[0]);
			System.out.printf("A1 = %.8f | ", spline[1]);
			System.out.printf("A2 = %.8f | ", spline[2]);
			System.out.printf("A3 = %.8f |", spline[3]);
			System.out.println();
			calculateValue(spline, value);
			System.out.println();
		}
	}
	
	public void calculateValue(double[] spline, double value) {
		double f = spline[0] + spline[1] * value + spline[2] * value * value + spline[3] * value * value * value;
		System.out.println("value = " + f);
	}
	public double[] makeCubicalNode(double x1, double x2, 
								 	double f1, double f2,
									double dx1, double dx2) {
		double[] spline = new double[4]; //cubical polynomial
		/* a0 */
		spline[0] = (-1.0 * dx2 * x1 * x1 * x2 * (x2 - x1) 
				     + f2 * x1 * x1 * (3.0 * x2 - x1) 
				     + f1 * x2 * x2 * (x2 - 3.0 * x1) 
				     - dx1 * x1 * x2 * x2 * (x2 - x1) ) 
				/ Math.pow(x2 - x1, 3);
		
		/* a1 */
		spline[1] = (  dx2 * x1 * (2.0 * x2 + x1) * (x2 - x1)
				     - 6.0 * (f2 - f1) * x1 * x2 
				     + dx1 * x2 * (x2 + 2.0 * x1) * (x2 - x1) ) 
				/ Math.pow(x2 - x1, 3);  
		
		/* a2 */
		spline[2] = (-dx2 * (x2 - x1) * (x2 + 2.0 * x1) 
					+ 3.0 * (f2 - f1) * (x2 + x1)
					- dx1 * (x2 - x1) * (x1 + 2.0 * x2) )
				/ Math.pow(x2 - x1, 3);
					
		/* a3 */
		spline[3] = ( dx2 * (x2 - x1) - 2.0 * (f2 - f1) + dx1 * (x2 - x1) )
				/ Math.pow(x2 - x1, 3);
		return spline;
	}
	
	
}
