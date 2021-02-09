package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderDetailStatus {

    ORDERING    (1 , "주문중","주문 진행 상태"),
    COMPLETE(2, "성공","주문 성공 상태"),
    CONFIRM(3, "확인","주문 확인 상태")
    ;

    private Integer id;
    private String title;
    private String description;

}
