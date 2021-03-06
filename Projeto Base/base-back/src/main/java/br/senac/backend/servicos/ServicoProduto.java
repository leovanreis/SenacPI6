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

import br.senac.backend.db.dao.DaoProduto;
import br.senac.backend.model.produto.Produto;

@Path("/produto")
public class ServicoProduto {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirProduto(Produto produto) {
		try {
			DaoProduto.inserir(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listarProduto() {
		try {
			return DaoProduto.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarProduto(Produto produto) {
		try {
			DaoProduto.atualizar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void removerProduto(@PathParam("id") Integer id) {
		try {
			DaoProduto.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
