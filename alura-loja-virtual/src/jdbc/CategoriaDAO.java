package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Produto;

public class CategoriaDAO {

	private final Connection connection;
	
	public CategoriaDAO(Connection connection){
		this.connection = connection;
	}
	
	public List<Categoria> lista() throws SQLException{
		List<Categoria> categorias = new ArrayList<>();
		//query que seleciona todas as categorias
		String sql = "select * from categoria";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();
			try(ResultSet result = statement.getResultSet()){ //para cada um dos resultados do resultset devemos instanciar uma nova categoria e colocá-la na lista
				while(result.next()){
					int id = result.getInt("id");
					String nome = result.getString("nome");
					Categoria categoria = new Categoria (id, nome);
					categorias.add(categoria);
				}
			}
		}
		return categorias;
	}
	
	public List<Categoria> listaComProdutos() throws SQLException{
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultimo = null;
		
		String sql = "select c.id  as c_id, c.nome as c_nome,  p.id as p_id, p.nome as p_nome, p.descricao as p_descricao "
				+ "from categoria as c join "
				+ "produto as p on p.categoria_id = c.id";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();
			try(ResultSet result = statement.getResultSet()){
				while(result.next()){
					int id = result.getInt("c_id"); //carrega o id e o nome da categoria
					String nome = result.getString("c_nome");
					//verifica se a categoria mudou
					if(ultimo == null || !ultimo.getNome().equals(nome)){ //adiciona a categoria caso ultima seja null ou caso o nome da categoria seja diferente
						Categoria categoria = new Categoria(id, nome);
						categorias.add(categoria);
						ultimo = categoria;
					}
					//criar produto e adc na categoria
					int idDoProduto = result.getInt("p_id");
					String nomeDoProduto = result.getString("p_nome");
					String descricaoDoProduto = result.getString("p_descricao");
					Produto p = new Produto(nomeDoProduto, descricaoDoProduto);
					p.setId(idDoProduto);
					ultimo.adiciona(p);
				}
			}
		}
		return categorias;
	}
}
