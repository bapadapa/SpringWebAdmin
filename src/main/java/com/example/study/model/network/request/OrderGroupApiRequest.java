package com.example.study.model.network.request;


import com.example.study.model.enumclass.OrderDetailStatus;
import com.example.study.model.enumclass.OrderGroupPaymentType;
import com.example.study.model.enumclass.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiRequest {
    private Long id;

    private OrderDetailStatus status;

    private OrderType orderType; //주문 형태   -> 일괄 / 개별

    @Column(columnDefinition = "TEXT")
    private String revAddress;

    private String revName;

    private OrderGroupPaymentType paymentType;  // 결제 수단

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Long userId;




}
