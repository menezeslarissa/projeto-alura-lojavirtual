package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* a cada novo statement do tipo insert, comando � comitado automaticamente para o servidor, n�o d� pra voltar atr�s
 * para executar dois comando com sucesso ou nenhum, precisa-se desativar o auto commit
 * 
 * */
 

public class TesteInsercao {
	public static void main(String[] args) throws SQLException{
		try(Connection connection = new ConnectionPool().getConnection()){
			connection.setAutoCommit(false);
			String sql = "insert into produto (nome, descricao) values (?, ?)"; //passa '?' nos lugares que se deseja preencher
			//escapar os dados do usu�rio final
			try{
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS ); //formata um statement
				adiciona("TV", "32 Polegadas", statement);
				adiciona("Smartphone Motorola", "G5 Plus", statement);
				adiciona("Smartphone Samsung", "7", statement);
				connection.commit();
			} catch(Exception ex){
				ex.printStackTrace();
				connection.rollback(); //dentro do catch, desejamos voltar atr�s
				System.out.println("Rollback efetuado"); //quando h� o rollback nada � inserido
			}
			


		}
	
		//
		
		//statement.close(); // O statement � fechado automaticamente com o try
		//connection.close(); //connection � fechado automaticamente
		
	}

	private static ResultSet adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
		/*if(nome.equals("Smartphone Samsung")){
			throw new IllegalArgumentException("Problema");
		}*/
		
		boolean resultado = statement.execute();
		// executa a query
		System.out.println("O resultado foi: " + resultado);
		// se o statement devolver uma lista de resultados, entao o resultado � true, caso simplesmente altere uma informa��o no banco, o resultado � false
		
		//entrega todas as chaves que foram geradas automaticamente
		ResultSet resultSet = statement.getGeneratedKeys(); 
		
		while(resultSet.next()){
			String id = resultSet.getString("id");
			System.out.println(id + " gerado");
		}
		resultSet.close();
		return resultSet;
	}
}
