package com.draekk.salesmanagement.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {

    private String message;
    private int status;
    private boolean success;
    private T data;

    public ResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
