package br.com.fiap.resource;

import br.com.fiap.application.App;
import br.com.fiap.dto.request.UsuarioLoginRequestDTO;
import br.com.fiap.dto.request.UsuarioRequestDTO;
import br.com.fiap.dto.response.UsuarioResponseDTO;
import br.com.fiap.util.api.ResponseUtils;
import br.com.fiap.util.api.UnauthorizedException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("login")
public class UsuarioLoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create (UsuarioLoginRequestDTO dtoEntrada) {
        try {
            UsuarioResponseDTO dtoSaida = App.usuarioService.login(dtoEntrada);
            return ResponseUtils.buildWithData(200, dtoSaida);
        }
        catch (UnauthorizedException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(401, exception.getMessage());
        }
        catch (Exception exception) {
            // Exibindo erro no log apenas para exceptions desconhecidas
            exception.printStackTrace();
            return ResponseUtils.buildWithSingleErrorMessage(500, exception.getMessage());
        }
    }
}
