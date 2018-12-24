package algorithms;
import optimization.OptimizationAlgorithm;
import optimization.Configuration;
import java.util.ArrayList;
import java.util.Arrays;

public class HillClimbing extends OptimizationAlgorithm {
	
	/* Attributes */
	protected double k;			// Increasing factor
	
	/* Carries out the search */
	@Override
	public void search() {
		
		// Initiates the selection parameters
		initSearch();
		
		// We apply hill climbing generating one random configuration
		applyHillClimbling(problem.genRandomConfiguration());
	
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
	public ArrayList<Configuration> generateNeighborhood(Configuration configuration) {
		
		ArrayList<Configuration> neighbors;
		
		int x1 = 0; int x2= 0;
		double min = 0;
		double max = problem.size();
		double step;
		int[] params;
		
		neighbors = new ArrayList<Configuration>(); 
		
		for (int i = 0; i < problem.size(); i++) {
			
			
			// We define the step
			step = k * (max - min);
			
			// Copy of the original configuration values
			params = Arrays.copyOf(configuration.getValues(), problem.size());
			
			x1 = (int) ((int) (configuration.getValues().length) * Math.random());
			x2 = (int) ((int) (configuration.getValues().length) * Math.random());
			
			// We establish the neighbors values
			params[i] = (int) Math.min(configuration.getValues()[i] + x1 * step, max);
			neighbors.add(new Configuration(params));
			params[i] = (int) Math.max(configuration.getValues()[i] - x2 * step, min);
			neighbors.add(new Configuration(params));
			
		}
		
		return neighbors;

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
