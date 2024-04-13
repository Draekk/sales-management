package com.draekk.salesmanagement.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.draekk.salesmanagement.entities.Client;
import com.draekk.salesmanagement.entities.Sale;
import com.draekk.salesmanagement.models.DtoManager;
import com.draekk.salesmanagement.models.ResponseMessage;
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
    @Transactional(readOnly = true)
    public SaleDto findSaleById(Long id) {
        return manager.createSaleDto(saleRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<SaleDto> findSalesByMonth(Map<String, Integer> json) {
        try {
            int month = json.get("month");
            int year = json.get("year");

            return saleRepository.findSalesByMonth(month, year).stream().map(manager::createSaleDto).toList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDto> findSalesByYear(Integer year) {
        try {
            return saleRepository.findSalesByYear(year).stream().map(manager::createSaleDto).toList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDto> findAllSales() {
        try {
            List<Sale> sales = (List<Sale>) saleRepository.findAll();
            return sales.stream().map(manager::createSaleDto).toList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public ResponseDto<SaleDto> updateSale(Map<String, Object> json) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ResponseDto<SaleDto> response = new ResponseDto<>();

            Long id = Long.parseLong(json.get("id").toString());
            Integer boxAmount = Integer.parseInt(json.get("box_amount").toString());
            Date date = format.parse(json.get("date").toString());

            Optional<Sale> saleOptional = saleRepository.findById(id);
            if(saleOptional.isPresent()) {
                saleOptional.get().setBoxAmount(boxAmount);
                saleOptional.get().setDate(date);
                response.setData(manager.createSaleDto(saleRepository.save(saleOptional.get())));
                response.setMessage(ResponseMessage.UPDATED.getMessage());
                response.setStatus(HttpStatus.ACCEPTED.value());
                response.setSuccess(true);
                return response;
            } else {
                return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_DELETED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional
    public ResponseDto<SaleDto> deleteSaleById(Long id) {
        try {
            ResponseDto<SaleDto> response = new ResponseDto<>();

            Optional<Sale> saleOptional = saleRepository.findById(id);
            if(saleOptional.isPresent()) {
                saleRepository.deleteById(id);
                response.setMessage(ResponseMessage.DELETED.getMessage());
                response.setStatus(HttpStatus.ACCEPTED.value());
                response.setSuccess(true);
                return response;
            } else {
                return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_DELETED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<ClientDto> findCliendById(Long id) {
        try {
            ResponseDto<ClientDto> response = new ResponseDto<>();
            response.setMessage(ResponseMessage.FOUND.getMessage());
            response.setStatus(HttpStatus.OK.value());
            response.setSuccess(true);
            response.setData(manager.createClientDto(clientRepository.findById(id).orElseThrow()));
            
            return response;
        } catch (NoSuchElementException e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<ClientDto>> findClientsByName(Map<String, String> json) {
        try {
            String name = json.get("name");
            ResponseDto<List<ClientDto>> response = new ResponseDto<>();
            response.setMessage(ResponseMessage.FOUND.getMessage());
            response.setStatus(HttpStatus.OK.value());
            response.setSuccess(true);;
            response.setData(clientRepository.findByName(name).stream().map(manager::createClientDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<ClientDto>> findClientsByNameContaining(Map<String, String> json) {
        try {
            String name = json.get("name");
            ResponseDto<List<ClientDto>> response = new ResponseDto<>();
            response.setMessage(ResponseMessage.FOUND.getMessage());
            response.setStatus(HttpStatus.OK.value());
            response.setSuccess(true);
            response.setData(clientRepository.findByNameContaining(name).stream().map(manager::createClientDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<ClientDto>> findClientsByRegion(Map<String, String> json) {
        try {
            String region = json.get("region");
            ResponseDto<List<ClientDto>> response = new ResponseDto<>();
            response.setMessage(ResponseMessage.FOUND.getMessage());
            response.setStatus(HttpStatus.OK.value());
            response.setSuccess(true);
            response.setData(clientRepository.findByRegion(region).stream().map(manager::createClientDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<ClientDto>> findClientsByRegionContaining(Map<String, String> json) {
        try {
            String region = json.get("region");
            ResponseDto<List<ClientDto>> response = new ResponseDto<>();
            response.setMessage(ResponseMessage.FOUND.getMessage());
            response.setStatus(HttpStatus.OK.value());
            response.setSuccess(true);
            response.setData(clientRepository.findByRegionContaining(region).stream().map(manager::createClientDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<ClientDto>> findAllClients() {
        try {
            List<Client> clients = (List<Client>)clientRepository.findAll();
            ResponseDto<List<ClientDto>> response = new ResponseDto<>();
            response.setMessage(ResponseMessage.FOUND.getMessage());
            response.setStatus(HttpStatus.OK.value());
            response.setSuccess(true);
            response.setData(clients.stream().map(manager::createClientDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional
    public ResponseDto<ClientDto> createClient(Map<String, Object> json) {
        try {
            Client client = new Client(json.get("name").toString(), json.get("region").toString());
            ResponseDto<ClientDto> response = new ResponseDto<>();
            response.setMessage(ResponseMessage.CREATED.getMessage());
            response.setStatus(HttpStatus.CREATED.value());
            response.setSuccess(true);
            response.setData(manager.createClientDto(clientRepository.save(client)));
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_CREATED.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    @Transactional
    public ResponseDto<ClientDto> updateClient(Map<String, Object> json) {
        try {
            Long id = Long.parseLong(json.get("id").toString());
            String name = json.get("name").toString();
            String region = json.get("region").toString();

            Optional<Client> clientOptional = clientRepository.findById(id);
            if(clientOptional.isPresent()) {
                clientOptional.get().setName(name);
                clientOptional.get().setRegion(region);

                ResponseDto<ClientDto> response = new ResponseDto<>();
                response.setData(manager.createClientDto(clientRepository.save(clientOptional.get())));
                response.setMessage(ResponseMessage.UPDATED.getMessage());
                response.setStatus(HttpStatus.OK.value());
                response.setSuccess(true);
                return response;
            } else {
                return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND.value());
            }

        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_UPDATED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional
    public ResponseDto<ClientDto> deleteClientById(Long id) {
        try {
            ResponseDto<ClientDto> response = new ResponseDto<>();
            if(clientRepository.findById(id).isPresent()) {
                clientRepository.deleteById(id);
                response.setMessage(ResponseMessage.DELETED.getMessage());
                response.setStatus(HttpStatus.ACCEPTED.value());
                response.setSuccess(true);
            } else {
                response.setMessage(ResponseMessage.NOT_FOUND.getMessage());
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_DELETED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    
}
