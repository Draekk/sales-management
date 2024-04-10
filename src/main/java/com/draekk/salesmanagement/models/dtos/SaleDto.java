package com.draekk.salesmanagement.models.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SaleDto {

    private Long id;
    private Integer boxAmount;
    private Date date;
}
