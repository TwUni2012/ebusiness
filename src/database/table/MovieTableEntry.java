package database.table;


public class MovieTableEntry extends DatabaseTableEntry {

	private final static String[] columns = { "title", "year", "releasedate",
			"posterurl", "director", "country", "genre1", "genre2", "genre3",
			"genre4", "genre5", "actor1", "actor2", "actor3", "actor4",
			"actor5" };
	private final static Class[] types = { String.class, Integer.class,
			Integer.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class,
			String.class, String.class };

	public MovieTableEntry() throws Exception {
		super(columns, types);
	}

}
