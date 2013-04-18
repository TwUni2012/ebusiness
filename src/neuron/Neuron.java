package neuron;

import java.awt.Color;
import java.util.Vector;

public class Neuron {

	private Vector<Neuron> inputs;
	protected double output;
	private double outputWeight;
	private String name;
	private int x, y;
	private Color color;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public double getOutput() {
		return output;
	}

	public double getOutputWeight() {
		return outputWeight;
	}

	public Color getColor() {
		return color;
	}

	public Neuron(String name, Color col) {
		this.name = name;
		this.color = col;
		inputs = new Vector<Neuron>();
	}

	public Neuron(String name) {
		this.name = name;
		this.color = Color.BLACK;
		inputs = new Vector<Neuron>();
	}

	void addInputNeuron(Neuron neuron) {
		inputs.add(neuron);
		outputWeight = 1.0 / (double) inputs.size();
	}

	public Vector<Neuron> getInputs() {
		return inputs;
	}

	@Override
	public String toString() {
		String s = "";
		for (Neuron n : inputs) {
			s += n.getName() + ", ";
		}
		return getName() + " | " + s;
	}

}
