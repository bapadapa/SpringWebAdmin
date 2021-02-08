package com.example.study.model.entitiy;


import com.example.study.model.enumclass.ItemStatus;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    private String name;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;


    //Item N : 1 Partner
    @ManyToOne
    private Partner partner;
    //private Long partnerId;


    //Item 1 : N  OrderDetail
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetailList;


    //아래는 연습시 사용.
    //연결된 부분은 N개니, List로 연결 시켜줘야함!
    //1: N
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
//    private List<OrderDetail> orderDetailList;
}
