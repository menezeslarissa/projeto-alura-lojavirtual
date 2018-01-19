package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Database {
	static Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/alura-loja-virtual", "SA", "");
		return connection;
	}
}
