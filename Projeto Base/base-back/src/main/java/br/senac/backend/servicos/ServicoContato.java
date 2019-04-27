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

import br.senac.backend.db.dao.DaoContato;
import br.senac.backend.model.contato.Contato;

@Path("/contato")
public class ServicoContato {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirContato(Contato contato) {
		try {
			DaoContato.inserir(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contato> listarContatos() {
		try {
			return DaoContato.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarContato(Contato contato) {
		try {
			DaoContato.atualizar(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void removerContato(@PathParam("id") Integer id) {
		try {
			DaoContato.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
