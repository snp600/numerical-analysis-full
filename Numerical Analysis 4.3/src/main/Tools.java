package main;

import keys.Keys;
public class Tools {
	
	public void showMaxDiff(double[] numericalSolution, Keys k) {
		final int N = 9;
		final int c = numericalSolution.length / 10;
		int index = 0;
		double max = 0;
		double[] solution = k.getSolution();
		for (int i = 1; i <= N; i++) {
			double difference = Math.abs(solution[i] - numericalSolution[i * c]);
			if (difference > max) {
				index = i;
				max = difference;
			}
		}
		System.out.println("maxDiff = " + max + " (index = " + index + ")");
	}
	
}


	
