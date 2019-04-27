package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.item.Item;

public class DaoItem {

	public static void inserir(Item item) throws SQLException, Exception {

		// Monta a string de inserção de um item no BD,
		// utilizando os dados do item passados como parâmetro
		String sql = "INSERT INTO bd_pi6.itemPedido (qtd_itemPedido) " + " VALUES (?)";

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
			preparedStatement.setInt(1, item.getQtd_item_pedido());
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

	// Realiza a atualização dos dados de um item, com ID e dados
	// fornecidos como parâmetro através de um objeto da classe "Item"
	public static void atualizar(Item item) throws SQLException, Exception {

		// Monta a string de atualização do item no BD, utilizando
		// prepared statement
		String sql = "UPDATE bd_pi6.itemPedido SET qtd_itemPedido=?" + "WHERE (iditemPedido=?)";

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

			preparedStatement.setInt(1, item.getQtd_item_pedido());

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

	// Realiza a exclusão lógica de um item no BD, com ID fornecido
	// como parâmetro. A exclusão lógica simplesmente "desliga" o
	// item, configurando um atributo específico, a ser ignorado
	// em todas as consultas de item ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualização do item no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM bd_pi6.item WHERE (iditemPedido=?);";

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

	// Lista todos os item da tabela item
	public static List<Item> listar() throws SQLException, Exception {

		// Monta a string de listagem de item no banco, considerando
		// apenas a coluna de ativação de item ("enabled")
		String sql = "SELECT * FROM bd_pi6.item WHERE (qtd_itemPedido=?)";

		// Lista de item de resultado
		List<Item> listaItem = null;

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
				if (listaItem == null) {
					listaItem = new ArrayList<Item>();
				}

				// Cria uma instância de Item e popula com os valores do BD
				Item item = new Item();

				item.setQtd_item_pedido(result.getInt("qtd_itemPedido"));

				// Adiciona a instância na lista
				listaItem.add(item);

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

		// Retorna a lista de item do banco de dados
		return listaItem;

	}

	// Procura um item no banco de dados, de acordo com o id
	// ou com o qtd, passado como parâmetro
	public static List<Item> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de item no banco, utilizando
		// o valor passado como parâmetro para busca nas colunas de
		// id ou qtd (através do "LIKE" e ignorando minúsculas
		// ou maiúsculas, através do "UPPER" aplicado à coluna e ao
		// parâmetro). Além disso, também considera apenas os elementos
		// que possuem a coluna de ativação de item configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM bd_pi6.item WHERE ((UPPER(id_item_pedido) LIKE UPPER(?) "
				+ "OR UPPER(item.qtd_item_pedido) LIKE UPPER(?)) AND iditemPedido=?)";

		// Lista de item de resultado
		List<Item> listaItem = null;

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
				if (listaItem == null) {
					listaItem = new ArrayList<Item>();
				}

				// Cria uma instância de Item e popula com os valores do BD
				Item item = new Item();

				item.setId_item_pedido(result.getInt("iditemPedido"));
				item.setQtd_item_pedido(result.getInt("qtd_itemPedido"));

				// Adiciona a instância na lista
				listaItem.add(item);

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

		// Retorna a lista de item do banco de dados
		return listaItem;

	}

//Obtém uma instância da classe "Pedido" através de dados do
//banco de dados, de acordo com o ID fornecido como parâmetro
	public static Item obter(Integer id) throws SQLException, Exception {

		// Compõe uma String de consulta que considera apenas o item
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM item.bd_pi6.i WHERE (iditemPedido=? AND enabled=?)";

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

				// Cria uma instância de item e popula com os valores do BD
				Item item = new Item();

				item.setId_item_pedido(result.getInt("iditemPedido"));
				item.setQtd_item_pedido(result.getInt("qtd_itemPedido"));

				// Retorna o resultado
				return item;

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
