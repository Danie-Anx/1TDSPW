package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.OficinaDAO;
import br.com.smartConnectionCar.model.Oficina;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/oficinas")
public class OficinaController {

    private OficinaDAO oficinaDAO = new OficinaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oficina> getAllOficinas() {
        return oficinaDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOficinaById(@PathParam("id") int id) {
        Oficina oficina = oficinaDAO.read(id);
        if (oficina != null) {
            return Response.ok(oficina).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOficina(Oficina oficina) {
        oficinaDAO.create(oficina);
        return Response.status(Response.Status.CREATED).entity(oficina).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOficina(@PathParam("id") int id, Oficina oficina) {
        oficina.setIdOficina(id);
        oficinaDAO.update(oficina);
        return Response.ok(oficina).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOficina(@PathParam("id") int id) {
        oficinaDAO.delete(id);
        return Response.noContent().build();
    }
}
