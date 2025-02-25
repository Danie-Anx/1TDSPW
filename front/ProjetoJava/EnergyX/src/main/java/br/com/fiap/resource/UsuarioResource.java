package br.com.fiap.resource;

import br.com.fiap.application.App;
import br.com.fiap.dto.request.UsuarioRequestDTO;
import br.com.fiap.dto.response.UsuarioResponseDTO;
import br.com.fiap.util.api.ResponseUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("usuarios")
public class UsuarioResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create (UsuarioRequestDTO dtoEntrada) {
        try {
            UsuarioResponseDTO dtoSaida = App.usuarioService.criarUsuario(dtoEntrada);
            return ResponseUtils.buildWithData(201, dtoSaida);
        }
        catch (BadRequestException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(400, exception.getMessage());
        }
        catch (Exception exception) {
            // Exibindo erro no log apenas para exceptions desconhecidas
            exception.printStackTrace();
            return ResponseUtils.buildWithSingleErrorMessage(500, exception.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("id") String sid) {
        try {
            int id = Integer.parseInt(sid);
            UsuarioResponseDTO dto = App.usuarioService.obterUsuarioPorId(id);
            return ResponseUtils.buildWithData(200, dto);
        }
        catch (NumberFormatException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(400, "ID deve ser um número");
        }
        catch (NotFoundException exception) {
            System.out.println("a");
            return ResponseUtils.buildWithSingleErrorMessage(404, exception.getMessage());
        }
        catch (Exception exception) {
            // Exibindo erro no log apenas para exceptions desconhecidas
            exception.printStackTrace();
            return ResponseUtils.buildWithSingleErrorMessage(500, exception.getMessage());
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String sid, UsuarioRequestDTO dtoEntrada) {
        try {
            int id = Integer.parseInt(sid);
            UsuarioResponseDTO dtoSaida = App.usuarioService.atualizarUsuarioPorId(id, dtoEntrada);
            return ResponseUtils.buildWithData(200, dtoSaida);
        }
        catch (NumberFormatException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(400, "ID deve ser um número");
        }
        catch (BadRequestException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(400, exception.getMessage());
        }
        catch (NotFoundException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(404, exception.getMessage());
        }
        catch (Exception exception) {
            // Exibindo erro no log apenas para exceptions desconhecidas
            exception.printStackTrace();
            return ResponseUtils.buildWithSingleErrorMessage(500, exception.getMessage());
        }
    }
}
