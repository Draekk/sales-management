package com.draekk.salesmanagement.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.models.dtos.SaleDto;

@SpringBootTest
public class SaleServiceImplTest {

    @Autowired
    private SaleService service;

    @Test
    @Transactional
    void testCreateSale() {
        Map<String, Object> json = new HashMap<>();
        json.put("client_id", 7);
        json.put("box_amount", 3);
        json.put("date", "2024-04-27");

        ResponseDto<SaleDto> response = service.createSale(json);
        assertEquals("2024-04-27", response.getData().getDate());
    }

    @Test
    void testDeleteSaleById() {

    }

    @Test
    void testFindAllSales() {

    }

    @Test
    void testFindSaleById() {

    }

    @Test
    void testFindSalesByDate() {

    }

    @Test
    void testFindSalesByMonth() {

    }

    @Test
    void testFindSalesByYear() {

    }

    @Test
    void testUpdateSale() {

    }
}
