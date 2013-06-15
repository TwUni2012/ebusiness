package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import database.table.DatabaseTableEntry;

public abstract class TableManager<T extends DatabaseTableEntry> {

	protected Connection connection;
	protected Statement statement;
	protected String table;
	protected String database;

	public String getTable() {
		return table;
	}

	public String getDatabase() {
		return database;
	}

	public TableManager(String database, String table)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/"
				+ database + "?user=testuser&password=testpw");
		statement = connection.createStatement();
		this.database = database;
		this.table = table;
	}

	public ResultSet getResultSetForColumn(String column) throws SQLException {
		return statement.executeQuery("select " + column + " from " + database
				+ "." + table);
	}

	public ResultSet getResultSetWhere(String[] conditions) throws SQLException {
		String query = "select * from " + database + "." + table + " where ";
		for (int i = 0; i < conditions.length; i++) {
			query += conditions[i] + (i < conditions.length - 1 ? " and " : "");
		}
		return statement.executeQuery(query);
	}

	public ResultSet getResultSetWhere(String condition) throws SQLException {
		return statement.executeQuery("select * from " + database + "." + table
				+ " where " + condition);
	}

	public int countWhere(String condition) throws SQLException {
		ResultSet set = statement.executeQuery("select count(*) from "
				+ database + "." + table + " where " + condition);
		set.first();
		return set.getInt(1);
	}

	public ResultSet getCompleteResultSet() throws SQLException {
		return statement
				.executeQuery("select * from " + database + "." + table);
	}

	public void printResult(ResultSet result) throws SQLException {
		ResultSetMetaData meta = result.getMetaData();
		for (int i = 0; i < meta.getColumnCount(); i++) {
			System.out.print(meta.getColumnLabel(i + 1) + "\t");
		}
		System.out.println();
		while (result.next()) {
			for (int i = 0; i < meta.getColumnCount(); i++) {
				System.out.print(result.getString(i + 1) + "\t");
			}
			System.out.println();
		}
	}

}
