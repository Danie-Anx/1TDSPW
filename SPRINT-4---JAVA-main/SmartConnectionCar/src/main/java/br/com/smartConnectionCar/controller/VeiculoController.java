package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.VeiculoDAO;
import br.com.smartConnectionCar.model.Veiculo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/veiculos")
public class VeiculoController {

    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> getAllVeiculos() {
        return veiculoDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVeiculoById(@PathParam("id") int id) {
        Veiculo veiculo = veiculoDAO.read(id);
        if (veiculo != null) {
            return Response.ok(veiculo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVeiculo(Veiculo veiculo) {
        veiculoDAO.create(veiculo);
        return Response.status(Response.Status.CREATED).entity(veiculo).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVeiculo(@PathParam("id") int id, Veiculo veiculo) {
        veiculo.setIdVeiculo(id);
        veiculoDAO.update(veiculo);
        return Response.ok(veiculo).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteVeiculo(@PathParam("id") int id) {
        veiculoDAO.delete(id);
        return Response.noContent().build();
    }
}
