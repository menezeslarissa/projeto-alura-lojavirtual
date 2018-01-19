package jdbc;

import java.util.ArrayList;
//import java.awt.List;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Categoria;
import model.Produto;

public class ProdutoDAO { // Data Access Object, uma instância dessa classe é um
							// objeto responsável pelo acesso aos dados

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Produto produto) throws SQLException {
		String sql = "insert into produto (nome, descricao) values (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.execute();

			try (ResultSet result = statement.getGeneratedKeys()) { // chama o
																	// método
																	// generatedkeys
				if (result.next()) { // itera para o primeiro elemento
					int id = result.getInt("id"); // extrai o id
					produto.setId(id); // seta o id em produto
				}
			}
		}
	}

	public List<Produto> lista() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from produto";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			retornaProdutosDoResSet(produtos, statement);
		}
		return produtos;
	}

	public List<Produto> busca(Categoria categoria) throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from produto where categoria_id =  ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, categoria.getId());
			statement.execute();
			retornaProdutosDoResSet(produtos, statement);

		}
		return produtos;
	}

	private void retornaProdutosDoResSet(List<Produto> produtos, PreparedStatement statement) throws SQLException {
		try(ResultSet result = statement.getResultSet()){
			while(result.next()){
				int id = result.getInt("id");
				String nome = result.getString("nome");
				String descricao = result.getString("descricao");
				Produto produto = new Produto(nome, descricao);
				produto.setId(id);
				produtos.add(produto);
			}
		}
	}
}