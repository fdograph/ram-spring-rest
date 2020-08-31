package com.fdograph.demo.controllers;

import org.springframework.web.client.RestClientException;

public class ApiException extends RuntimeException {
  public ApiException(RestClientException ex) {
    super(ex.getLocalizedMessage());
  }
}
