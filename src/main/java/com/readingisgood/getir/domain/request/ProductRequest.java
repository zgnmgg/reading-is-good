package com.readingisgood.getir.domain.request;

import com.readingisgood.getir.domain.validators.interfaces.Create;
import com.readingisgood.getir.domain.validators.interfaces.Update;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProductRequest {

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String name;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String author;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private Long stock;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private Long price;

    @NotNull(groups = { Create.class, Update.class })
    @NotBlank(groups = { Create.class, Update.class })
    private String publishYear;
}
