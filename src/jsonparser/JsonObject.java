package jsonparser;

import java.util.ArrayList;
import java.util.HashMap;

import movies.ImdbApiReader;

public abstract class JsonObject {

	protected HashMap<String, ArrayList<String>> entries;

	public JsonObject() {
		entries = new HashMap<String, ArrayList<String>>();
	}

	public boolean addEntry(String key, String entry) {
		entry = entry.replaceAll("'", "");
		if (entries.get(key) == null) {
			ArrayList<String> newlist = new ArrayList<String>();
			newlist.add(entry);
			entries.put(key, newlist);
			return true;
		}
		if (entries.get(key).size() < ImdbApiReader.maxEntries) {
			entries.get(key).add(entry);
			return true;
		}
		return false;
	}

	public ArrayList<String> getEntriesForKey(String key) {
		ArrayList<String> array;
		if ((array = entries.get(key)) != null) {
			return array;
		}
		return new ArrayList<String>();
	}

	public Object[] getKeys() {
		return entries.keySet().toArray();
	}

	@Override
	public String toString() {
		String s = "";

		for (Object k : getKeys()) {
			if (k instanceof String) {
				s += k + "\n";
				for (Object v : getEntriesForKey((String) k)) {
					if (v instanceof String)
						s += "\t" + v + "\n";
				}
			}
		}

		return s;
	}

	public String getEntryForKey(String key) {

		if (key.matches("[a-zA-Z]+[0-9]")) {
			try {
				return entries.get(key.substring(0, key.length() - 1)).get(
						Integer.parseInt(key.substring(key.length() - 1,
								key.length())) - 1);
			} catch (Exception e) {
				return "";
			}
		}
		try {
			return entries.get(key).get(0);
		} catch (Exception e) {
		}
		return "";
	}
}
