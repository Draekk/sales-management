package com.draekk.salesmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.draekk.salesmanagement.entities.Sale;
import java.util.List;
import java.util.Date;


public interface SaleRepository extends CrudRepository<Sale, Long> {

    List<Sale> findByDate(Date date);

    @Query("select s from Sale s where month(s.date) = ?1 and year(s.date) = ?2")
    List<Sale> findSalesByMonth(int month, int year);

    @Query("select s from Sale s where year(s.date) = ?1")
    List<Sale> findSalesByYear(int year);
}
