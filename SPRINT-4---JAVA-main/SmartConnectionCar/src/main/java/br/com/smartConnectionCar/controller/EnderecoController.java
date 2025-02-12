package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.EnderecoDAO;
import br.com.smartConnectionCar.model.Endereco;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/enderecos")
public class EnderecoController {

    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> getAllEnderecos() {
        return enderecoDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnderecoById(@PathParam("id") int id) {
        Endereco endereco = enderecoDAO.read(id);
        if (endereco != null) {
            return Response.ok(endereco).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEndereco(Endereco endereco) {
        enderecoDAO.create(endereco);
        return Response.status(Response.Status.CREATED).entity(endereco).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEndereco(@PathParam("id") int id, Endereco endereco) {
        endereco.setIdEndereco(id);
        enderecoDAO.update(endereco);
        return Response.ok(endereco).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEndereco(@PathParam("id") int id) {
        enderecoDAO.delete(id);
        return Response.noContent().build();
    }
}
