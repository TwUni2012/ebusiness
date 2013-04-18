package neuron;

import java.util.Vector;

import neuron.view.NeuronWindow;

public class NeuroNet {

	public static Vector<Vector<Neuron<Double>>> layers;
	static NeuronWindow window;

	public static void main(String[] args) {
		layers = new Vector<Vector<Neuron<Double>>>();

		Vector<Neuron<Double>> layer1 = new Vector<Neuron<Double>>();
		layer1.add(new Neuron<Double>("A1"));
		layer1.add(new Neuron<Double>("B1"));
		layer1.add(new Neuron<Double>("C1"));
		layer1.add(new Neuron<Double>("D1"));
		layers.add(layer1);

		Vector<Neuron<Double>> layer2 = new Vector<Neuron<Double>>();
		layer2.add(new Neuron<Double>("A2"));
		layer2.add(new Neuron<Double>("B2"));
		layers.add(layer2);

		Vector<Neuron<Double>> layer3 = new Vector<Neuron<Double>>();
		layer3.add(new Neuron<Double>("A3"));
		layer3.add(new Neuron<Double>("B3"));
		layer3.add(new Neuron<Double>("C3"));
		layers.add(layer3);

		window = new NeuronWindow();
	}

}
