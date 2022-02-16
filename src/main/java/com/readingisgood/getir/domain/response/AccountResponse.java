package com.readingisgood.getir.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {
    private String email;
    private String name;
}
