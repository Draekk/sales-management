package com.draekk.salesmanagement.services;

import java.util.List;
import java.util.Map;

import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.models.dtos.SaleDto;

public interface SaleService {

    SaleDto findSaleById(Long id);
    List<SaleDto> findSalesByDate(Map<String, Object> json);
    List<SaleDto> findSalesByMonth(Map<String, Integer> json);
    List<SaleDto> findSalesByYear(Integer year);
    List<SaleDto> findAllSales();
    ResponseDto<SaleDto> createSale(Map<String, Object> json);
    ResponseDto<SaleDto> updateSale(Map<String, Object> json);
    ResponseDto<SaleDto> deleteSaleById(Long id);
}
