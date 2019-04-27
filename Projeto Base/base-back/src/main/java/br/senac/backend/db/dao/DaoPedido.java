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

		// Monta a string de inserção de um pedido no BD,
		// utilizando os dados do pedidos passados como parâmetro
		String sql = "INSERT INTO bd_pi6.pedido (data_pedido, numero_pedido) " + " VALUES (?, ?)";

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
			Timestamp t = new Timestamp(pedido.getData_pedido().getTime());
			preparedStatement.setTimestamp(1, t);
			preparedStatement.setInt(2, pedido.getNumero_pedido());

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

	// Realiza a atualização dos dados de um pedido, com ID e dados
	// fornecidos como parâmetro através de um objeto da classe "Pedido"
	public static void atualizar(Pedido pedido) throws SQLException, Exception {

		// Monta a string de atualização do pedido no BD, utilizando
		// prepared statement
		String sql = "UPDATE bd_pi6.pedido SET data_pedido=?, numero_pedido=?" + "WHERE (idpedido=?)";

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

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a exclusão lógica de um pedido no BD, com ID fornecido
	// como parâmetro. A exclusão lógica simplesmente "desliga" o
	// pedido, configurando um atributo específico, a ser ignorado
	// em todas as consultas de pedido ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualização do pedido no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM bd_pi6.pedido WHERE (idpedido=?);";

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

	// Lista todos os pedido da tabela pedido
	public static List<Pedido> listar() throws SQLException, Exception {

		// Monta a string de listagem de pedidos no banco, considerando
		// apenas a coluna de ativação de pedido ("enabled")
		String sql = "SELECT * FROM bd_pi6.pedidos WHERE (data_pedido=?, numero_pedido=?)";

		// Lista de pedido de resultado
		List<Pedido> listaPedidos = null;

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
				if (listaPedidos == null) {
					listaPedidos = new ArrayList<Pedido>();
				}

				// Cria uma instância de Pedido e popula com os valores do BD
				Pedido pedido = new Pedido();

				java.util.Date d = new java.util.Date(result.getTimestamp("data_pedido").getTime());
				pedido.setData_pedido(d);
				pedido.setNumero_pedido(result.getInt("numero_pedido"));

				// Adiciona a instância na lista
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

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de pedido do banco de dados
		return listaPedidos;

	}

	// Procura um pedido no banco de dados, de acordo com o nome
	// ou com o sobrenome, passado como parâmetro
	public static List<Pedido> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de pedido no banco, utilizando
		// o valor passado como parâmetro para busca nas colunas de
		// numero pedido ou data_pedido (através do "LIKE" e ignorando minúsculas
		// ou maiúsculas, através do "UPPER" aplicado à coluna e ao
		// parâmetro). Além disso, também considera apenas os elementos
		// que possuem a coluna de ativação de pedido configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM bd_pi6.pedido WHERE ((UPPER(numero_pedido) LIKE UPPER(?) "
				+ "OR UPPER(pedido.data_pedido) LIKE UPPER(?)) AND idpedido=?)";

		// Lista de pedido de resultado
		List<Pedido> listaPedido = null;

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
				if (listaPedido == null) {
					listaPedido = new ArrayList<Pedido>();
				}

				// Cria uma instância de Pedido e popula com os valores do BD
				Pedido pedido = new Pedido();

				java.util.Date d = new java.util.Date(result.getTimestamp("data_pedido").getTime());
				pedido.setData_pedido(d);
				pedido.setNumero_pedido(result.getInt("numero_pedido"));

				// Adiciona a instância na lista
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

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de pedido do banco de dados
		return listaPedido;

	}

	// Obtém uma instância da classe "Pedido" através de dados do
	// banco de dados, de acordo com o ID fornecido como parâmetro
	public static Pedido obter(Integer id) throws SQLException, Exception {

		// Compõe uma String de consulta que considera apenas o pedido
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM pedido WHERE (idpedido=? AND enabled=?)";

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

				// Cria uma instância de Pedido e popula com os valores do BD
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
