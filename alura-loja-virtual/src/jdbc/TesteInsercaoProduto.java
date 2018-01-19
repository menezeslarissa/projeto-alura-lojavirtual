package jdbc;

import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

import model.Produto;

public class TesteInsercaoProduto {
	public static void main(String[] args) throws SQLException {
		Produto produto1 = new Produto("produto qualquer", "descrição qualquer");

		try (Connection connection = new ConnectionPool().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(connection);
			dao.adiciona(produto1);
		}
		System.out.println("Produto inserido com sucesso: " + produto1);
	}


}
