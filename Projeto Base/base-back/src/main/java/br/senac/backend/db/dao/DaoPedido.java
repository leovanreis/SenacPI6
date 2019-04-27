package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.pedido.Pedido;

public class DaoPedido {

	// Insere um pedido na tabela "pedido" do banco de dados
	public static void inserir(Pedido pedido) throws SQLException, Exception {

		// Monta a string de inser��o de um pedido no BD,
		// utilizando os dados do pedidos passados como par�metro
		String sql = "INSERT INTO bd_pi6.pedido (data_pedido, numero_pedido) " + " VALUES (?, ?)";

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
			Timestamp t = new Timestamp(pedido.getData_pedido().getTime());
			preparedStatement.setTimestamp(1, t);
			preparedStatement.setInt(2, pedido.getNumero_pedido());

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

	// Realiza a atualiza��o dos dados de um pedido, com ID e dados
	// fornecidos como par�metro atrav�s de um objeto da classe "Pedido"
	public static void atualizar(Pedido pedido) throws SQLException, Exception {

		// Monta a string de atualiza��o do pedido no BD, utilizando
		// prepared statement
		String sql = "UPDATE bd_pi6.pedido SET data_pedido=?, numero_pedido=?" + "WHERE (idpedido=?)";

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

			Timestamp t = new Timestamp(pedido.getData_pedido().getTime());
			preparedStatement.setTimestamp(1, t);
			preparedStatement.setInt(2, pedido.getNumero_pedido());

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

	// Realiza a exclus�o l�gica de um pedido no BD, com ID fornecido
	// como par�metro. A exclus�o l�gica simplesmente "desliga" o
	// pedido, configurando um atributo espec�fico, a ser ignorado
	// em todas as consultas de pedido ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualiza��o do pedido no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM bd_pi6.pedido WHERE (idpedido=?);";

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

	// Lista todos os pedido da tabela pedido
	public static List<Pedido> listar() throws SQLException, Exception {

		// Monta a string de listagem de pedidos no banco, considerando
		// apenas a coluna de ativa��o de pedido ("enabled")
		String sql = "SELECT * FROM bd_pi6.pedidos WHERE (data_pedido=?, numero_pedido=?)";

		// Lista de pedido de resultado
		List<Pedido> listaPedidos = null;

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
				if (listaPedidos == null) {
					listaPedidos = new ArrayList<Pedido>();
				}

				// Cria uma inst�ncia de Pedido e popula com os valores do BD
				Pedido pedido = new Pedido();

				java.util.Date d = new java.util.Date(result.getTimestamp("data_pedido").getTime());
				pedido.setData_pedido(d);
				pedido.setNumero_pedido(result.getInt("numero_pedido"));

				// Adiciona a inst�ncia na lista
				listaPedidos.add(pedido);

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

		// Retorna a lista de pedido do banco de dados
		return listaPedidos;

	}

	// Procura um pedido no banco de dados, de acordo com o nome
	// ou com o sobrenome, passado como par�metro
	public static List<Pedido> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de pedido no banco, utilizando
		// o valor passado como par�metro para busca nas colunas de
		// numero pedido ou data_pedido (atrav�s do "LIKE" e ignorando min�sculas
		// ou mai�sculas, atrav�s do "UPPER" aplicado � coluna e ao
		// par�metro). Al�m disso, tamb�m considera apenas os elementos
		// que possuem a coluna de ativa��o de pedido configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM bd_pi6.pedido WHERE ((UPPER(numero_pedido) LIKE UPPER(?) "
				+ "OR UPPER(pedido.data_pedido) LIKE UPPER(?)) AND idpedido=?)";

		// Lista de pedido de resultado
		List<Pedido> listaPedido = null;

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
				if (listaPedido == null) {
					listaPedido = new ArrayList<Pedido>();
				}

				// Cria uma inst�ncia de Pedido e popula com os valores do BD
				Pedido pedido = new Pedido();

				java.util.Date d = new java.util.Date(result.getTimestamp("data_pedido").getTime());
				pedido.setData_pedido(d);
				pedido.setNumero_pedido(result.getInt("numero_pedido"));

				// Adiciona a inst�ncia na lista
				listaPedido.add(pedido);

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

		// Retorna a lista de pedido do banco de dados
		return listaPedido;

	}

	// Obt�m uma inst�ncia da classe "Pedido" atrav�s de dados do
	// banco de dados, de acordo com o ID fornecido como par�metro
	public static Pedido obter(Integer id) throws SQLException, Exception {

		// Comp�e uma String de consulta que considera apenas o pedido
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM pedido WHERE (idpedido=? AND enabled=?)";

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

				// Cria uma inst�ncia de Pedido e popula com os valores do BD
				Pedido pedido = new Pedido();

				java.util.Date d = new java.util.Date(result.getTimestamp("data_pedido").getTime());
				pedido.setData_pedido(d);
				pedido.setId_pedido(result.getInt("idpedido"));

				// Retorna o resultado
				return pedido;

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
