package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

	protected Connection connection;
	protected PreparedStatement s;
	protected String sql;
	protected ResultSet result;
//	private String host = "jdbc:mysql://localhost:3306/art";
	private String host = "jdbc:sqlite:file.db";
	private String user = "root";
	private String password = "";

	protected Connection createConnection() {
		try {
			connection = DriverManager.getConnection(host, user, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	public void addColumn() throws SQLException {
		sql = "ALTER TABLE FILE ADD COLUMN number long";
		connection = createConnection();
		s = connection.prepareStatement(sql);
		s.execute();
	}

//	protected void closeConnectionWithResult(Connection connection, PreparedStatement s, ResultSet res)
//			throws SQLException {
//		connection.close();
//		s.close();
//		res.close();
//	}
//
//	protected void closeConnection(Connection connection, PreparedStatement s) throws SQLException {
//		connection.close();
//		s.close();
//	}
}
