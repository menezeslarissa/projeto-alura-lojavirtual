package jdbc;

//import java.sql.DriverManager; 
import java.sql.SQLException;
import org.hsqldb.jdbc.JDBCPool;
import java.sql.Connection;
//import javax.sql.DataSource;


public class ConnectionPool {
	
	private JDBCPool DataSource;
	
	ConnectionPool(){
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/alura-loja-virtual");
		pool.setUser("SA");
		pool.setPassword("");
		this.DataSource = pool;
		
	}
	
	Connection getConnection() throws SQLException{
		//Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/alura-loja-virtual", "SA", "");
		Connection connection = DataSource.getConnection();
		return connection;
	}
}
