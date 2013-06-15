package database;

import java.sql.SQLException;

import jsonparser.JsonObject;
import database.table.MovieTableEntry;

public class MovieManager extends TableManager<MovieTableEntry> {

	public MovieManager(String database, String table)
			throws ClassNotFoundException, SQLException {
		super(database, table);
	}

	public void insertIntoDatabase(JsonObject object) throws Exception {
		if (object == null)
			return;

		MovieTableEntry entry = new MovieTableEntry();
		String query = "insert into " + database + "." + table
				+ " values (default";// , 'lars',
		for (String c : entry.getColumnHeaders()) {
			query += ", '" + object.getEntryForKey(c) + "'";
		}
		query += ")";
		// System.out.println(query);
		statement.executeUpdate(query);
	}
}
