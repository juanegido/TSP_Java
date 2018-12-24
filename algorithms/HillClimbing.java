package algorithms;
import optimization.OptimizationAlgorithm;
import optimization.Configuration;
import java.util.ArrayList;
import java.util.Arrays;

public class HillClimbing extends OptimizationAlgorithm {
	
	/* Attributes */
	protected double k;			// Increasing factor
	
	/* Carries out the search */
	public static final int ITERATIONS_BEFORE_MAXIMA = 100;
	@Override
	public void search() {
		
		// Initiates the selection parameters
		initSearch();
		
		// We apply hill climbing generating one random configuration
		applyHillClimbling(genRandomConfiguration());
	
		// Stop the search
		stopSearch();
	}
	
	/* Apply HillClimbing algorithm */
	public Configuration applyHillClimbling(Configuration initialSolution) {
		
		// Local variables
		boolean improves; 				// Flag to control improvement
		Configuration currentSolution;	// Best current configuration taken, as input, initialSolution
		
		currentSolution = initialSolution.clone();
		evaluate(currentSolution);
		improves = true;
		
		while(improves) {
			
			improves = false;
			
			// Generates neighbor of best solution
			for(Configuration neighbor : generateNeighborhood(currentSolution)) {
				
				double score = evaluate(neighbor);
				
				// After evaluating, if best solution and score are equal is because we have improved it
				if(score < currentSolution.score()) {
					
					currentSolution = neighbor.clone();
					improves = true;
				}
			}
		}
		
		return currentSolution;
	}
	
	/* Generates the neighborhood of the configuration given by parameter
	 * Generates a neighbor per each parameter decreasing and increasing */
	public Configuration generateNeighborhood(Configuration configuration) {
		
		int x1 = 0; int x2= 0;
		int[] solution = configuration.getValues();
		while (x1 == x2) {
			x1 = (int) ((int) (configuration.getValues().length) * Math.random());
			x2 = (int) ((int) (configuration.getValues().length) * Math.random());
		}
		int City1 = solution[x1];
		int City2 = solution[x2];
		
		solution[x2] = solution[City1];
		solution[x1] = solution[City2];
		
		
		
		
		
		
		return new Configuration(solution);
		
	

	}

	@Override
	public void showSearchStats() {

		// For this algorithm, it does not show any additional information.
	}

	@Override
	public void setParams(String[] args) {
		
		try {
			
			k = Double.parseDouble(args[0]);
			System.out.println("Using specified configuration: k = " + k);
		} catch(Exception ex) {
			
			k = 0.1;
			System.out.println("Using default configuration: k = " + k);
		}
	}

	@Override
	public void showAlgorithmStats() {
		// TODO Auto-generated method stub
		
	}
}
