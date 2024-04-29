package com.draekk.salesmanagement.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.services.SaleService;

@RestController
@RequestMapping("/api/sale")
public class SaleRestController {

    @Autowired
    private SaleService service;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<ResponseDto<?>> findSaleById(@PathVariable Long id) {
        ResponseDto<?> response = service.findSaleById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/find/date")
    public ResponseEntity<ResponseDto<?>> findSalesByDate(@RequestBody Map<String, Object> json) {
        ResponseDto<?> response = service.findSalesByDate(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/find/month")
    public ResponseEntity<ResponseDto<?>> findSalesByMonth(@RequestBody Map<String, Integer> json) {
        ResponseDto<?> response = service.findSalesByMonth(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @GetMapping("/find/year/{year}")
    public ResponseEntity<ResponseDto<?>> findSalesByYear(@PathVariable Integer year) {
        ResponseDto<?> response = service.findSalesByYear(year);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @GetMapping("/find/all")
    public ResponseEntity<ResponseDto<?>> findAllSales() {
        ResponseDto<?> response = service.findAllSales();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<?>> createSale(@RequestBody Map<String, Object> json) {
        ResponseDto<?> response = service.createSale(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto<?>> updateSale(@RequestBody Map<String, Object> json) {
        ResponseDto<?> response = service.updateSale(json);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
