package com.example.study.model.entitiy;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity// == table
@ToString(exclude = {"orderGroupList"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // User  1: N OrderGorup
    @OneToMany(fetch =FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;




    //연습시 사용한 코드들..
    //FetchType
    //Lazy == 지연로딩 , EAGER == 즉시 로딩
    //Lagy = SELECT * FROM item where id = ?
    //따로 메소드를 GetMethod와 같이 메소드로 호출하지 않는 이상 연관관계가 설정된 테이블에 대해서 Select하지 않겠다.

    //EAGER =
    //item_id = order_detail.item_id
    //user_id = order_detail.user_id
    //where item.id = ?
    // 즉시 모든 것을 Load하겠다. -> 모든 테이블에 대해서 JOIN이 발생함.
    // Data가 많다면, 해당 쿼리문이 실행 될 때 성능저하 및 검색 실패할 수 있다.
    // Many to One  or One to One 에서만 사용 하는 것을 추천함.

    //연결된 부분은 N개니, List로 연결 시켜줘야함!

    //1:N 관계
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<OrderDetail> orderDetailList;


}
