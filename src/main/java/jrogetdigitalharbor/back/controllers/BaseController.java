package jrogetdigitalharbor.back.controllers;

import jrogetdigitalharbor.back.ResponseModel;

public class BaseController {

    protected ResponseModel sendResponse(String message, Object body) {
        return new ResponseModel(message, body);
    }

    protected ResponseModel sendResponse(String message) {
        return new ResponseModel(message);
    }

    protected ResponseModel sendErrorResponse(Exception e) {
        return this.sendResponse(e.getMessage() + ":" + e.getClass());
    }
}
