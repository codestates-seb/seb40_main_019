package com.backend.domain.product.api;

import com.backend.domain.product.application.ProductService;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.dto.ProductPatchDto;
import com.backend.domain.product.dto.ProductPostDto;
import com.backend.domain.product.mapper.ProductMapper;
import com.backend.domain.user.domain.AuthUser;
import com.backend.global.annotation.CurrentMember;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.Response.SingleResponseDto;
import com.backend.global.dto.request.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping("/products")
    public ResponseEntity create(@CurrentMember AuthUser authUser,
            @Valid @RequestBody ProductPostDto productPostDto){
        Product product = productMapper.productPostDtoToProduct(productPostDto);
        Long userId = authUser.getUserId();

        Product response = productService.create(userId, product);

        return ResponseEntity.ok(productMapper.ProductToProductResponseDto(product));
    }

    @PatchMapping("/products/{productsId}")
    public ResponseEntity update(@PathVariable Long productsId,
                                       @Valid @RequestBody ProductPatchDto productPatchDto){
        Product product = productMapper.productPatchDtoToProduct(productPatchDto);
        Product response = productService.update(productsId, product);

        return ResponseEntity.ok(new SingleResponseDto<>(productMapper.ProductToProductResponseDto(response)));
    }

    @DeleteMapping("/products/{productsId}")
    public ResponseEntity<Long> delete(@PathVariable Long productsId){

        return ResponseEntity.ok(productService.delete(productsId));
    }

    @GetMapping("/products")
    public ResponseEntity<MultiResponse> getList(@RequestParam int page){
        int size= 15;
        Page<Product> pageProduct = productService.getList(page, size);
        List<Product> response = pageProduct.getContent();

        return ResponseEntity.ok(new MultiResponse<>(productMapper.productsToProductResponseDto(response),pageProduct));
    }
}