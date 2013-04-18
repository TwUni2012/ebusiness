package neuron.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JPanel;

import neuron.NeuroNet;
import neuron.Neuron;

public class NeuronPanel extends JPanel {

	private int radius = 30, labelpadding = 2;
	private int hordist, vertdist, x, y, xLabel, yLabel, wLabel, hLabel;
	private Dimension size;
	private FontMetrics metrics;
	private Font labelFont, numberFont;
	private Rectangle2D rect;

	public NeuronPanel() {
		labelFont = Font.decode("arial-24");
		numberFont = Font.decode("courier-12");
	}

	@Override
	public void paint(Graphics g) {
		size = getSize();
		Graphics2D gAntiAlias = (Graphics2D) g;
		gAntiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		hordist = size.width / NeuroNet.layers.size();
		x = hordist / 2;

		updateFont(gAntiAlias, numberFont);
		for (Vector<Neuron> v : NeuroNet.layers) {

			vertdist = size.height / v.size();
			y = vertdist / 2;

			for (Neuron n : v) {

				n.setX(x);
				n.setY(y);

				y += vertdist;

				drawConnectionsFrom(n, gAntiAlias);
			}
			System.out.println();
			x += hordist;
		}

		updateFont(gAntiAlias, labelFont);
		for (Vector<Neuron> v : NeuroNet.layers) {
			for (Neuron n : v) {

				rect = metrics.getStringBounds(n.getName(), gAntiAlias);

				wLabel = (int) Math.max(2 * radius, rect.getWidth() + 4
						* labelpadding);
				hLabel = (int) Math.max(2 * radius, rect.getHeight() + 2
						* labelpadding);
				xLabel = n.getX() - wLabel / 2;
				yLabel = n.getY() - hLabel / 2;

				gAntiAlias.setColor(Color.WHITE);
				gAntiAlias.fillOval(xLabel, yLabel, wLabel, hLabel);

				gAntiAlias.setColor(n.getColor());
				gAntiAlias.drawOval(xLabel, yLabel, wLabel, hLabel);

				gAntiAlias.setColor(Color.BLACK);
				gAntiAlias.drawString(n.getName(),
						(int) (n.getX() - (rect.getWidth() / 2)) + 2
								* labelpadding,
						yLabel + (hLabel + metrics.getHeight()) / 2 - 2
								* labelpadding);
			}
		}
	}

	private void updateFont(Graphics g, Font font) {
		g.setFont(font);
		metrics = g.getFontMetrics();
	}

	private void drawConnectionsFrom(Neuron n, Graphics g) {
		for (Neuron n2 : n.getInputs()) {
			g.setColor(n2.getColor());
			g.drawLine(n.getX(), n.getY(), n2.getX(), n2.getY());
			drawValueLabel(n, n2, g);
		}
	}

	private void drawValueLabel(Neuron n, Neuron n2, Graphics g) {
		String label = n.getOutput() + "";
		label = label.substring(0,
				Math.min(label.indexOf(".") + 3, label.length()));
		int w = metrics.stringWidth(label);
		int h = metrics.getAscent();
		int x = n.getX() + ((n2.getX() - n.getX()) / 2) - w / 2;
		int y = n.getY() + ((n2.getY() - n.getY()) / 2) - h / 2;

		g.setColor(Color.WHITE);
		g.fillRect(x - labelpadding, y - labelpadding, w + 2 * labelpadding, h
				+ 2 * labelpadding);

		g.setColor(n2.getColor());
		g.drawRect(x - labelpadding, y - labelpadding, w + 2 * labelpadding, h
				+ 2 * labelpadding);

		g.setColor(Color.BLACK);
		g.drawString(label, x, y + 2 * labelpadding + h / 2);
	}
}
