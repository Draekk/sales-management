package com.draekk.salesmanagement.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.draekk.salesmanagement.models.dtos.SaleDto;

@SpringBootTest
public class GeneralServiceImplTest {

    @Autowired
    private GeneralServiceImpl service;

    @Test
    void testCreateClient() {

    }

    @Test
    void testDeleteClient() {

    }

    @Test
    void testFindAllClients() {

    }

    @Test
    void testFindAllSales() {

    }

    @Test
    void testFindCliendById() {

    }

    @Test
    void testFindClientsByName() {

    }

    @Test
    void testFindClientsByNameContaining() {

    }

    @Test
    void testFindClientsByRegion() {

    }

    @Test
    void testFindClientsByRegionContaining() {

    }

    @Test
    void testFindSaleById() {
        SaleDto sale = service.findSaleById(2L);

        assertEquals(3, sale.getBoxAmount());
    }

    @Test
    void testFindSalesByDate() {
        Map<String, Object> json = new HashMap<>();
        json.put("date", "2024-04-09");
        List<SaleDto> sales = service.findSalesByDate(json);

        assertEquals(3, sales.size());
        assertEquals(5, sales.get(0).getBoxAmount());
        assertEquals("2024-04-09", sales.get(2).getDate().toString());
    }

    @Test
    void testFindSalesByMonth() {
        Map<String, Integer> json = new HashMap<>();
        json.put("month", 3);
        json.put("year", 2024);

        List<SaleDto> sales = service.findSalesByMonth(json);

        assertEquals(6, sales.get(0).getBoxAmount());
    }

    @Test
    void testFindSalesByYear() {

    }

    @Test
    void testUpdateClient() {

    }
}
