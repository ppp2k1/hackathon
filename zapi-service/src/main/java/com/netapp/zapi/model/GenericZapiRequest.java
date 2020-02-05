package com.netapp.zapi.model;

import java.util.List;
import java.util.Map;

public class GenericZapiRequest {

    private String zapiRequest;

    private String zapiObject;

    private List<String> desiredParameters;

    private Map<String, Object> queryParams;

    private Map<String, Object> inputParams;

    public String getZapiRequest() {
        return zapiRequest;
    }

    public void setZapiRequest(String zapiRequest) {
        this.zapiRequest = zapiRequest;
    }

    public String getZapiObject() {
        return zapiObject;
    }

    public void setZapiObject(String zapiObject) {
        this.zapiObject = zapiObject;
    }

    public List<String> getDesiredParameters() {
        return desiredParameters;
    }

    public void setDesiredParameters(List<String> desiredParameters) {
        this.desiredParameters = desiredParameters;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, Object> getInputParams() {
        return inputParams;
    }

    public void setInputParams(Map<String, Object> inputParams) {
        this.inputParams = inputParams;
    }
}
