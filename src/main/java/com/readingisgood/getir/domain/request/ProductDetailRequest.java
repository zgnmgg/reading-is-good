package com.readingisgood.getir.domain.request;

import com.readingisgood.getir.domain.validators.interfaces.Create;
import com.readingisgood.getir.domain.validators.interfaces.QuantityValidator;
import com.readingisgood.getir.domain.validators.interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDetailRequest {

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private Long id;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    @QuantityValidator(groups = { Create.class, Update.class })
    private Integer quantity;
}
