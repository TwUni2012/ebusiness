package test;

import static org.junit.Assert.fail;
import movies.ImdbApiReader;

import org.junit.Test;

import database.MovieManager;

public class TestImdbApi {

	private ImdbApiReader imdbReader;
	private MovieManager manager;

	@Test
	public void test() {
		try {
			manager = new MovieManager("imdblist", "movies");
			imdbReader = new ImdbApiReader(manager);
			imdbReader.readMovies("data/movietitles.txt");

			System.out.println("Database: " + manager.getDatabase()
					+ "\nTable: " + manager.getTable() + "\n");
			manager.printResult(manager.getCompleteResultSet());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
