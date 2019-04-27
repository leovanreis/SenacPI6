package br.senac.backend.servicos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.senac.backend.db.dao.DaoItem;
import br.senac.backend.model.item.Item;

@Path("/pedido")
public class ServicoPedido {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirItem(Item item) {
		try {
			DaoItem.inserir(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> listarItem() {
		try {
			return DaoItem.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarItem(Item item) {
		try {
			DaoItem.atualizar(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void removerItem(@PathParam("id") Integer id) {
		try {
			DaoItem.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
