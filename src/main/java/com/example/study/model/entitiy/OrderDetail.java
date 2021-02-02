package com.example.study.model.entitiy;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = {"user","item"}) // 상호 참조하여 Looping이 돈다 -> OverFlow!
@Entity  //order_detail과 자동 맵핑됨, java(카멜 케이스 ) - DB ( 스테이크 )
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;




    //연습시 사용함.
    //반듯이 객체로 연결시켜 줘야함!!
    //N:1
//    @ManyToOne
//    private User user;
//
//    // N:1
//    @ManyToOne
//    private Item item;


}
