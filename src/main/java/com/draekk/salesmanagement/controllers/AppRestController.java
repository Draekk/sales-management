package com.draekk.salesmanagement.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.services.GeneralServiceImpl;

@RestController
@RequestMapping("/api")
public class AppRestController {

    @Autowired
    GeneralServiceImpl service;

    // #region Clients

    @GetMapping("/client/find/{id}")
    public ResponseEntity<ResponseDto<?>> findClientById(@PathVariable Long id) {
        try {
            ResponseDto<?> response = service.findCliendById(id);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/client/find/name")
    public ResponseEntity<ResponseDto<?>> findClientsByName(@RequestBody Map<String, String> json) {
        ResponseDto<?> response = service.findClientsByNameContaining(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    // #endregion
}
