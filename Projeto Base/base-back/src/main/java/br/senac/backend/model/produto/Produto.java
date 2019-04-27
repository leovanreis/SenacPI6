package br.senac.backend.model.produto;

//Classe de negócio produto

public class Produto {

	// Atributos
	private Integer id_produto;
	private String nome_produto;
	private String descricao_produto;
	private String categoria_produto;
	private double valor_produto;
	private double valor_venda_produto;
	private Integer estoque_produto;
	private boolean ativo_produto;

	// Métodos de acesso
	public Integer getId_produto() {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public String getDescricao_produto() {
		return descricao_produto;
	}

	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}

	public String getCategoria_produto() {
		return categoria_produto;
	}

	public void setCategoria_produto(String categoria_produto) {
		this.categoria_produto = categoria_produto;
	}

	public double getValor_produto() {
		return valor_produto;
	}

	public void setValor_produto(double valor_produto) {
		this.valor_produto = valor_produto;
	}

	public double getValor_venda_produto() {
		return valor_venda_produto;
	}

	public void setValor_venda_produto(double valor_venda_produto) {
		this.valor_venda_produto = valor_venda_produto;
	}

	public Integer getEstoque_produto() {
		return estoque_produto;
	}

	public void setEstoque_produto(Integer estoque_produto) {
		this.estoque_produto = estoque_produto;
	}

	public boolean isAtivo_produto() {
		return ativo_produto;
	}

	public void setAtivo_produto(boolean ativo_produto) {
		this.ativo_produto = ativo_produto;
	}

}
