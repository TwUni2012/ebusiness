package neuron.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import neuron.NeuroNet;

public class NeuronWindow extends JFrame {

	private NeuronPanel panel;

	public NeuronWindow() {
		setLayout(new BorderLayout());
		setSize(1024, 768);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(NeuroNet.layers.size()
				+ "-schichtiges neuronales Netz (Perceptron)");

		add(panel = new NeuronPanel(), BorderLayout.CENTER);
	}

}
