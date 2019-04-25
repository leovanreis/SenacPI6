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

import br.senac.backend.db.dao.DaoCliente;
import br.senac.backend.model.clientes.Cliente;

@Path("/cliente")
public class ServicoCliente {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirCliente(Cliente cliente) {
		try {
			DaoCliente.inserir(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> listarClientes() {
		try {
			return DaoCliente.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarCliente(Cliente cliente) {
		try {
			DaoCliente.atualizar(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void removerCliente(@PathParam("id") Integer id) {
		try {
			DaoCliente.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
