package neuron;

import java.util.Vector;

public class Neuron<T> {

	private Vector<InOutput<T>> inputs;
	private InOutput<T> output;
	private double outputWeight;
	private String name;

	public String getName() {
		return name;
	}

	public InOutput<T> getOutput() {
		return output;
	}

	public Neuron(String name) {
		this.name = name;
		inputs = new Vector<InOutput<T>>();
		outputWeight = 1.0 / (double) inputs.size();
	}

	void plugIntoNeuron(Neuron<T> neuron) {
		inputs.add(neuron.getOutput());
	}

}
