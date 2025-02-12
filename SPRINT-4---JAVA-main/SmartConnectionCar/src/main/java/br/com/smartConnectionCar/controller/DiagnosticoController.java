package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.DiagnosticoDAO;
import br.com.smartConnectionCar.model.Diagnostico;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/diagnosticos")
public class DiagnosticoController {

    private DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiagnosticoById(@PathParam("id") int id) {
        Diagnostico diagnostico = diagnosticoDAO.read(id);
        if (diagnostico != null) {
            return Response.ok(diagnostico).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDiagnostico(Diagnostico diagnostico) {
        diagnosticoDAO.create(diagnostico);
        return Response.status(Response.Status.CREATED).entity(diagnostico).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDiagnostico(@PathParam("id") int id, Diagnostico diagnostico) {
        diagnostico.setIdDiagnostico(id);
        diagnosticoDAO.update(diagnostico);
        return Response.ok(diagnostico).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDiagnostico(@PathParam("id") int id) {
        diagnosticoDAO.delete(id);
        return Response.noContent().build();
    }
}
