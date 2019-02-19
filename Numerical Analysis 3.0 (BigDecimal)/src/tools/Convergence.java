package tools;

import java.math.BigDecimal;
import java.math.MathContext;

public class Convergence {
	
	private int n; // amount of grids
	private int currentIndex = 0;
	private BigDecimal[][] grid; //stair-step array
	private BigDecimal[] difference;
	
	private BigDecimal[] dif; //difference between Y(k)[N0] and Y(k-1)[N0/2]
	
	public Convergence(int n) {
		this.n = n;
		this.grid = new BigDecimal[n][];
		this.difference = new BigDecimal[n-1];
		
		this.dif = new BigDecimal[n-1];
	}
	
	public void addElement(BigDecimal y[]) {
		if (currentIndex >= n)
			throw new IllegalArgumentException();
		grid[currentIndex] = new BigDecimal[y.length];
		grid[currentIndex++] = y;
	}
	
	public void composeDifferences() {
		for (int i = 0; i < n - 1; i++) {
			difference[i] = findMaximumDifference(grid[i], grid[i+1]);
			System.out.println(difference[i]);
		}
	}
	
	/* y1[i] > y2[i] for any i */
	/* y1[dimension]; y2[dimension*2] */	
	public BigDecimal findMaximumDifference(BigDecimal[] y1, BigDecimal[] y2) {
		BigDecimal max = new BigDecimal(0.0);
		for (int i = 1; i < y1.length; i++) 
			if ((y1[i].subtract(y2[i/2]).compareTo(max)) == 1)
				max = y1[i].subtract(y2[i/2]);
		return max;
	}
	
	public void composeDiffs() {
		for (int i = 0; i < n-1; i++) {
			int index = grid[i].length - 1;
			dif[i] = grid[i][index].subtract(grid[i+1][index * 2]);
			System.out.println(dif[i]);
		}
	}
	
	public void showDivisionOfDiffs() {
		MathContext eps = new MathContext(30);
		System.out.println();
		for (int i = 0; i < n - 2; i++) 
			System.out.println(dif[i].divide(dif[i+1], eps));
	}
}
