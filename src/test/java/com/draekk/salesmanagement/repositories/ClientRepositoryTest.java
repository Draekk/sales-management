package com.draekk.salesmanagement.repositories;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.h

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.draekk.salesmanagement.entities.Client;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repository;

    @Test
    void testFindByNameContaining() {
        List<Client> clients = repository.findByNameContaining("jav");
        
        assertEquals(1, clients.size());
    }

    @Test
    void testFindByName() {
        List<Client> clients = repository.findByName("elian");

        assertEquals(2, clients.size());
    }
}
