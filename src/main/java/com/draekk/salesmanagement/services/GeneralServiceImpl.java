package com.draekk.salesmanagement.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.draekk.salesmanagement.models.DtoManager;
import com.draekk.salesmanagement.models.dtos.ClientDto;
import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.models.dtos.SaleDto;
import com.draekk.salesmanagement.repositories.ClientRepository;
import com.draekk.salesmanagement.repositories.SaleRepository;

@Service
public class GeneralServiceImpl implements ClientService, SaleService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private DtoManager manager;

    @Override
    public SaleDto findSaleById(Long id) {
        return manager.createSaleDto(saleRepository.findById(id).orElse(null));
    }

    @Override
    public List<SaleDto> findSalesByDate(Map<String, Object> json) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(json.get("date").toString());
            return saleRepository.findByDate(date).stream().map(manager::createSaleDto).toList();
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public List<SaleDto> findSalesByMonth(Map<String, Integer> json) {
        try {
            int month = json.get("month");
            int year = json.get("year");

            return saleRepository.findSaleByMonth(month, year).stream().map(manager::createSaleDto).toList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<SaleDto> findSalesByYear(Map<String, Object> json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSalesByYear'");
    }

    @Override
    public List<SaleDto> findAllSales() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllSales'");
    }

    @Override
    public ResponseDto<ClientDto> findCliendById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCliendById'");
    }

    @Override
    public ResponseDto<List<ClientDto>> findClientsByName(Map<String, String> json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findClientsByName'");
    }

    @Override
    public ResponseDto<List<ClientDto>> findClientsByNameContaining(Map<String, String> json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findClientsByNameContaining'");
    }

    @Override
    public ResponseDto<List<ClientDto>> findClientsByRegion(Map<String, String> json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findClientsByRegion'");
    }

    @Override
    public ResponseDto<List<ClientDto>> findClientsByRegionContaining(Map<String, String> json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findClientsByRegionContaining'");
    }

    @Override
    public ResponseDto<List<ClientDto>> findAllClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllClients'");
    }

    @Override
    public ResponseDto<ClientDto> createClient(Map<String, Object> json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createClient'");
    }

    @Override
    public ResponseDto<ClientDto> updateClient(Map<String, Object> json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClient'");
    }

    @Override
    public ResponseDto<ClientDto> deleteClient(Map<String, Object> json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClient'");
    }


}
