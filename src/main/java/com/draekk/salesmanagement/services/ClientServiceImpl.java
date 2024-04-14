package com.draekk.salesmanagement.services;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.draekk.salesmanagement.entities.Client;
import com.draekk.salesmanagement.models.DtoManager;
import com.draekk.salesmanagement.models.ResponseMessage;
import com.draekk.salesmanagement.models.dtos.ClientDto;
import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DtoManager manager;

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
    public ResponseDto<List<ClientDto>> findClientsByNameContaining(Map<String, String> json) {
        try {
            String name = json.get("name");
            ResponseDto<List<ClientDto>> response = new ResponseDto<>();
            response.setMessage(ResponseMessage.FOUND.getMessage());
            response.setStatus(HttpStatus.OK.value());
            response.setSuccess(true);
            response.setData(clientRepository.findByNameContaining(name).stream().map(manager::createClientDto).toList());
            
            if(response.getData().isEmpty()){
                response.setMessage(ResponseMessage.NOT_FOUND.getMessage());
            }
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
