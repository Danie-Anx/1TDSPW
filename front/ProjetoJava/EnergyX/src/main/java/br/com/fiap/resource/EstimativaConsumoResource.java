package br.com.fiap.resource;

import br.com.fiap.application.App;
import br.com.fiap.dto.request.EstimativaConsumoRequestDTO;
import br.com.fiap.dto.response.EstimativaConsumoResponseDTO;
import br.com.fiap.util.api.ResponseUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("usuarios/{idUsuario}/estimativas-consumo")
public class EstimativaConsumoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAll(@PathParam("idUsuario") String sidUsuario) {
        try {
            int idUsuario = Integer.parseInt(sidUsuario);
            List<EstimativaConsumoResponseDTO> dtos = App.estimativaConsumoService.obterEstimativasConsumo(idUsuario);
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("idUsuario") String sidUsuario, EstimativaConsumoRequestDTO dtoEntrada) {
        try {
            int idUsuario = Integer.parseInt(sidUsuario);
            EstimativaConsumoResponseDTO dtoSaida = App.estimativaConsumoService.criarEstimativaConsumo(idUsuario, dtoEntrada);
            return ResponseUtils.buildWithData(201, dtoSaida);
        }
        catch (NumberFormatException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(400, "ID deve ser um número");
        }
        catch (NotFoundException exception) {
            return ResponseUtils.buildWithSingleErrorMessage(404, exception.getMessage());
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
    @Path("/{idEstimativaConsumo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readById(@PathParam("idUsuario") String sidUsuario, @PathParam("idEstimativaConsumo") String sidEstimativaConsumo) {
        try {
            int idUsuario = Integer.parseInt(sidUsuario);
            int idEstimativaConsumo = Integer.parseInt(sidEstimativaConsumo);
            EstimativaConsumoResponseDTO dto = App.estimativaConsumoService.obterEstimativaConsumoPorId(idUsuario, idEstimativaConsumo);
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

    @PUT
    @Path("/{idEstimativaConsumo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idUsuario") String sidUsuario,
            @PathParam("idEstimativaConsumo") String sidEstimativaConsumo,
            EstimativaConsumoRequestDTO dtoEntrada
    ) {
        try {
            int idUsuario = Integer.parseInt(sidUsuario);
            int idEstimativaConsumo = Integer.parseInt(sidEstimativaConsumo);
            EstimativaConsumoResponseDTO dtoSaida = App.estimativaConsumoService.atualizarEstimativaConsumoPorId(idUsuario, idEstimativaConsumo, dtoEntrada);
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

    @DELETE
    @Path("/{idEstimativaConsumo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("idUsuario") String sidUsuario,
            @PathParam("idEstimativaConsumo") String sidEstimativaConsumo
    ) {
        try {
            int idUsuario = Integer.parseInt(sidUsuario);
            int idEstimativaConsumo = Integer.parseInt(sidEstimativaConsumo);
            App.estimativaConsumoService.deletarEstimativaConsumo(idUsuario, idEstimativaConsumo);
            return ResponseUtils.buildWithoutData(200);
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
