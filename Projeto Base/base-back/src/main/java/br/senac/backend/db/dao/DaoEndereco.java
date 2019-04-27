package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.endereco.Endereco;

public class DaoEndereco {
	// Insere um endereco na tabela "endereco" do banco de dados
	public static void inserir(Endereco endereco) throws SQLException, Exception {

		// Monta a string de inser��o de um endereco no BD,
		// utilizando os dados do enderecos passados como par�metro
		String sql = "INSERT INTO bd_pi6.endereco (rua_endereco, numero_endereco, cep_endereco, bairro_endereco, cidade_endereco, complemento_endereco) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

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
			preparedStatement.setString(1, endereco.getRua_endereco());
			preparedStatement.setString(2, endereco.getNumero_endereco());
			preparedStatement.setString(3, endereco.getCep_endereco());
			preparedStatement.setString(4, endereco.getBairro_endereco());
			preparedStatement.setString(5, endereco.getCidade_endereco());
			preparedStatement.setString(6, endereco.getComplemento_endereco());

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

	// Realiza a atualiza��o dos dados de um endereco, com ID e dados
	// fornecidos como par�metro atrav�s de um objeto da classe "Endereco"
	public static void atualizar(Endereco endereco) throws SQLException, Exception {

		// Monta a string de atualiza��o do endereco no BD, utilizando
		// prepared statement
		String sql = "UPDATE bd_pi6.endereco SET rua_endereco=?, numero_endereco=?, cep_endereco=?, bairro_endereco, cidade_endereco, complemento_endereco"
				+ "WHERE (idendereco=?)";

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
			preparedStatement.setString(1, endereco.getRua_endereco());
			preparedStatement.setString(2, endereco.getNumero_endereco());
			preparedStatement.setString(3, endereco.getCep_endereco());
			preparedStatement.setString(4, endereco.getBairro_endereco());
			preparedStatement.setString(5, endereco.getCidade_endereco());
			preparedStatement.setString(6, endereco.getComplemento_endereco());

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

	// Realiza a exclus�o l�gica de um endereco no BD, com ID fornecido
	// como par�metro. A exclus�o l�gica simplesmente "desliga" o
	// endereco, configurando um atributo espec�fico, a ser ignorado
	// em todas as consultas de endereco ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualiza��o do endereco no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM bd_pi6.endereco WHERE (idendereco=?);";

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

	// Lista todos os endereco da tabela endereco
	public static List<Endereco> listar() throws SQLException, Exception {

		// Monta a string de listagem de endereco no banco, considerando
		// apenas a coluna de ativa��o de endereco ("enabled")
		String sql = "SELECT * FROM bd_pi6.endereco WHERE (rua_endereco=?, numero_endereco=?, cep_endereco=?,  bairro_endereco=?, cidade_endereco=?, complemento_endereco=? )";

		// Lista de endereco de resultado
		List<Endereco> listaEndereco = null;

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
				if (listaEndereco == null) {
					listaEndereco = new ArrayList<Endereco>();
				}

				// Cria uma inst�ncia de Endereco e popula com os valores do BD
				Endereco endereco = new Endereco();

				endereco.setId_endereco(result.getInt("idendereco"));
				endereco.setRua_endereco(result.getString("rua_endereco"));
				endereco.setNumero_endereco(result.getString("numero_endereco"));
				endereco.setCep_endereco(result.getString("cep_endereco"));
				endereco.setBairro_endereco(result.getString("bairro_endereco"));
				endereco.setCep_endereco(result.getString("cidade_endereco"));
				endereco.setComplemento_endereco(result.getString("complemento_endereco"));

				// Adiciona a inst�ncia na lista
				listaEndereco.add(endereco);

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

		// Retorna a lista de endereco do banco de dados
		return listaEndereco;

	}

	// Procura um endereco no banco de dados, de acordo com o rua
	// ou com o cep, passado como par�metro
	public static List<Endereco> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de endereco no banco, utilizando
		// o valor passado como par�metro para busca nas colunas de
		// nome ou sobrenome (atrav�s do "LIKE" e ignorando min�sculas
		// ou mai�sculas, atrav�s do "UPPER" aplicado � coluna e ao
		// par�metro). Al�m disso, tamb�m considera apenas os elementos
		// que possuem a coluna de ativa��o de endereco configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM bd_pi6.endereco WHERE ((UPPER(rua_endereco) LIKE UPPER(?) "
				+ "OR UPPER(endereco.cep_endereco) LIKE UPPER(?)) AND idendereco=?)";

		// Lista de endereco de resultado
		List<Endereco> listaEndereco = null;

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
				if (listaEndereco == null) {
					listaEndereco = new ArrayList<Endereco>();
				}

				// Cria uma inst�ncia de Endereco e popula com os valores do BD
				Endereco endereco = new Endereco();

				endereco.setId_endereco(result.getInt("idendereco"));
				endereco.setRua_endereco(result.getString("rua_endereco"));
				endereco.setNumero_endereco(result.getString("numero_endereco"));
				endereco.setCep_endereco(result.getString("cep_endereco"));
				endereco.setBairro_endereco(result.getString("bairro_endereco"));
				endereco.setCep_endereco(result.getString("cidade_endereco"));
				endereco.setComplemento_endereco(result.getString("complemento_endereco"));

				// Adiciona a inst�ncia na lista
				listaEndereco.add(endereco);

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

		// Retorna a lista de endereco do banco de dados
		return listaEndereco;

	}

	// Obt�m uma inst�ncia da classe "Endereco" atrav�s de dados do
	// banco de dados, de acordo com o ID fornecido como par�metro
	public static Endereco obter(Integer id) throws SQLException, Exception {

		// Comp�e uma String de consulta que considera apenas o endereco
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM endereco WHERE (idendereco=? AND enabled=?)";

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

				// Cria uma inst�ncia de Endereco e popula com os valores do BD
				Endereco endereco = new Endereco();

				endereco.setId_endereco(result.getInt("idendereco"));
				endereco.setRua_endereco(result.getString("rua_endereco"));
				endereco.setNumero_endereco(result.getString("numero_endereco"));
				endereco.setCep_endereco(result.getString("cep_endereco"));
				endereco.setBairro_endereco(result.getString("bairro_endereco"));
				endereco.setCep_endereco(result.getString("cidade_endereco"));
				endereco.setComplemento_endereco(result.getString("complemento_endereco"));

				// Retorna o resultado
				return endereco;

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
