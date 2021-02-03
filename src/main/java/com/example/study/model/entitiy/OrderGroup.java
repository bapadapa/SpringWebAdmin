package com.example.study.model.entitiy;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString (exclude = {"user","orderDetailList"})
public class OrderGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType; //주문 형태   -> 일괄 / 개별

    @Column(columnDefinition = "TEXT")
    private String revAddress;

    private String revName;

    private String paymentType;  // 결제 수단

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //OrderGroup N : 1 User
    @ManyToOne
    private User user;
    //private Long userId;

    //OrderGroup 1 : N OrderDetail
    @OneToMany (fetch = FetchType.LAZY,mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;

}
