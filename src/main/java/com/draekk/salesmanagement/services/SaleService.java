package com.draekk.salesmanagement.services;

import java.util.List;
import java.util.Map;

import com.draekk.salesmanagement.models.dtos.ResponseDto;
import com.draekk.salesmanagement.models.dtos.SaleDto;

public interface SaleService {

    ResponseDto<SaleDto> findSaleById(Long id);
    ResponseDto<List<SaleDto>> findSalesByDate(Map<String, Object> json);
    ResponseDto<List<SaleDto>> findSalesByMonth(Map<String, Integer> json);
    ResponseDto<List<SaleDto>> findSalesByYear(Integer year);
    ResponseDto<List<SaleDto>> findAllSales();
    ResponseDto<SaleDto> createSale(Map<String, Object> json);
    ResponseDto<SaleDto> updateSale(Map<String, Object> json);
    ResponseDto<SaleDto> deleteSaleById(Long id);
}
