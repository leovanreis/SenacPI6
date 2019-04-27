package br.senac.backend.model.pedido;

import java.util.Date;

//Classe de negócio de cliente
public class Pedido {

	// Atributos
	private Integer id_pedido;
	private Date data_pedido;
	private Integer numero_pedido;

	// Métodos de acesso
	public Integer getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Date getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}

	public Integer getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(Integer numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

}
