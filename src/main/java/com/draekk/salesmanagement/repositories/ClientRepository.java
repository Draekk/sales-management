package com.draekk.salesmanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.draekk.salesmanagement.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
