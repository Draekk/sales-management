package com.draekk.salesmanagement.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.draekk.salesmanagement.entities.Client;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    @Test
    void testFindByName() {
        List<Client> clients = repository.findByName("elian");

        assertEquals(2, clients.size());
    }

    @Test
    void testFindByNameContaining() {
        List<Client> clients = repository.findByNameContaining("jav");
        
        assertEquals(1, clients.size());
    }

    @Test
    void testFindByRegion() {
        List<Client> clients = repository.findByRegion("carabobo");

        assertEquals(2, clients.size());
    }
    
    @Test
    @Transactional(readOnly = true)
    void testFindByRegionContaining() {
        List<Client> clients = repository.findByRegionContaining("cara");

        assertEquals(2, clients.size());
    }

    @Test
    @Transactional(readOnly = true)
    void testFindById() {
        Optional<Client> optionalClient = repository.findById(1L);

        assertEquals(4, optionalClient.get().getSales().get(0).getBoxAmount());
    }
}
