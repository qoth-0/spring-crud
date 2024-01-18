package com.example.crud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseEntityController {
//    에러 발생 시
    public static ResponseEntity<Map<String, Object>> failed(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(status.value()));
        body.put("status message", status.getReasonPhrase());
        body.put("error message", message);
        return new ResponseEntity<>(body, status);
    }

//    정상 작동
    public static ResponseEntity<Map<String, Object>> success(HttpStatus status, Object object) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(status.value()));
        body.put("data", object);
        return new ResponseEntity<>(body, status);
    }
}
