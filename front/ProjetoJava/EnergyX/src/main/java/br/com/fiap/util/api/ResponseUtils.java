package br.com.fiap.util.api;
import jakarta.ws.rs.core.Response;

public class ResponseUtils {

    public static Response buildWithSingleErrorMessage(int status, String errorMessage) {
        return Response.status(status).entity(
                ResponseEnvelope.buildWithSingleErrorMessage(errorMessage)
        ).build();
    }

    public static Response buildWithData (int status, Object data) {
        return Response.status(status).entity(
                ResponseEnvelope.buildWithData(data)
        ).build();
    }

    public static Response buildWithoutData (int status) {
        return Response.status(status).entity(
                new ResponseEnvelope()
        ).build();
    }
}
