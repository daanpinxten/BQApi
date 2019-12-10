package com.damiwawo.BoektQuizt.model.helpers;

public class ServerResponse {

    private int statusCode;
    private String response;
    private String info;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ServerResponse(int statusCode, String response, String info) {
        this.statusCode = statusCode;
        this.response = response;
        this.info = info;
    }

    public static ServerResponse notFound(String info) {
        if (info == "") info = "none";
        return new ServerResponse(404, "The server returned a 'Not Found' error code.", info);
    }

    public static ServerResponse badRequest(String info) {
        if (info == "") info = "none";
        return new ServerResponse(400, "The server returned a 'Bad Request' error code.", info);
    }

    public static ServerResponse unauthorized(String info) {
        if (info == "") info = "none";
        return new ServerResponse(401, "The server returned a 'Unauthorized' error code.", info);
    }
    public static ServerResponse ok(String info) {
        if (info == "") info = "none";
        return new ServerResponse(200, "Your request has been successfully processed.", info);
    }
}
