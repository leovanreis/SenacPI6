package br.senac.backend.model.item;

//Classe de negócio de cliente
public class Item {

	// Atributos
	private Integer id_item_pedido;
	private Integer qtd_item_pedido;

	// Métodos de acesso
	public Integer getId_item_pedido() {
		return id_item_pedido;
	}

	public void setId_item_pedido(Integer id_item_pedido) {
		this.id_item_pedido = id_item_pedido;
	}

	public Integer getQtd_item_pedido() {
		return qtd_item_pedido;
	}

	public void setQtd_item_pedido(Integer qtd_item_pedido) {
		this.qtd_item_pedido = qtd_item_pedido;
	}

}
