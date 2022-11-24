package de.bermuda.test;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface MyPasswordApi {

    ResponseEntity<List<String>> checkPassword(
           String body
    );

}
