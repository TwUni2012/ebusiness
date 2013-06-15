package database.table;

import java.util.ArrayList;

public interface DatabaseTableIF {
	public ArrayList<String> getColumnHeaders();

	public String getClassNameForColumn(String header);

	public int getNumberOfColumns();

}
