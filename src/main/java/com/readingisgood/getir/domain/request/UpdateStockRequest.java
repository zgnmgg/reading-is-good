package com.readingisgood.getir.domain.request;

import com.readingisgood.getir.domain.validators.interfaces.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateStockRequest {

    @NotNull(groups = { Create.class})
    @NotBlank(groups = { Create.class})
    private Long stock;
}
