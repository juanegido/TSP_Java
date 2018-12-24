package problems.tsp.maze;

import optimization.Configuration;
import problems.tsp.TSP;
import utils.Position;
import visualization.*;

/**
 * Extends the TSP to represent it in a maze where movements are either
 * horizontal or vertical, and uses manhattan as distance.
 */
public class MazeTSP extends TSP implements ProblemVisualizable {

	/** Constructors */
	public MazeTSP() {
		generateInstance(20, 10, 0);
	}

	public MazeTSP(int rangeXY, int numCities) {
		generateInstance(rangeXY, numCities, 0);
	}

	public MazeTSP(int rangeXY, int numCities, int seed) {
		generateInstance(rangeXY, numCities, seed);
	}

	/** Returns a view of the problem. */
	@Override
	public ProblemView getView() {
		MazeTSPView mazeView = new MazeTSPView(this, 600);
		return mazeView;
	}

	/**
	 * Calculates the score of a configuration as the sum of the path.
	 */
	@Override
	
	public double score(Configuration configuration) {
		int[] solution = configuration.getValues();
	    int distance = 0;
	    for (int index = 0; index < solution.length; index++) {
	        int starting = solution[index];
	        int destination = 0;
			int pos_x = posCities.get(starting).x;
			int pos_y = posCities.get(starting).y;
			int pos_x2 = posCities.get(destination).x;
			int pos_y2 = posCities.get(destination).y;
	        if (index + 1 < solution.length) {
	            destination = solution[index + 1];
	        } else {
	            destination = solution[0];
	            distance += Math.abs(this.posAgent.x - pos_x2) + Math.abs(this.posAgent.y - pos_y2);
	        }
	            distance += Math.abs(pos_x - pos_x2) + Math.abs(pos_y - pos_y2);
	    }
	    return distance;
	}

	/** Calculates the distance between two points. */
	private double dist(Position from, Position to) {
		return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
	}
}
