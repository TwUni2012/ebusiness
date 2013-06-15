package test;

import static org.junit.Assert.fail;
import html.HtmlExport;

import org.junit.Test;

public class TestHtml {

	@Test
	public void test() {
		try {
			HtmlExport.export("data/list.html");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
