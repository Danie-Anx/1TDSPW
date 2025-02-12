package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.AgendamentoDAO;
import br.com.smartConnectionCar.model.Agendamento;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/agendamentos")
public class AgendamentoController {

    private AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAgendamentoById(@PathParam("id") int id) {
        Agendamento agendamento = agendamentoDAO.read(id);
        if (agendamento != null) {
            return Response.ok(agendamento).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento com ID " + id + " não encontrado").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAgendamento(Agendamento agendamento) {
        // Validação básica
        if (agendamento.getData() == null || agendamento.getServico() == null || agendamento.getServico().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados insuficientes para criar um agendamento").build();
        }

        agendamentoDAO.create(agendamento);
        return Response.status(Response.Status.CREATED).entity(agendamento).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAgendamento(@PathParam("id") int id, Agendamento agendamento) {
        Agendamento existingAgendamento = agendamentoDAO.read(id);

        if (existingAgendamento == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento com ID " + id + " não encontrado para atualização").build();
        }

        agendamento.setIdAgendamento(id);
        agendamentoDAO.update(agendamento);
        return Response.ok(agendamento).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAgendamento(@PathParam("id") int id) {
        Agendamento existingAgendamento = agendamentoDAO.read(id);

        if (existingAgendamento == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento com ID " + id + " não encontrado para exclusão").build();
        }

        agendamentoDAO.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
