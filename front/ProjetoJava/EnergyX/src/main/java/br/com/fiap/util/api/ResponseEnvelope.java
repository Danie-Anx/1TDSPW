package br.com.fiap.util.api;

import jakarta.json.bind.annotation.JsonbProperty;

import java.util.ArrayList;
import java.util.List;

public class ResponseEnvelope {
    @JsonbProperty(nillable = true)
    private Object data;
    private List<String> errors;
    private List<String> warnings;

    public ResponseEnvelope() {
        this.data = null;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public static ResponseEnvelope buildWithSingleErrorMessage (String errorMessage) {
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.errors.add(errorMessage);
        return responseEnvelope;
    }

    public static ResponseEnvelope buildWithData (Object data) {
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setData(data);
        return responseEnvelope;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}
