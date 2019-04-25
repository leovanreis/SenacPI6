package br.senac.backend.model.clientes;

//Classe de negócio de cliente
public class Cliente {

    //Atributos
    private Integer id;
    private String nome;
    private String cpf;

    //Métodos de acesso
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}    
}
