package com.readingisgood.getir.domain.request;

import com.readingisgood.getir.domain.validators.interfaces.Create;
import com.readingisgood.getir.domain.validators.interfaces.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderIntervalRequest {

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String startDate;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String endDate;
}
