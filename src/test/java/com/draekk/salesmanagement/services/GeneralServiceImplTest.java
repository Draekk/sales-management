package com.draekk.salesmanagement.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        json.put("name", "ubaldo");
        json.put("region", "bejuma");

        ResponseDto<ClientDto> response = service.createClient(json);

        assertEquals(6, response.getData().getId());
    }

    @Test
    void testDeleteClient() {
        ResponseDto<ClientDto> response = service.deleteClientById(2L);

        assertEquals(ResponseMessage.DELETED.getMessage(), response.getMessage());
    }

    @Test
    void testFindAllClients() {
        ResponseDto<List<ClientDto>> response = service.findAllClients();

        assertEquals(4, response.getData().size());
    }

    @Test
    void testFindAllSales() {
        List<SaleDto> sales = service.findAllSales();

        assertEquals(8, sales.size());
    }

    @Test
    void testFindCliendById() {
        ResponseDto<ClientDto> response = service.findCliendById(3L);

        assertEquals(3, response.getData().getId());
        assertEquals("gever", response.getData().getName());
    }

    @Test
    void testFindClientsByName() {
        ResponseDto<List<ClientDto>> response = service.findClientsByName(Collections.singletonMap("name", "elian"));

        assertEquals(2, response.getData().size());
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
        List<SaleDto> sales = service.findSalesByYear(2023);

        assertEquals(7, sales.get(0).getId());
    }

    @Test
    void testUpdateClient() {
        Map<String, Object> json = new HashMap<>();
        json.put("id", 1L);
        json.put("name", "nicolas");
        json.put("region", "curacavi");

        ResponseDto<ClientDto> response = service.updateClient(json);
        assertEquals("nicolas", response.getData().getName());
    }

    @Test
    void testUpdateSale() {
        Map<String, Object> json = new HashMap<>();
        json.put("id", 2);
        json.put("box_amount", 4);
        json.put("date", "2024-04-12");

        ResponseDto<SaleDto> response = service.updateSale(json);

        assertEquals(4, response.getData().getBoxAmount());
    }

    @Test
    void testDeleteSaleById() {
        ResponseDto<SaleDto> response = service.deleteSaleById(4L);

        assertEquals(ResponseMessage.DELETED.getMessage(), response.getMessage());
    }
}
