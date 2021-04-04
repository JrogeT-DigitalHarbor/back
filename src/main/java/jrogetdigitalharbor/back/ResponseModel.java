package jrogetdigitalharbor.back;

public class ResponseModel {

    public String message;
    public Object body;

    public ResponseModel(String message, Object body) {
        this.message = message;
        this.body = body;
    }

    public ResponseModel(String message) {
        this.message = message;
        this.body = null;
    }
}
