package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* a cada novo statement do tipo insert, comando é comitado automaticamente para o servidor, não dá pra voltar atrás
 * para executar dois comando com sucesso ou nenhum, precisa-se desativar o auto commit
 * 
 * */
 

public class TesteInsercao {
	public static void main(String[] args) throws SQLException{
		try(Connection connection = new ConnectionPool().getConnection()){
			connection.setAutoCommit(false);
			String sql = "insert into produto (nome, descricao) values (?, ?)"; //passa '?' nos lugares que se deseja preencher
			//escapar os dados do usuário final
			try{
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS ); //formata um statement
				adiciona("TV", "32 Polegadas", statement);
				adiciona("Smartphone Motorola", "G5 Plus", statement);
				adiciona("Smartphone Samsung", "7", statement);
				connection.commit();
			} catch(Exception ex){
				ex.printStackTrace();
				connection.rollback(); //dentro do catch, desejamos voltar atrás
				System.out.println("Rollback efetuado"); //quando há o rollback nada é inserido
			}
			


		}
	
		//
		
		//statement.close(); // O statement é fechado automaticamente com o try
		//connection.close(); //connection é fechado automaticamente
		
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
		// se o statement devolver uma lista de resultados, entao o resultado é true, caso simplesmente altere uma informação no banco, o resultado é false
		
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
