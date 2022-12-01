package com.backend.domain.product.api;

import com.backend.domain.product.application.ProductService;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.dto.DetailImg;
import com.backend.domain.product.dto.ProResponseDto;
import com.backend.domain.product.dto.ProductResponseDto;
import com.backend.domain.product.dto.TitleImg;
import com.backend.domain.product.mapper.ProductMapper;
import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.Response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @PostMapping("/products/{categoryId}")
    public ResponseEntity create(@CurrentUser CustomUserDetails authUser, @PathVariable Long categoryId,
                                 @RequestParam("price") int price, @RequestParam("productName") String productName,
                                 @RequestParam("tag") String tag,
                                 TitleImg titleImg, DetailImg detailImg){
        log.info("post 맵핑 실행 ");
        Long userId = authUser.getUser().getUserId();
        log.info("user 조회 완료 ");
        Product response = productService.create(userId,price,productName,titleImg,detailImg,tag,categoryId);

        return new ResponseEntity(new SingleResponseDto<>(productMapper.productToProductResponseDto(response)), HttpStatus.CREATED);
    }

    @PatchMapping("/products/{categoryId}/{productsId}")
    public ResponseEntity update(@PathVariable Long productsId,@CurrentUser CustomUserDetails authUser,
                                 @RequestParam("price") int price, @RequestParam("productName") String productName,
                                 TitleImg titleImg,DetailImg detailImg,@PathVariable Long categoryId){
        log.info(" 수정 실행 ");

        Product response = productService.update(productsId,categoryId,price,productName,titleImg,detailImg);
        ProductResponseDto productResponseDto = productMapper.productToProductResponseDto(response);
        productResponseDto.setCategoryId(response.getCategory().getCategoryId());
        log.info(" 수정 된 상품 출력 ");
        return new ResponseEntity(new SingleResponseDto<>(productResponseDto), HttpStatus.OK);
    }

    @DeleteMapping("/products/{productsId}")
    public ResponseEntity<Long> delete(@PathVariable Long productsId){
        log.info("삭제 맵핑 실행");
        return ResponseEntity.ok(productService.delete(productsId));
    }

    // 상품 조회 (필터링)
    @GetMapping("/products/filter/{filterId}")
    public ResponseEntity getLists(@RequestParam int page,@PathVariable int filterId){
        int size= 12;
        log.info("getLists 실행 ");
        Page<Product> pageProduct = productService.getLists(page-1, size,filterId);
        log.info(" 페이징 리스트로 변환 ");
        List<Product> response = pageProduct.getContent();
        log.info(" 상품 목록 조회 완료 ");
        return new ResponseEntity<>(new MultiResponse<>(productMapper.productsToProductResponseDto(response),pageProduct), HttpStatus.OK);
    }

    // 제품 상세 조회
    @GetMapping("/products/{productsId}")
    public ResponseEntity getListReview(@PathVariable Long productsId){
        log.info("getListReview 실행");
        Product product = productService.getList(productsId);
        String average = productService.calculateReviewAverage(productsId);
        ProResponseDto proResponseDto = productMapper.productToProResponseDto(product);
        log.info(" 평균 리뷰 대입 ");
        proResponseDto.setAverage(average);
        proResponseDto.setCategoryId(product.getCategory().getCategoryId());
        log.info(" getListReview 완료 ");
        return new ResponseEntity(new SingleResponseDto<>(proResponseDto),HttpStatus.OK);
    }
    // 카테고리 필터 출력
    @GetMapping("/products/category/{categoryId}/{filterId}")
    public ResponseEntity getListCategory(@PathVariable Long categoryId,@RequestParam int page,@PathVariable int filterId){
        int size= 12;
        log.info(" getListCategory 실행 ");
        Page<Product> pageProduct = productService.getCategory(categoryId,filterId, page-1, size);
        log.info(" getcategory 리스트로 변환 ");
        List<Product> content = pageProduct.getContent();
        log.info(" getListCategory 완료 ");
        return new ResponseEntity<>(new MultiResponse<>(productMapper.productsToProductResponseDto(content),pageProduct), HttpStatus.OK);
    }

    // 랜덤 추천
    @GetMapping("/products/random")
    public ResponseEntity getRandomList(){
        List<Product> random = productService.random();
        return new ResponseEntity<>(new SingleResponseDto<>(productMapper.productsToProductResponseDto(random)),HttpStatus.OK);
    }
}