package com.readingisgood.getir.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String author;
    private String publishYear;
    private Long stock;
    private Long price;
}
