package com.backend.domain.product.api;

import com.backend.domain.product.application.ProductService;
import com.backend.domain.product.dto.ProductPatchDto;
import com.backend.domain.product.dto.ProductPostDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Long> create(@Valid @RequestBody ProductPostDto productPostDto){
        return ResponseEntity.ok(productService.create(productPostDto));
    }

    @PatchMapping("/products/{productsId}")
    public ResponseEntity<Long> update(@PathVariable Long productsId,
                                       @Valid @RequestBody ProductPatchDto productPatchDto){
        return ResponseEntity.ok(productService.update(productsId,productPatchDto));
    }

    @DeleteMapping("/products/{productsId}")
    public ResponseEntity<Long> delete(@PathVariable Long productsId){

        return ResponseEntity.ok(productService.delete(productsId));
    }

}