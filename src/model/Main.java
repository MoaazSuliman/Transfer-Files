package model;

import java.sql.SQLException;

import database.DatabaseConnection;

public class Main {

	public static void main(String[] args) {

		try {
			new DatabaseConnection().addColumn();
			System.out.println("Done");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
