package com.fdograph.demo.controllers;

import com.fdograph.demo.api.ApiClientException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApiControllerAdvice {
  @ResponseBody
  @ExceptionHandler(ApiClientException.class)
  String employeeNotFoundHandler(HttpServletResponse response, ApiClientException ex) {
    response.setStatus(ex.getStatusCode().value());
    return ex.getMessage();
  }
}
