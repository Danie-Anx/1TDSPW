package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.OrcamentoDAO;
import br.com.smartConnectionCar.model.Orcamento;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/orcamentos")
public class OrcamentoController {

    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orcamento> getAllOrcamentos() {
        return orcamentoDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrcamentoById(@PathParam("id") int id) {
        Orcamento orcamento = orcamentoDAO.read(id);
        if (orcamento != null) {
            return Response.ok(orcamento).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrcamento(Orcamento orcamento) {
        orcamentoDAO.create(orcamento);
        return Response.status(Response.Status.CREATED).entity(orcamento).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrcamento(@PathParam("id") int id, Orcamento orcamento) {
        orcamento.setIdOrcamento(id);
        orcamentoDAO.update(orcamento);
        return Response.ok(orcamento).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrcamento(@PathParam("id") int id) {
        orcamentoDAO.delete(id);
        return Response.noContent().build();
    }
}
