package com.backend.domain.product.application;

import com.backend.domain.category.dao.CategoryRepository;
import com.backend.domain.category.domain.Category;
import com.backend.domain.category.excption.CategoryNotFound;
import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.exception.ProductExist;
import com.backend.domain.product.exception.ProductNotFound;
import com.backend.domain.review.dao.ReviewRepository;
import com.backend.domain.review.domain.Review;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final CategoryRepository categoryRepository;

    // 제품 생성
    @Transactional
    public Product create(Long userId, int price, String productName,String titleUrl,String detailUrl,String tag,Long categoryId){
        log.info(" 프로젝트 새로 생성 ");
        Product product = new Product();
        product.setPrice(price);
        product.setProductName(productName);
        product.setTitleImg(titleUrl);
        product.setDetailImg(detailUrl);
        product.setTag(tag);

        log.info(" 상품 이름 중복 검사 ");
        existSameName(product.getProductName());

        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        log.info(" user : " ,user);
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFound::new);
        log.info("category : ",category);
        product.setCategory(category);
        product.setUser(user);
        log.info(" product : ",product);
        return productRepository.save(product);
    }

    // 상품 수정
    @Transactional
    public Product update(Long productId, Long categoryId,int price ,String productName, String titleUrl, String detailImg) {

        Product findProduct = productRepository.findById(productId).orElseThrow(ProductNotFound::new);
        log.info(" findProduct : ",findProduct);
        Category category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFound::new);
        log.info(" category : ", category);
        log.info(" 수정 카테고리 입력 ");
        Optional.ofNullable(category)
                        .ifPresent(findProduct::setCategory);
        log.info(" 수정 상품 아이디 입력 ");
        Optional.ofNullable(productId)
                .ifPresent(findProduct::setProductId);
        log.info(" 수정 상품 이름 입력 ");
        Optional.ofNullable(productName)
                .ifPresent(findProduct::setProductName);
        log.info(" 수정 가격 입력 ");
        Optional.ofNullable(price)
                .ifPresent(findProduct::setPrice);
        log.info(" 수정 타이틀 이미지 입력 ");
        Optional.ofNullable(titleUrl)
                .ifPresent(findProduct::setTitleImg);
        log.info(" 수정 디테일 이미지 입력 ");
        Optional.ofNullable(detailImg)
                .ifPresent(findProduct::setDetailImg);
        log.info(" findProduct : ", findProduct);
        return productRepository.save(findProduct);
    }

    @Transactional
    public Long delete(Long productsId) {

        Product product = productRepository.findById(productsId).orElseThrow(ProductNotFound::new);
        log.info(" product : ", product);
        productRepository.delete(product);
        log.info(" 상품 삭제 ");
        return  productsId;
    }

    @Transactional
    public Page<Product> getLists(int page,int size,int filter ) {
        log.info("전체 리스트 조회");
        String str ="productId";
        if(filter== 1) { str = "productId";}
        else if (filter== 2) { str = "viewCount";}
        else if (filter == 3 ) {
            str = "price";
        }
        else if (filter == 4 ) {
            return productRepository.findAll(PageRequest.of(page, size, Sort.by("price").ascending()));
        }
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(str).descending()));
    }

    // 제품 이름이 같을 경우 오류 발생
    private void existSameName(String productName) {
        if (productRepository.existsByProductName(productName)){
            // 상품 이름이 겹치면 에러
            log.info(" 상품 이름이 겹침");
            throw new ProductExist();
        }
    }

    @Transactional
    public Product getList(Long productId) {
        log.info(" 상품 하나 조회 ");
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFound::new);
        product.ViewCountPlus(product.getViewCount());
        return product;
    }
    @Transactional
    public String calculateReviewAverage(Long productId){
        log.info("평점 계산 상품 검색 ");
        List<Review> reviews = reviewRepository.findByProductId(productId);
        if (reviews.isEmpty()){
            log.info("리뷰가 없어요");
            return "0";
        }else {
            log.info("리뷰 있다");
            int totalScore = reviews.stream()
                    .mapToInt(Review::getStar)
                    .sum();
            int totalUsers = reviews.size();
            log.info(" 리뷰 계산 중  ");
            double result = ((double) totalScore / (double) totalUsers);

            DecimalFormat form = new DecimalFormat("#.##");
            log.info("result : ",result);
            return form.format(result);
        }
    }
    @Transactional
    public Page<Product> getCategory(Long categoryId,int page, int size) {
        log.info(" 카테고리 별 상품 조회 ");
        return productRepository.findByCategory(categoryId,PageRequest.of(page,size,Sort.by("productId").descending()));
    }

    @Transactional
    public List<Product> random() {
        List<Product> list = new ArrayList<>();

        Random rnd = new Random();

        int[] arr = new int[3];

        for (int i =0; i<arr.length;i++){
            arr[i] = rnd.nextInt(10) +1; // 1부터 10까지 랜덤 숫자 뽑음
            for (int j = 0; j<i;j++){
                if (arr[i] == arr[j]) i--;  // 중복제거
            }
        }
        for (int i : arr){
            Product product = productRepository.findById((long) i).orElseThrow(ProductNotFound::new);
            list.add(product);
        }
        return list;
    }
}