package com.draekk.salesmanagement.services;

import java.util.List;
import java.util.Map;

import com.draekk.salesmanagement.models.dtos.ClientDto;
import com.draekk.salesmanagement.models.dtos.ResponseDto;

public interface ClientService {

    ResponseDto<ClientDto> findCliendById(Long id);
    ResponseDto<List<ClientDto>> findClientsByNameContaining(Map<String, String> json);
    ResponseDto<List<ClientDto>> findClientsByRegion(Map<String, String> json);
    ResponseDto<List<ClientDto>> findClientsByRegionContaining(Map<String, String> json);
    ResponseDto<List<ClientDto>> findAllClients();
    ResponseDto<ClientDto> createClient(Map<String, Object> json);
    ResponseDto<ClientDto> updateClient(Map<String, Object> json);
    ResponseDto<ClientDto> deleteClientById(Long id);
}
