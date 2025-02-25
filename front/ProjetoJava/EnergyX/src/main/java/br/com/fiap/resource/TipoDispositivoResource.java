package br.com.fiap.resource;

import br.com.fiap.application.App;
import br.com.fiap.dto.response.TipoDispositivoResponseDTO;
import br.com.fiap.util.api.ResponseUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("tipos-dispositivo")
public class TipoDispositivoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        try {
            List<TipoDispositivoResponseDTO> dtos = App.tipoDispositivoService.obterTiposDispositivo();
            return ResponseUtils.buildWithData(200, dtos);
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
            TipoDispositivoResponseDTO dto = App.tipoDispositivoService.obterTipoDispositivoPorId(id);
            return ResponseUtils.buildWithData(200, dto);
        }
        catch (NumberFormatException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(400, "ID deve ser um número");
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
