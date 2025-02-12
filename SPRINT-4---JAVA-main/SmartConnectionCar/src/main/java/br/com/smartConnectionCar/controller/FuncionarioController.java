package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.FuncionarioDAO;
import br.com.smartConnectionCar.model.Funcionario;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/funcionarios")
public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFuncionarioById(@PathParam("id") int id) {
        Funcionario funcionario = funcionarioDAO.read(id);
        if (funcionario != null) {
            return Response.ok(funcionario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFuncionario(Funcionario funcionario) {
        funcionarioDAO.create(funcionario);
        return Response.status(Response.Status.CREATED).entity(funcionario).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFuncionario(@PathParam("id") int id, Funcionario funcionario) {
        funcionario.setIdFuncionario(id);
        funcionarioDAO.update(funcionario);
        return Response.ok(funcionario).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFuncionario(@PathParam("id") int id) {
        funcionarioDAO.delete(id);
        return Response.noContent().build();
    }

}
