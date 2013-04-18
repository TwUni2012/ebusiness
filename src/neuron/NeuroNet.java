package neuron;

import java.awt.Color;
import java.util.Vector;

import neuron.view.NeuronWindow;

public class NeuroNet {

	public static Vector<Vector<Neuron>> layers;
	static NeuronWindow window;

	public static void main(String[] args) {
		layers = new Vector<Vector<Neuron>>();

		Vector<Neuron> layer1 = new Vector<Neuron>();
		Neuron a1, b1, c1, d1, a2, b2, a3, b3, c3;
		layer1.add(a1 = new Neuron("A1", Color.RED));
		layer1.add(b1 = new Neuron("B1", Color.BLUE));
		layer1.add(c1 = new Neuron("C1", Color.GRAY));
		layer1.add(d1 = new Neuron("D1", Color.GREEN));
		layers.add(layer1);

		Vector<Neuron> layer2 = new Vector<Neuron>();
		layer2.add(a2 = new Neuron("A2", Color.ORANGE));
		a2.addInputNeuron(a1);
		a2.addInputNeuron(b1);
		a2.addInputNeuron(c1);

		layer2.add(b2 = new Neuron("B2", Color.MAGENTA));
		b2.addInputNeuron(b1);
		b2.addInputNeuron(d1);
		layers.add(layer2);

		Vector<Neuron> layer3 = new Vector<Neuron>();
		layer3.add(a3 = new Neuron("A3"));
		a3.addInputNeuron(a2);
		a3.addInputNeuron(b2);

		layer3.add(b3 = new Neuron("B3"));
		b3.addInputNeuron(a2);
		b3.addInputNeuron(b2);

		layer3.add(c3 = new Neuron("C3"));
		c3.addInputNeuron(a2);
		c3.addInputNeuron(b2);
		layers.add(layer3);

		window = new NeuronWindow();
	}

}
