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

		// Monta a string de inser��o de um item no BD,
		// utilizando os dados do item passados como par�metro
		String sql = "INSERT INTO bd_pi6.itemPedido (qtd_itemPedido) " + " VALUES (?)";

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"
			preparedStatement.setInt(1, item.getQtd_item_pedido());
			preparedStatement.execute();

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a atualiza��o dos dados de um item, com ID e dados
	// fornecidos como par�metro atrav�s de um objeto da classe "Item"
	public static void atualizar(Item item) throws SQLException, Exception {

		// Monta a string de atualiza��o do item no BD, utilizando
		// prepared statement
		String sql = "UPDATE bd_pi6.itemPedido SET qtd_itemPedido=?" + "WHERE (iditemPedido=?)";

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"

			preparedStatement.setInt(1, item.getQtd_item_pedido());

			// Executa o comando no banco de dados
			preparedStatement.execute();

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a exclus�o l�gica de um item no BD, com ID fornecido
	// como par�metro. A exclus�o l�gica simplesmente "desliga" o
	// item, configurando um atributo espec�fico, a ser ignorado
	// em todas as consultas de item ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualiza��o do item no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM bd_pi6.item WHERE (iditemPedido=?);";

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"
			preparedStatement.setInt(1, id);

			// Executa o comando no banco de dados
			preparedStatement.execute();

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Lista todos os item da tabela item
	public static List<Item> listar() throws SQLException, Exception {

		// Monta a string de listagem de item no banco, considerando
		// apenas a coluna de ativa��o de item ("enabled")
		String sql = "SELECT * FROM bd_pi6.item WHERE (qtd_itemPedido=?)";

		// Lista de item de resultado
		List<Item> listaItem = null;

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenar� os resultados do banco de dados
		ResultSet result = null;

		try {
			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Itera por cada item do resultado
			while (result.next()) {

				// Se a lista n�o foi inicializada, a inicializa
				if (listaItem == null) {
					listaItem = new ArrayList<Item>();
				}

				// Cria uma inst�ncia de Item e popula com os valores do BD
				Item item = new Item();

				item.setQtd_item_pedido(result.getInt("qtd_itemPedido"));

				// Adiciona a inst�ncia na lista
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

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de item do banco de dados
		return listaItem;

	}

	// Procura um item no banco de dados, de acordo com o id
	// ou com o qtd, passado como par�metro
	public static List<Item> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de item no banco, utilizando
		// o valor passado como par�metro para busca nas colunas de
		// id ou qtd (atrav�s do "LIKE" e ignorando min�sculas
		// ou mai�sculas, atrav�s do "UPPER" aplicado � coluna e ao
		// par�metro). Al�m disso, tamb�m considera apenas os elementos
		// que possuem a coluna de ativa��o de item configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM bd_pi6.item WHERE ((UPPER(id_item_pedido) LIKE UPPER(?) "
				+ "OR UPPER(item.qtd_item_pedido) LIKE UPPER(?)) AND iditemPedido=?)";

		// Lista de item de resultado
		List<Item> listaItem = null;

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenar� os resultados do banco de dados
		ResultSet result = null;

		try {

			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"
			preparedStatement.setString(1, "%" + valor + "%");
			preparedStatement.setString(2, "%" + valor + "%");
			preparedStatement.setBoolean(3, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Itera por cada item do resultado
			while (result.next()) {

				// Se a lista n�o foi inicializada, a inicializa
				if (listaItem == null) {
					listaItem = new ArrayList<Item>();
				}

				// Cria uma inst�ncia de Item e popula com os valores do BD
				Item item = new Item();

				item.setId_item_pedido(result.getInt("iditemPedido"));
				item.setQtd_item_pedido(result.getInt("qtd_itemPedido"));

				// Adiciona a inst�ncia na lista
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

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de item do banco de dados
		return listaItem;

	}

//Obt�m uma inst�ncia da classe "Pedido" atrav�s de dados do
//banco de dados, de acordo com o ID fornecido como par�metro
	public static Item obter(Integer id) throws SQLException, Exception {

		// Comp�e uma String de consulta que considera apenas o item
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM item.bd_pi6.i WHERE (iditemPedido=? AND enabled=?)";

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenar� os resultados do banco de dados
		ResultSet result = null;

		try {
			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"
			preparedStatement.setInt(1, id);
			preparedStatement.setBoolean(2, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Verifica se h� pelo menos um resultado
			if (result.next()) {

				// Cria uma inst�ncia de item e popula com os valores do BD
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

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Se chegamos aqui, o "return" anterior n�o foi executado porque
		// a pesquisa n�o teve resultados
		// Neste caso, n�o h� um elemento a retornar, ent�o retornamos "null"
		return null;

	}

}
