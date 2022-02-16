package com.readingisgood.getir.domain.request;

import com.readingisgood.getir.domain.validators.interfaces.Create;
import com.readingisgood.getir.domain.validators.interfaces.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderRequest {

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private Long customerId;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private List<ProductDetailRequest> productOrderDetails;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private BigDecimal orderAmount;
}
