package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.produto.Produto;

//Data Access Object de Produto. Realiza operações de BD com o produto. 
public class DaoProduto {

	// Insere um produto na tabela "produto" do banco de dados
	public static void inserir(Produto produto) throws SQLException, Exception {

		// Monta a string de inserção de um produto no BD,
		// utilizando os dados do produto passados como parâmetro
		String sql = "INSERT INTO bd_pi6.produto (nome_produto, descricao_produto, categoria_produto, valor_produto, valor_venda_produto, estoque_produto, ativo_produto) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
			preparedStatement.setString(1, produto.getNome_produto());
			preparedStatement.setString(2, produto.getDescricao_produto());
			preparedStatement.setString(3, produto.getCategoria_produto());
			preparedStatement.setDouble(4, produto.getValor_produto());
			preparedStatement.setDouble(5, produto.getValor_venda_produto());
			preparedStatement.setInt(6, produto.getEstoque_produto());
			preparedStatement.setBoolean(7, true);

			preparedStatement.execute();

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a atualização dos dados de um produto, com ID e dados
	// fornecidos como parâmetro através de um objeto da classe "Produto"
	public static void atualizar(Produto produto) throws SQLException, Exception {

		// Monta a string de atualização do produto no BD, utilizando
		// prepared statement
		String sql = "UPDATE bd_pi6.produto SET nome_produto=?, descricao_produto=?, categoria_produto=?, valor_produto=?, valor_venda_produto=?, estoque_produto=?"
				+ "WHERE (idproduto=?)";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"

			preparedStatement.setString(1, produto.getNome_produto());
			preparedStatement.setString(2, produto.getDescricao_produto());
			preparedStatement.setString(3, produto.getCategoria_produto());
			preparedStatement.setDouble(4, produto.getValor_produto());
			preparedStatement.setDouble(5, produto.getValor_venda_produto());
			preparedStatement.setInt(6, produto.getEstoque_produto());

			// Executa o comando no banco de dados
			preparedStatement.execute();

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a exclusão lógica de um produto no BD, com ID fornecido
	// como parâmetro. A exclusão lógica simplesmente "desliga" o
	// produto, configurando um atributo específico, a ser ignorado
	// em todas as consultas de produto ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualização do produto no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM bd_pi6.produto WHERE (idproduto=?);";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
			preparedStatement.setInt(1, id);

			// Executa o comando no banco de dados
			preparedStatement.execute();

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Lista todos os produto da tabela produto
	public static List<Produto> listar() throws SQLException, Exception {

		// Monta a string de listagem de produtos no banco, considerando
		// apenas a coluna de ativação de produtos ("enabled")
		String sql = "SELECT * FROM bd_pi6.produto WHERE (idproduto=?, nome_produto, descricao_produto=?,  categoria_produto=?, valor_produto=?, valor_venda_produto=?, estoque_produto)";

		// Lista de produtos de resultado
		List<Produto> listaProdutos = null;

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenará os resultados do banco de dados
		ResultSet result = null;

		try {
			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Itera por cada item do resultado
			while (result.next()) {

				// Se a lista não foi inicializada, a inicializa
				if (listaProdutos == null) {
					listaProdutos = new ArrayList<Produto>();
				}

				// Cria uma instância de produto e popula com os valores do BD
				Produto produto = new Produto();

				produto.setId_produto(result.getInt("idproduto"));
				produto.setNome_produto(result.getString("nome_produto"));
				produto.setDescricao_produto(result.getString("descricao_produto"));
				produto.setCategoria_produto(result.getString("categoria_produto"));
				produto.setValor_produto(result.getDouble("valor_produto"));
				produto.setValor_venda_produto(result.getDouble("valor_venda_produto"));
				produto.setEstoque_produto(result.getInt("estoque_produto"));

				// Adiciona a instância na lista
				listaProdutos.add(produto);

			}

		} finally {

			// Se o result ainda estiver aberto, realiza seu fechamento
			if (result != null && !result.isClosed()) {
				result.close();
			}

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de produto do banco de dados
		return listaProdutos;

	}

	// Procura um produto no banco de dados, de acordo com o nome
	// ou com o produto, passado como parâmetro
	public static List<Produto> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de produto no banco, utilizando
		// o valor passado como parâmetro para busca nas colunas de
		// nome ou produto (através do "LIKE" e ignorando minúsculas
		// ou maiúsculas, através do "UPPER" aplicado à coluna e ao
		// parâmetro). Além disso, também considera apenas os elementos
		// que possuem a coluna de ativação de produtos configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM bd_pi6.produto WHERE ((UPPER(nome_produto) LIKE UPPER(?) "
				+ "OR UPPER(produto.nome_produto) LIKE UPPER(?)) AND idproduto=?)";

		// Lista de produtos de resultado
		List<Produto> listaProdutos = null;

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenará os resultados do banco de dados
		ResultSet result = null;

		try {

			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
			preparedStatement.setString(1, "%" + valor + "%");
			preparedStatement.setString(2, "%" + valor + "%");
			preparedStatement.setBoolean(3, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Itera por cada item do resultado
			while (result.next()) {

				// Se a lista não foi inicializada, a inicializa
				if (listaProdutos == null) {
					listaProdutos = new ArrayList<Produto>();
				}

				// Cria uma instância de Produtos e popula com os valores do BD
				Produto produto = new Produto();

				produto.setId_produto(result.getInt("idproduto"));
				produto.setNome_produto(result.getString("nome_produto"));
				produto.setDescricao_produto(result.getString("descricao_produto"));
				produto.setCategoria_produto(result.getString("categoria_produto"));
				produto.setValor_produto(result.getInt("valor_produto"));
				produto.setValor_venda_produto(result.getInt("valor_venda_produto"));
				produto.setEstoque_produto(result.getInt("estoque_produto"));

				;

				// Adiciona a instância na lista
				listaProdutos.add(produto);

			}

		} finally {

			// Se o result ainda estiver aberto, realiza seu fechamento
			if (result != null && !result.isClosed()) {
				result.close();
			}

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de produtos do banco de dados
		return listaProdutos;

	}

//Obtém uma instância da classe "Produto" através de dados do
//banco de dados, de acordo com o ID fornecido como parâmetro
	public static Produto obter(Integer id) throws SQLException, Exception {

		// Compõe uma String de consulta que considera apenas o produto
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM produto WHERE (idproduto=? AND enabled=?)";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenará os resultados do banco de dados
		ResultSet result = null;

		try {
			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
			preparedStatement.setInt(1, id);
			preparedStatement.setBoolean(2, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Verifica se há pelo menos um resultado
			if (result.next()) {

				// Cria uma instância de Produto e popula com os valores do BD
				Produto produto = new Produto();

				produto.setId_produto(result.getInt("idproduto"));
				produto.setNome_produto(result.getString("nome_produto"));
				produto.setDescricao_produto(result.getString("descricao_produto"));
				produto.setCategoria_produto(result.getString("categoria_produto"));
				produto.setValor_produto(result.getInt("valor_produto"));
				produto.setValor_venda_produto(result.getInt("valor_venda_produto"));
				produto.setEstoque_produto(result.getInt("estoque_produto"));

				// Retorna o resultado
				return produto;

			}

		} finally {

			// Se o result ainda estiver aberto, realiza seu fechamento
			if (result != null && !result.isClosed()) {
				result.close();
			}

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Se chegamos aqui, o "return" anterior não foi executado porque
		// a pesquisa não teve resultados
		// Neste caso, não há um elemento a retornar, então retornamos "null"
		return null;

	}

}
