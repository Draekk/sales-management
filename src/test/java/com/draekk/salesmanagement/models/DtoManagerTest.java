package com.draekk.salesmanagement.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.draekk.salesmanagement.entities.Client;
import com.draekk.salesmanagement.models.dtos.ClientDto;
import com.draekk.salesmanagement.repositories.ClientRepository;

@SpringBootTest
public class DtoManagerTest {

    @Autowired
    private DtoManager manager;

    @Autowired
    private ClientRepository repository;

    @Test
    @Transactional(readOnly = true)
    void testCreateDto() {

        Optional<Client> clientOptional = repository.findById(1L);

        ClientDto clientDto = manager.createClientDto(clientOptional.orElseThrow());

        assertEquals("Gever", clientDto.getName());
        assertEquals(4, clientDto.getSales().get(0).getBoxAmount());
    }
}
