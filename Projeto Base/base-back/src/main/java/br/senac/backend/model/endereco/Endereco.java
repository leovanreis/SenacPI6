package br.senac.backend.model.endereco;

//Classe de negócio de cliente
public class Endereco {

	// Atributos
	private Integer id_endereco;
	private String rua_endereco;
	private String numero_endereco;
	private String cep_endereco;
	private String bairro_endereco;
	private String cidade_endereco;
	private String complemento_endereco;

	// Métodos de acesso
	public Integer getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(Integer id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getRua_endereco() {
		return rua_endereco;
	}

	public void setRua_endereco(String rua_endereco) {
		this.rua_endereco = rua_endereco;
	}

	public String getNumero_endereco() {
		return numero_endereco;
	}

	public void setNumero_endereco(String numero_endereco) {
		this.numero_endereco = numero_endereco;
	}

	public String getCep_endereco() {
		return cep_endereco;
	}

	public void setCep_endereco(String cep_endereco) {
		this.cep_endereco = cep_endereco;
	}

	public String getBairro_endereco() {
		return bairro_endereco;
	}

	public void setBairro_endereco(String bairro_endereco) {
		this.bairro_endereco = bairro_endereco;
	}

	public String getCidade_endereco() {
		return cidade_endereco;
	}

	public void setCidade_endereco(String cidade_endereco) {
		this.cidade_endereco = cidade_endereco;
	}

	public String getComplemento_endereco() {
		return complemento_endereco;
	}

	public void setComplemento_endereco(String complemento_endereco) {
		this.complemento_endereco = complemento_endereco;
	}

}
