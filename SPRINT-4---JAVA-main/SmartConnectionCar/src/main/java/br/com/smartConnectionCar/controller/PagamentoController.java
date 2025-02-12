package br.com.smartConnectionCar.controller;

import br.com.smartConnectionCar.dao.PagamentoDAO;
import br.com.smartConnectionCar.model.Pagamento;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/pagamentos")
public class PagamentoController {

    private PagamentoDAO pagamentoDAO = new PagamentoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pagamento> getAllPagamentos() {
        return pagamentoDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPagamentoById(@PathParam("id") int id) {
        Pagamento pagamento = pagamentoDAO.read(id);
        if (pagamento != null) {
            return Response.ok(pagamento).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPagamento(Pagamento pagamento) {
        pagamentoDAO.create(pagamento);
        return Response.status(Response.Status.CREATED).entity(pagamento).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePagamento(@PathParam("id") int id, Pagamento pagamento) {
        pagamento.setIdPagamento(id);
        pagamentoDAO.update(pagamento);
        return Response.ok(pagamento).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePagamento(@PathParam("id") int id) {
        pagamentoDAO.delete(id);
        return Response.noContent().build();
    }
}
