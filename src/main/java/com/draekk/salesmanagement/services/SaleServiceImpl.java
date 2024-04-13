package com.draekk.salesmanagement.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.draekk.salesmanagement.entities.Client;
import com.draekk.salesmanagement.entities.Sale;
import com.draekk.salesmanagement.models.DtoManager;
import com.draekk.salesmanagement.models.ResponseMessage;
import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.models.dtos.SaleDto;
import com.draekk.salesmanagement.repositories.ClientRepository;
import com.draekk.salesmanagement.repositories.SaleRepository;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DtoManager manager;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<SaleDto> findSaleById(Long id) {
        try {
            ResponseDto<SaleDto> response = new ResponseDto<>();
            manager.createFoundResponse(response);
            response.setData(manager.createSaleDto(saleRepository.findById(id).orElseThrow()));
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<SaleDto>> findSalesByDate(Map<String, Object> json) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(json.get("date").toString());
            ResponseDto<List<SaleDto>> response = new ResponseDto<>();
            manager.createFoundResponse(response);
            response.setData(saleRepository.findByDate(date).stream().map(manager::createSaleDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<SaleDto>> findSalesByMonth(Map<String, Integer> json) {
        try {
            ResponseDto<List<SaleDto>> response = new ResponseDto<>();
            int month = json.get("month");
            int year = json.get("year");

            manager.createFoundResponse(response);
            response.setData(saleRepository.findSalesByMonth(month, year).stream().map(manager::createSaleDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<SaleDto>> findSalesByYear(Integer year) {
        try {
            ResponseDto<List<SaleDto>> response = new ResponseDto<>();
            manager.createFoundResponse(response);
            response.setData(saleRepository.findSalesByYear(year).stream().map(manager::createSaleDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<SaleDto>> findAllSales() {
        try {
            List<Sale> sales = (List<Sale>) saleRepository.findAll();
            
            ResponseDto<List<SaleDto>> response = new ResponseDto<>();
            manager.createFoundResponse(response);
            response.setData(sales.stream().map(manager::createSaleDto).toList());
            return response;
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    @Transactional
    public ResponseDto<SaleDto> createSale(Map<String, Object> json) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ResponseDto<SaleDto> response = new ResponseDto<>();

            int boxAmount = Integer.parseInt(json.get("box_amount").toString());
            Date date = format.parse(json.get("date").toString());
            Long cliendId = Long.parseLong(json.get("client_id").toString());

            Optional<Client> clientOptional = clientRepository.findById(cliendId);
            if(clientOptional.isPresent()) {
                Sale newSale = new Sale(null, boxAmount, date, clientOptional.get());
                response.setData(manager.createSaleDto(saleRepository.save(newSale)));
                response.setMessage(ResponseMessage.CREATED.getMessage());
                response.setStatus(HttpStatus.OK.value());
                response.setSuccess(true);
                return response;
            } else {
                return new ResponseDto<>(ResponseMessage.NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            return new ResponseDto<>(ResponseMessage.NOT_CREATED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
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
}
