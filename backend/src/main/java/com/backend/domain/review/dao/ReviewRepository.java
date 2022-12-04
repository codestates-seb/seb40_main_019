package com.backend.domain.review.dao;

import com.backend.domain.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select r from Review r where r.user.userId = :userId")
    Page<Review> findByUser(@Param("userId") long userId, Pageable pageable);

    @Query("select r from Review r where r.product.productId = :productId")
    Page<Review> findByProduct(@Param("productId") long productId, Pageable pageable);

    @Query("select r from Review r where r.product.productId = :productId")
    List<Review> findByProductId(@Param("productId") long productId);

    @Query("select r from Review r join Product p on p.productId = r.proId where p.user.userId = :userId")
    Page<Review> findByUserId(@Param("userId") Long userId,Pageable pageable);

    void deleteByUser_UserRoleOrUser_UserRole(String roleUserTest, String roleAdminTest);
}
