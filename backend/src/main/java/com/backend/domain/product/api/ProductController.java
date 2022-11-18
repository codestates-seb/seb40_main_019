package com.backend.domain.product.api;

import com.backend.domain.product.application.ProductService;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.dto.ProResponseDto;
import com.backend.domain.product.dto.ProductPatchDto;
import com.backend.domain.product.dto.ProductPostDto;
import com.backend.domain.product.mapper.ProductMapper;
import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.dto.response.MultiResponse;
import com.backend.global.dto.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity create(@CurrentUser CustomUserDetails authUser,
            @Valid @RequestBody ProductPostDto productPostDto){
        Product product = productMapper.productPostDtoToProduct(productPostDto);

        Long userId = authUser.getUser().getUserId();

        Product response = productService.create(userId, product);

        return new ResponseEntity(new SingleResponseDto<>(productMapper.productToProductResponseDto(response)), HttpStatus.CREATED);
    }

    @PatchMapping("/products/{productsId}")
    public ResponseEntity update(@PathVariable Long productsId,
                                       @Valid @RequestBody ProductPatchDto productPatchDto){
        Product product = productMapper.productPatchDtoToProduct(productPatchDto);

        Product response = productService.update(productsId, product);

        return new ResponseEntity(new SingleResponseDto<>(productMapper.productToProductResponseDto(response)), HttpStatus.OK);
    }

    @DeleteMapping("/products/{productsId}")
    public ResponseEntity<Long> delete(@PathVariable Long productsId){

        return ResponseEntity.ok(productService.delete(productsId));
    }

    @GetMapping("/products")
    public ResponseEntity getLists(@RequestParam int page){
        int size= 15;
        Page<Product> pageProduct = productService.getLists(page, size);
        List<Product> response = pageProduct.getContent();

        return new ResponseEntity<>(new MultiResponse<>(productMapper.productsToProductResponseDto(response),pageProduct), HttpStatus.OK);
    }

    // 평점 추가
    @GetMapping("/products/{productsId}")
    public ResponseEntity getListReview(@PathVariable Long productsId){
        Product product = productService.getList(productsId);
        long average = productService.calculateReviewAverage(productsId);
        ProResponseDto proResponseDto = productMapper.productToProResponseDto(product);
        proResponseDto.setAverage(average);
        return new ResponseEntity(new SingleResponseDto<>(proResponseDto),HttpStatus.OK);

    }
}
