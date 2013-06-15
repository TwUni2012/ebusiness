package test;

import static org.junit.Assert.assertEquals;
import markov.MarkovNetz;
import markov.categories.ALTER;
import markov.categories.GESCHLECHT;

import org.junit.Test;

public class TestCategory {

	MarkovNetz catAlter;
	MarkovNetz catGeschlecht;

	@Test
	public void testInitialize() {
		catAlter = new MarkovNetz(ALTER.values());
		assertEquals(3, catAlter.getNumberOfDiscretes());

		catGeschlecht = new MarkovNetz(GESCHLECHT.values());
		assertEquals(2, catGeschlecht.getNumberOfDiscretes());

		assertEquals(1 / (double) catAlter.getNumberOfDiscretes(),
				catAlter.getProbabilityOfDiscrete(ALTER.KIND), 0.001);

		assertEquals(1 / (double) catGeschlecht.getNumberOfDiscretes(),
				catGeschlecht.getProbabilityOfDiscrete(GESCHLECHT.MAENNLICH),
				0.001);

	}

}
