package com.draekk.salesmanagement.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.draekk.salesmanagement.models.ResponseMessage;
import com.draekk.salesmanagement.models.dtos.ClientDto;
import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.models.dtos.SaleDto;

@SpringBootTest
public class GeneralServiceImplTest {

    @Autowired
    private GeneralServiceImpl service;

    @Test
    void testCreateClient() {
        Map<String, Object> json = new HashMap<>();
        json.put("name", "jose");
        json.put("region", "valencia");

        ResponseDto<ClientDto> response = service.createClient(json);

        assertEquals("valencia", response.getData().getRegion());
    }

    @Test
    void testDeleteClient() {
        ResponseDto<ClientDto> response = service.deleteClientById(7L);

        assertEquals(ResponseMessage.DELETED.getMessage(), response.getMessage());
    }

    @Test
    void testFindAllClients() {
        ResponseDto<List<ClientDto>> response = service.findAllClients();

        assertTrue(!response.getData().isEmpty());
    }

    @Test
    void testFindAllSales() {
        ResponseDto<List<SaleDto>> response = service.findAllSales();

        assertTrue(response.getData().size() > 0);
    }

    @Test
    void testFindCliendById() {
        ResponseDto<ClientDto> response = service.findCliendById(3L);

        assertEquals(3, response.getData().getId());
    }

    @Test
    void testFindClientsByName() {
        ResponseDto<List<ClientDto>> response = service.findClientsByName(Collections.singletonMap("name", "elian"));

        assertTrue(!response.getData().isEmpty());
    }

    @Test
    void testFindClientsByNameContaining() {
        ResponseDto<List<ClientDto>> response = service.findClientsByNameContaining(Collections.singletonMap("name", "eli"));
        
        assertEquals(2, response.getData().size());
    }
    
    @Test
    void testFindClientsByRegion() {
        ResponseDto<List<ClientDto>> response = service.findClientsByRegion(Collections.singletonMap("region", "carabobo"));
        
        assertEquals(2, response.getData().size());
        
    }
    
    @Test
    void testFindClientsByRegionContaining() {
        ResponseDto<List<ClientDto>> response = service.findClientsByRegionContaining(Collections.singletonMap("region", "rabo"));
        
        assertEquals(2, response.getData().size());

    }

    @Test
    void testFindSaleById() {
        ResponseDto<SaleDto> response = service.findSaleById(2L);

        assertEquals(2, response.getData().getId());
    }

    @Test
    void testFindSalesByDate() {
        Map<String, Object> json = new HashMap<>();
        json.put("date", "2024-04-09");
        ResponseDto<List<SaleDto>> response = service.findSalesByDate(json);

        assertTrue(response.getData().size() > 0);
    }

    @Test
    void testFindSalesByMonth() {
        Map<String, Integer> json = new HashMap<>();
        json.put("month", 3);
        json.put("year", 2024);

        ResponseDto<List<SaleDto>> response = service.findSalesByMonth(json);

        assertTrue(!response.getData().isEmpty());
    }

    @Test
    void testFindSalesByYear() {
        ResponseDto<List<SaleDto>> response = service.findSalesByYear(2024);

        assertTrue(!response.getData().isEmpty());
    }

    @Test
    void testUpdateClient() {
        Map<String, Object> json = new HashMap<>();
        json.put("id", 1L);
        json.put("name", "nico");
        json.put("region", "curacavi");

        ResponseDto<ClientDto> response = service.updateClient(json);
        assertEquals("nico", response.getData().getName());
    }

    @Test
    void testUpdateSale() {
        Map<String, Object> json = new HashMap<>();
        json.put("id", 2);
        json.put("box_amount", 4);
        json.put("date", "2024-04-17");

        ResponseDto<SaleDto> response = service.updateSale(json);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        assertEquals("2024-04-17", format.format(response.getData().getDate()));
    }

    @Test
    void testDeleteSaleById() {
        ResponseDto<SaleDto> response = service.deleteSaleById(5L);

        assertEquals(ResponseMessage.DELETED.getMessage(), response.getMessage());
    }

    @Test
    void testCreateSale() {
        Map<String, Object> json = new HashMap<>();
        json.put("box_amount", 3);
        json.put("date", "2024-04-16");
        json.put("client_id", 3);

        ResponseDto<SaleDto> response = service.createSale(json);
        assertEquals(3, response.getData().getBoxAmount());
    }
}
