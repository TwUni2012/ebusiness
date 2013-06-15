package jsonparser;

public class JsonMovieParser {

	private static boolean inGenre, inDirectors, inActors, inCountry;

	public static JsonMovieObject parseJsonString(String json) {

		if (json.contains("\"code\":404"))
			return null;

		inGenre = false;
		inDirectors = false;
		inActors = false;
		inCountry = false;

		JsonMovieObject object = new JsonMovieObject();

		String[] entries = json.split("[\\{\\}\\[\\],]");

		for (String s : entries) {
			s = s.trim();
			// //System.out.println(s);

			if (s.startsWith("[{") || s.startsWith(", "))
				s = s.substring(2, s.length() - 1);

			if (s.contains(":")) {
				inGenre = false;
				inDirectors = false;
				inActors = false;
				inCountry = false;
			}

			if (s.startsWith("\"genres\":")) {
				inGenre = true;
			} else if (s.startsWith("\"directors\":")) {
				inDirectors = true;
			} else if (s.startsWith("\"actors\":")) {
				inActors = true;
			} else if (s.startsWith("\"country\":")) {
				inCountry = true;
			} else {
				s = s.replaceAll("\"", "");

				if (s.length() > 0) {
					if (inGenre) {
						// System.out.println("Genre: " + s);
						object.addEntry("genre", s);
					} else if (inDirectors) {
						// System.out.println("Director: " + s);
						object.addEntry("director", s);
					} else if (inActors) {
						// System.out.println("Actor: " + s);
						object.addEntry("actor", s);
					} else if (inCountry) {
						// System.out.println("Country: " + s);
						object.addEntry("country", s);
					}

					else {
						if (s.startsWith("year:")) {
							s = s.split(" ")[1];
							// System.out.println("Year: " + s);
							object.addEntry("year", s);
						} else if (s.startsWith("release_date:")) {
							s = s.split(" ")[1];
							// System.out.println("Release: " + s);
							object.addEntry("releasedate", s);
						} else if (s.startsWith("plot_simple:")) {
							s = s.split(": ")[1];
							// System.out.println("Plot: " + s);
							object.addEntry("simple_plot", s);
						} else if (s.startsWith("poster:")) {
							s = s.split(": ")[1];
							// System.out.println("Poster: " + s);
							object.addEntry("posterurl", s);
						} else if (s.startsWith("title:")) {
							s = s.split(": ")[1];
							// System.out.println("Title: " + s);
							object.addEntry("title", s);
						} else if (s.startsWith("rating:")) {
							s = s.split(": ")[1];
							// System.out.println("Rating: " + s);
							object.addEntry("rating", s);
						}
					}
				}
			}

		}

		return object;
	}

}
