package com.backend.domain.product.mapper;

import com.backend.domain.product.domain.Product;
import com.backend.domain.product.dto.ProResponseDto;
import com.backend.domain.product.dto.ProductPatchDto;
import com.backend.domain.product.dto.ProductPostDto;
import com.backend.domain.product.dto.ProductResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product productPostDtoToProduct(ProductPostDto productPostDto);

    Product productPatchDtoToProduct(ProductPatchDto productPatchDto);

    ProductResponseDto productToProductResponseDto(Product product);

    List<ProductResponseDto> productsToProductResponseDto(List<Product> products);

    ProResponseDto productToProResponseDto(Product product);
}
