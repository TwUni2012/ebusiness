package neuron.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import neuron.NeuroNet;
import neuron.Neuron;

public class NeuronPanel extends JPanel {

	private int radius = 30;
	private int hordist, vertdist, max,x,y;
	private Dimension size;

	@Override
	public void paint(Graphics g) {
		size = getSize();
		hordist = size.width / (NeuroNet.layers.size() + 1);
		max = 0;
		for (Vector<Neuron<Double>> v : NeuroNet.layers) {
			if (v.size() > max) {
				max = v.size();
			}
		}
		vertdist = size.height / (max + 1);
		
		x=hordist/2;
		for (Vector<Neuron<Double>> v : NeuroNet.layers) {
			y=vertdist/2;
			for(Neuron<Double> n: v){
				g.setColor(Color.WHITE);
				g.fillOval(x-radius, y-radius, 2*radius, 2*radius);

				g.setColor(Color.BLACK);
				g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
				g.drawString(n.getName(), x, y);
				
				y+=vertdist;
			}
			x+=hordist;
		}
	}

}
