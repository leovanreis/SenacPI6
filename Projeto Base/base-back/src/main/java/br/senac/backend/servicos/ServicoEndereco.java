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

import br.senac.backend.db.dao.DaoEndereco;
import br.senac.backend.model.endereco.Endereco;

@Path("/endereco")
public class ServicoEndereco {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirEndereco(Endereco endereco) {
		try {
			DaoEndereco.inserir(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Endereco> listarEndereco() {
		try {
			return DaoEndereco.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarEndereco(Endereco endereco) {
		try {
			DaoEndereco.atualizar(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void removerEndereco(@PathParam("id") Integer id) {
		try {
			DaoEndereco.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
