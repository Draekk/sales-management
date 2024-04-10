package com.draekk.salesmanagement.models.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto {

    private Long id;
    private String name;
    private String region;
    private List<SaleDto> sales;
}
