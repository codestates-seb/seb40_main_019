package com.backend.domain.payment.api;

import com.backend.domain.payment.application.PaymentService;
import com.backend.domain.payment.domain.Payment;
import com.backend.domain.payment.dto.PaymentRequest;
import com.backend.domain.payment.mapper.PaymentMapper;
import com.backend.domain.point.application.PointService;
import com.backend.domain.point.domain.PointType;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final PointService pointService;

    @PostMapping("/payment/success")
    public ResponseEntity charge(@CurrentUser CustomUserDetails authUser, @RequestBody PaymentRequest paymentRequest) throws Exception {
        Long userId =authUser.getUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        Payment payment = paymentMapper.paymentRequestToPayment(paymentRequest);
        int price = payment.getAmount();
        int newRestCash = pointService.addCash(user, price, PointType.AddPoint);
//        JsonNode jsonNode = paymentService.create(payment);

        return new ResponseEntity(HttpStatus.OK);
    }
}
