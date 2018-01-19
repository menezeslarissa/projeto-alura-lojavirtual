package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class TesteRemocao {
	public static void main(String[] args) throws SQLException{
		Connection connection = new ConnectionPool().getConnection();
		Statement statement = connection.createStatement();
		
		statement.execute("delete from produto where id>3"); //m�todo execute: : se o retorno � um ResultSet por conta de um select, ele retorna true.
		//Caso contr�rio ou caso n�o haja nenhum valor retornado, o m�todo devolve false.
		
		int count = statement.getUpdateCount(); //devolve o n�mero de linhas atualizadas
		System.out.println(count + " linhas atualizadas");
		
		statement.close();
		connection.close();
	}
}
