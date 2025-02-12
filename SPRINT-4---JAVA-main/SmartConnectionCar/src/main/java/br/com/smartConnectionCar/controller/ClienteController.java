package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.ClienteDAO;
import br.com.smartConnectionCar.model.Cliente;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
public class ClienteController {

    private ClienteDAO clienteDAO = new ClienteDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getAllClientes() {
        return clienteDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClienteById(@PathParam("id") int id) {
        Cliente cliente = clienteDAO.read(id);
        if (cliente != null) {
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCliente(Cliente cliente) {
        clienteDAO.create(cliente);
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCliente(@PathParam("id") int id, Cliente cliente) {
        cliente.setIdCliente(id);
        clienteDAO.update(cliente);
        return Response.ok(cliente).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") int id) {
        clienteDAO.delete(id);
        return Response.noContent().build();
    }
}
