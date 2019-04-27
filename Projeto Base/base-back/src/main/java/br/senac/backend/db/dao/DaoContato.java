package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.contato.Contato;

public class DaoContato {

	// Insere um contato na tabela "contato" do banco de dados
	public static void inserir(Contato contato) throws SQLException, Exception {

		// Monta a string de inserção de um contato no BD,
		// utilizando os dados do contatos passados como parâmetro
		String sql = "INSERT INTO bd_pi6.contato (telefone_contato, celular_contato, email_contato) "
				+ " VALUES (?, ?, ?)";

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
			preparedStatement.setString(1, contato.getTelefone_contato());
			preparedStatement.setString(2, contato.getCelular_contato());
			preparedStatement.setString(3, contato.getEmail_contato());

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

	// Realiza a atualização dos dados de um contato, com ID e dados
	// fornecidos como parâmetro através de um objeto da classe "Contato"
	public static void atualizar(Contato contato) throws SQLException, Exception {

		// Monta a string de atualização do contato no BD, utilizando
		// prepared statement
		String sql = "UPDATE bd_pi6.contato SET telefone_contato=?, celular_contato=?, email_contato=?"
				+ "WHERE (idcontato=?)";

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
			preparedStatement.setString(1, contato.getTelefone_contato());
			preparedStatement.setString(2, contato.getCelular_contato());
			preparedStatement.setString(3, contato.getEmail_contato());

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

	// Realiza a exclusão lógica de um contato no BD, com ID fornecido
	// como parâmetro. A exclusão lógica simplesmente "desliga" o
	// contato, configurando um atributo específico, a ser ignorado
	// em todas as consultas de contato ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualização do contato no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM bd_pi6.contato WHERE (idcontato=?);";

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

	// Lista todos os contatos da tabela contatos
	public static List<Contato> listar() throws SQLException, Exception {

		// Monta a string de listagem de contatos no banco, considerando
		// apenas a coluna de ativação de contatos ("enabled")
		String sql = "SELECT * FROM bd_pi6.contatos WHERE (telefone_contato=?, celular_contato, email_contato=?)";

		// Lista de contatos de resultado
		List<Contato> listaContatos = null;

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
				if (listaContatos == null) {
					listaContatos = new ArrayList<Contato>();
				}

				// Cria uma instância de Contatos e popula com os valores do BD
				Contato contato = new Contato();

				contato.setId_contato(result.getInt("idcontato"));
				contato.setTelefone_contato(result.getString("telefone_contato"));
				contato.setCelular_contato(result.getString("celular_contato"));
				contato.setEmail_contato(result.getString("celular_contato"));

				// Adiciona a instância na lista
				listaContatos.add(contato);

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

		// Retorna a lista de contatos do banco de dados
		return listaContatos;

	}

//Procura um contatos no banco de dados, de acordo com o nome
//ou com o sobrenome, passado como parâmetro
	public static List<Contato> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de contatos no banco, utilizando
		// o valor passado como parâmetro para busca nas colunas de
		// telefone ou email (através do "LIKE" e ignorando minúsculas
		// ou maiúsculas, através do "UPPER" aplicado à coluna e ao
		// parâmetro). Além disso, também considera apenas os elementos
		// que possuem a coluna de ativação de contatos configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM bd_pi6.contato WHERE ((UPPER(telefone_contato) LIKE UPPER(?) "
				+ "OR UPPER(contato.email_contato) LIKE UPPER(?)) AND idcontato=?)";

		// Lista de contatos de resultado
		List<Contato> listaContatos = null;

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
				if (listaContatos == null) {
					listaContatos = new ArrayList<Contato>();
				}

				// Cria uma instância de Contatos e popula com os valores do BD
				Contato contato = new Contato();

				contato.setId_contato(result.getInt("idcontato"));
				contato.setTelefone_contato(result.getString("telefone_contato"));
				contato.setCelular_contato(result.getString("celular_contato"));
				contato.setEmail_contato(result.getString("celular_contato"));

				// Adiciona a instância na lista
				listaContatos.add(contato);

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

		// Retorna a lista de contatos do banco de dados
		return listaContatos;

	}

//Obtém uma instância da classe "Contato" através de dados do
//banco de dados, de acordo com o ID fornecido como parâmetro
	public static Contato obter(Integer id) throws SQLException, Exception {

		// Compõe uma String de consulta que considera apenas o contato
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM contato WHERE (idcontato=? AND enabled=?)";

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

				// Cria uma instância de Contato e popula com os valores do BD
				Contato contato = new Contato();

				contato.setId_contato(result.getInt("idcontato"));
				contato.setTelefone_contato(result.getString("telefone_contato"));
				contato.setCelular_contato(result.getString("celular_contato"));
				contato.setEmail_contato(result.getString("celular_contato"));

				// Retorna o resultado
				return contato;

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
