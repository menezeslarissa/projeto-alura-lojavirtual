package jdbc;

//import java.sql.SQLException;
//import org.hsqldb.Statement;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {
	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionPool().getConnection();
		Statement statement = connection.createStatement();

		@SuppressWarnings("unused")
		boolean resultado = statement.execute("select * from produto");
		ResultSet resultSet = statement.getResultSet();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String descricao = resultSet.getString("descricao");
			System.out.println(id);
			System.out.println(nome);
			System.out.println(descricao);
		}
		resultSet.close();
		statement.close();

		connection.close();

	}
}
