package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Categoria;
import model.Produto;

public class TesteCategoria {
	public static void main(String[] args) throws SQLException {
		try(Connection connection = new ConnectionPool().getConnection()){
			List<Categoria> categorias = new CategoriaDAO(connection).listaComProdutos(); //passa a conexão como argumento e chama o método que retorna uma lista de categorias
			for(Categoria categoria : categorias){
				System.out.println(categoria.getNome());
				
				for(Produto produto : categoria.getProdutos()){
					System.out.println(categoria.getNome() + " - " + produto.getNome());
				}
			}
		}
	}
}
