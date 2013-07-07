package html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;

import database.MovieManager;

public class HtmlExport {

	private static MovieManager manager;
	private static ResultSet set;

	public static void export(String filename) throws Exception {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					filename)));

			manager = new MovieManager("imdblist", "movies");
			set = manager.getCompleteResultSet();

			writer.write("<html><body><table width=80%>\n");
			while (set.next()) {
				writer.write("<tr><td align=center valign=middle><img height=150 src=\""
						+ set.getString("posterurl")
						+ "\"</td><td><h1>"
						+ set.getString("title")
						+ "</h1>"
						+ "<p>"
						+ set.getString("country")
						+ " "
						+ set.getString("year")
						+ ", Released: "
						+ set.getString("releasedate")
						+ "</p>"
						+ "<p><b>Director:</b> "
						+ set.getString("director")
						+ "<br><b>Genres:</b> ");
				for (int i = 1; i <= 5; i++) {
					String g = set.getString("genre" + i);
					if (g.length() > 0) {
						writer.write((i > 1 ? ", " : "") + g);
					}
				}
				writer.write("<br><b>Actors:</b> ");
				for (int i = 1; i <= 5; i++) {
					String g = set.getString("actor" + i);
					if (g.length() > 0) {
						writer.write((i > 1 ? ", " : "") + g);
					}
				}
				writer.write("</td></tr>\n");
			}
			writer.write("</table></body></html>");

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
