package com.fdograph.demo.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public class ApiClientException extends RestClientException {
  @Getter
  private ResponseEntity<?> response;

  @Getter
  private HttpStatus statusCode;

  ApiClientException() {
    super("");
  }

  ApiClientException(ResponseEntity<?> response) {
    super("Error in API call. Status:" + response.getStatusCode());
    this.response = response;
    this.statusCode = response.getStatusCode();
  }
}
