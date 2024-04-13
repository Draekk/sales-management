package com.draekk.salesmanagement.models;

import org.springframework.http.HttpStatus;

import com.draekk.salesmanagement.entities.Client;
import com.draekk.salesmanagement.entities.Sale;
import com.draekk.salesmanagement.models.dtos.ClientDto;
import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.models.dtos.SaleDto;

public interface DtoManager {

    default SaleDto createSaleDto(Sale sale) {
        SaleDto saleDto = new SaleDto();
        saleDto.setId(sale.getId());
        saleDto.setBoxAmount(sale.getBoxAmount());
        saleDto.setDate(sale.getDate());
        return saleDto;
    }

    default ClientDto createClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setRegion(client.getRegion());
        clientDto.setSales(client.getSales().stream().map(this::createSaleDto).toList());
        return clientDto;
    }

    default void createFoundResponse(ResponseDto<?> response) {
        response.setMessage(ResponseMessage.FOUND.getMessage());
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
    }
    
    default void createNotFoundResponse(ResponseDto<?> response) {
        response.setMessage(ResponseMessage.NOT_FOUND.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
