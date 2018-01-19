package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class TesteRemocao {
	public static void main(String[] args) throws SQLException{
		Connection connection = new ConnectionPool().getConnection();
		Statement statement = connection.createStatement();
		
		statement.execute("delete from produto where id>3"); //método execute: : se o retorno é um ResultSet por conta de um select, ele retorna true.
		//Caso contrário ou caso não haja nenhum valor retornado, o método devolve false.
		
		int count = statement.getUpdateCount(); //devolve o número de linhas atualizadas
		System.out.println(count + " linhas atualizadas");
		
		statement.close();
		connection.close();
	}
}
