package database.table;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class DatabaseTableEntry implements DatabaseTableIF {

	protected ArrayList<String> columnheaders;
	protected HashMap<String, Class> columntypes;
	protected HashMap<String, String> entries;

	public DatabaseTableEntry(String[] headers, Class[] types) throws Exception {

		if (headers.length != types.length)
			throw new Exception("Number of Headers and Datatypes don't fit!");

		this.columnheaders = new ArrayList<String>();
		this.columntypes = new HashMap<String, Class>();
		this.entries = new HashMap<String, String>();

		for (int i = 0; i < headers.length; i++) {
			this.columnheaders.add(headers[i]);
			this.columntypes.put(headers[i], types[i]);
		}

	}

	@Override
	public String toString() {
		String s = getClass().getSimpleName();
		for (String h : columnheaders) {
			s += "\n" + columntypes.get(h).getSimpleName() + "\t" + h + "\t"
					+ entries.get(h);
		}
		return s;
	}

	public ArrayList<String> getColumnHeaders() {
		return columnheaders;
	}

	public String getClassNameForColumn(String header) {
		return columntypes.get(header).getName();
	}

	public int getNumberOfColumns() {
		return columnheaders.size();
	}

	public boolean insertData(String data, String column) {
		if (entries.get(column) == null) {
			entries.put(column, data);
			return true;
		}
		return false;
	}

}
