package markov;

import java.util.HashMap;

import markov.categories.Category;

public class MarkovNetz {

	private HashMap<Category, Integer> indices;
	private Double[][] probabilities;

	public MarkovNetz(Category[] discretes) {
		indices = new HashMap<Category, Integer>();
		if (discretes.length > 0) {
			int index = 0;
			for (Category d : discretes) {
				indices.put(d, index++);
			}

			probabilities = new Double[index + 1][index + 1];
			for (int i = 0; i <= index; i++) {
				probabilities[i][i] = 1 / (double) index;
			}
		}
	}

	public int getNumberOfDiscretes() {
		return indices.size();
	}

	public double getProbabilityOfDiscrete(Category discrete) {
		int i = indices.get(discrete);
		return probabilities[i][i];
	}

	public double getProbabilityFromDiscreteToDiscrete(Category from,
			Category to) {
		return probabilities[indices.get(from)][indices.get(to)];
	}

}
