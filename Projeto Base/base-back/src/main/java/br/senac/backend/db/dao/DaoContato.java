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

		// Monta a string de inser��o de um contato no BD,
		// utilizando os dados do contatos passados como par�metro
		String sql = "INSERT INTO bd_pi6.contato (telefone_contato, celular_contato, email_contato) "
				+ " VALUES (?, ?, ?)";

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
			preparedStatement.setString(1, contato.getTelefone_contato());
			preparedStatement.setString(2, contato.getCelular_contato());
			preparedStatement.setString(3, contato.getEmail_contato());

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

	// Realiza a atualiza��o dos dados de um contato, com ID e dados
	// fornecidos como par�metro atrav�s de um objeto da classe "Contato"
	public static void atualizar(Contato contato) throws SQLException, Exception {

		// Monta a string de atualiza��o do contato no BD, utilizando
		// prepared statement
		String sql = "UPDATE bd_pi6.contato SET telefone_contato=?, celular_contato=?, email_contato=?"
				+ "WHERE (idcontato=?)";

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

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a exclus�o l�gica de um contato no BD, com ID fornecido
	// como par�metro. A exclus�o l�gica simplesmente "desliga" o
	// contato, configurando um atributo espec�fico, a ser ignorado
	// em todas as consultas de contato ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualiza��o do contato no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM bd_pi6.contato WHERE (idcontato=?);";

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

	// Lista todos os contatos da tabela contatos
	public static List<Contato> listar() throws SQLException, Exception {

		// Monta a string de listagem de contatos no banco, considerando
		// apenas a coluna de ativa��o de contatos ("enabled")
		String sql = "SELECT * FROM bd_pi6.contatos WHERE (telefone_contato=?, celular_contato, email_contato=?)";

		// Lista de contatos de resultado
		List<Contato> listaContatos = null;

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
				if (listaContatos == null) {
					listaContatos = new ArrayList<Contato>();
				}

				// Cria uma inst�ncia de Contatos e popula com os valores do BD
				Contato contato = new Contato();

				contato.setId_contato(result.getInt("idcontato"));
				contato.setTelefone_contato(result.getString("telefone_contato"));
				contato.setCelular_contato(result.getString("celular_contato"));
				contato.setEmail_contato(result.getString("celular_contato"));

				// Adiciona a inst�ncia na lista
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

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de contatos do banco de dados
		return listaContatos;

	}

//Procura um contatos no banco de dados, de acordo com o nome
//ou com o sobrenome, passado como par�metro
	public static List<Contato> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de contatos no banco, utilizando
		// o valor passado como par�metro para busca nas colunas de
		// telefone ou email (atrav�s do "LIKE" e ignorando min�sculas
		// ou mai�sculas, atrav�s do "UPPER" aplicado � coluna e ao
		// par�metro). Al�m disso, tamb�m considera apenas os elementos
		// que possuem a coluna de ativa��o de contatos configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM bd_pi6.contato WHERE ((UPPER(telefone_contato) LIKE UPPER(?) "
				+ "OR UPPER(contato.email_contato) LIKE UPPER(?)) AND idcontato=?)";

		// Lista de contatos de resultado
		List<Contato> listaContatos = null;

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
				if (listaContatos == null) {
					listaContatos = new ArrayList<Contato>();
				}

				// Cria uma inst�ncia de Contatos e popula com os valores do BD
				Contato contato = new Contato();

				contato.setId_contato(result.getInt("idcontato"));
				contato.setTelefone_contato(result.getString("telefone_contato"));
				contato.setCelular_contato(result.getString("celular_contato"));
				contato.setEmail_contato(result.getString("celular_contato"));

				// Adiciona a inst�ncia na lista
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

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de contatos do banco de dados
		return listaContatos;

	}

//Obt�m uma inst�ncia da classe "Contato" atrav�s de dados do
//banco de dados, de acordo com o ID fornecido como par�metro
	public static Contato obter(Integer id) throws SQLException, Exception {

		// Comp�e uma String de consulta que considera apenas o contato
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM contato WHERE (idcontato=? AND enabled=?)";

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

				// Cria uma inst�ncia de Contato e popula com os valores do BD
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
