package br.com.fiap.resource;

import br.com.fiap.application.App;
import br.com.fiap.dto.response.SugestaoEconomiaResponseDTO;
import br.com.fiap.dto.response.TipoDispositivoResponseDTO;
import br.com.fiap.util.api.ResponseUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("usuarios/{idUsuario}/sugestoes-economia")
public class SugestoesEconomia {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll(@PathParam("idUsuario") String sidUsuario) {
        try {
            int idUsuario = Integer.parseInt(sidUsuario);
            List<SugestaoEconomiaResponseDTO> dtos = App.sugestaoEconomiaService.obterSugestoesEconomia(idUsuario);
            return ResponseUtils.buildWithData(200, dtos);
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

    @PUT
    @Path("/processar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAll(@PathParam("idUsuario") String sidUsuario) {
        try {
            int idUsuario = Integer.parseInt(sidUsuario);
            App.sugestaoEconomiaService.processarSugestoesEconomia(idUsuario);
            return ResponseUtils.buildWithoutData(200);
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
