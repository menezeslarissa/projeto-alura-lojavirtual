package jdbc;

import java.util.List;

import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

import model.Produto;

public class TesteDAOProduto {
	public static void main(String[] args) throws SQLException {
		Produto produto1 = new Produto("Fogão Eletrolux", "6 bocas");
		Produto produto2 = new Produto("Geladeira Eletrolux", "2 portas");
		Produto produto3 = new Produto("Criado-mudo", "Duas gavetas");
		try (Connection connection = new ConnectionPool().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(connection);
			dao.adiciona(produto1);
			dao.adiciona(produto2);
			dao.adiciona(produto3);
			List<Produto> produtos = dao.lista();
			for(Produto produto : produtos){
				System.out.println("Existe o produto: " + produto);
			}
		}
		System.out.println("Produto inserido com sucesso: " + produto1);
	}


}
