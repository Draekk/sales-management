package com.draekk.salesmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.draekk.salesmanagement.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findByName(String name);

    @Query("select c from Client c where c.name like %?1%")
    List<Client> findByNameContaining(String name);

    List<Client> findByRegion(String region);

    @Query("select c from Client c where c.region like %?1%")
    List<Client> findByRegionContaining(String region);

}
