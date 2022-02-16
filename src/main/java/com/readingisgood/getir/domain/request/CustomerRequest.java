package com.readingisgood.getir.domain.request;

import com.readingisgood.getir.domain.validators.interfaces.Create;
import com.readingisgood.getir.domain.validators.interfaces.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CustomerRequest {
    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String email;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String firstName;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String lastName;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String address;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String phone;
}
