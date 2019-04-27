package br.senac.backend.model.clientes;

//Classe de negócio de cliente
public class Cliente {

	// Atributos
	private Integer id_cliente;
	private String nome_cliente;
	private Integer data_nascimento_cliente;
	private String sexo_cliente;
	private String cpf_cliente;
	private String cnjp_cliente;
	private boolean admin;
	private String senha;

	// Métodos de acesso
	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public String getCnjp_cliente() {
		return cnjp_cliente;
	}

	public void setCnjp_cliente(String cnjp_cliente) {
		this.cnjp_cliente = cnjp_cliente;
	}

	public boolean Admin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getData_nascimento_cliente() {
		return data_nascimento_cliente;
	}

	public void setData_nascimento_cliente(Integer data_nascimento_cliente) {
		this.data_nascimento_cliente = data_nascimento_cliente;
	}

	public String getSexo_cliente() {
		return sexo_cliente;
	}

	public void setSexo_cliente(String sexo_cliente) {
		this.sexo_cliente = sexo_cliente;
	}

}
