package com.draekk.salesmanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.draekk.salesmanagement.entities.Sale;

public interface SaleRepository extends CrudRepository<Sale, Long> {

}
