package com.example.study.model.entitiy;


import com.example.study.model.enumclass.OrderDetailStatus;
import com.example.study.model.enumclass.OrderGroupPaymentType;
import com.example.study.model.enumclass.OrderType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString (exclude = {"user","orderDetailList"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderDetailStatus status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType; //주문 형태   -> 일괄 / 개별

    @Column(columnDefinition = "TEXT")
    private String revAddress;

    private String revName;

    @Enumerated(EnumType.STRING)
    private OrderGroupPaymentType paymentType;  // 결제 수단

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;


    //OrderGroup N : 1 User
    @ManyToOne
    private User user;
    //private Long userId;

    //OrderGroup 1 : N OrderDetail
    @OneToMany (fetch = FetchType.LAZY,mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;

}
