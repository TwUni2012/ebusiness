package movies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jsonparser.JsonMovieObject;
import jsonparser.JsonMovieParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import database.MovieManager;

public class ImdbApiReader {

	/*
	 * http://mymovieapi.com/?title=127+hours&type=json
	 */
	private static String url_part1 = "http://imdbapi.org/?title=";
	private static String url_part3 = "&type=json&plot=simple";
	public final static int maxEntries = 5;
	private MovieManager manager;

	public ImdbApiReader(MovieManager movieManager) {
		this.manager = movieManager;
	}

	public void readMovies(String listfile) throws Exception {
		int countMovies = 0;
		String line = "", url = "";
		BufferedReader apireader = null;
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				listfile)));
		while ((line = reader.readLine()) != null) {
			line = line.toLowerCase();
			line = line.trim();
			line = line.replaceAll(" ", "+");
			line = line.replaceAll("&", "%26");
			line = line.replaceAll("ä", "%C3%A4");
			line = line.replaceAll("ö", "%C3%B6");
			line = line.replaceAll("ü", "%C3%BC");
			line = line.replaceAll("Ä", "%C3%84");
			line = line.replaceAll("Ö", "%C3%96");
			line = line.replaceAll("Ü", "%C3%9C");
			line = line.replaceAll("ß", "%C3%9F");
			line = line.replaceAll("\\?", "%3F");

			System.out.println("Request for '" + line + "'");
			url = url_part1 + line + url_part3;

			try {
				HttpClient client = new DefaultHttpClient();
				HttpResponse response = client.execute(new HttpGet(url));
				apireader = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			JsonMovieObject object = JsonMovieParser.parseJsonString(apireader
					.readLine());

			try {
				if (manager.countWhere("title = '"
						+ object.getEntryForKey("title") + "'") < 1) {
					manager.insertIntoDatabase(object);
					System.out.println(" => inserted");
				} else
					System.out.println(" => already in table");

			} catch (Exception e) {
				System.out.println(" => error: " + e.getMessage());
			}
		}
		reader.close();
	}

}
