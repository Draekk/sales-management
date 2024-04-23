package com.draekk.salesmanagement.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.services.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

    @Autowired
    ClientService service;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<ResponseDto<?>> findClientById(@PathVariable Long id) {
        ResponseDto<?> response = service.findCliendById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/find/name")
    public ResponseEntity<ResponseDto<?>> findClientsByName(@RequestBody Map<String, String> json) {
        ResponseDto<?> response = service.findClientsByNameContaining(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @GetMapping("/find/region")
    public ResponseEntity<ResponseDto<?>> findClientsByRegion(@RequestBody Map<String, String> json) {
        ResponseDto<?> response = service.findClientsByRegionContaining(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/find/all")
    public ResponseEntity<ResponseDto<?>> findAllClients() {
        ResponseDto<?> response = service.findAllClients();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<?>> createClient(@RequestBody Map<String, String> json) {
        ResponseDto<?> response = service.createClient(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto<?>> updateClient(@RequestBody Map<String, Object> json) {
        ResponseDto<?> response = service.updateClient(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<ResponseDto<?>> deleteClientById(@PathVariable Long id) {
        ResponseDto<?> response = service.deleteClientById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
