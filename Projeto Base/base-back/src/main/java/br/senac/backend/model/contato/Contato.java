package br.senac.backend.model.contato;

//Classe de negócio de contato
public class Contato {

	// Atributos
	private Integer id_contato;
	private String telefone_contato;
	private String celular_contato;
	private String email_contato;

	// Métodos de acesso
	public Integer getId_contato() {
		return id_contato;
	}

	public void setId_contato(Integer id_contato) {
		this.id_contato = id_contato;
	}

	public String getTelefone_contato() {
		return telefone_contato;
	}

	public void setTelefone_contato(String telefone_contato) {
		this.telefone_contato = telefone_contato;
	}

	public String getCelular_contato() {
		return celular_contato;
	}

	public void setCelular_contato(String celular_contato) {
		this.celular_contato = celular_contato;
	}

	public String getEmail_contato() {
		return email_contato;
	}

	public void setEmail_contato(String email_contato) {
		this.email_contato = email_contato;
	}

}
