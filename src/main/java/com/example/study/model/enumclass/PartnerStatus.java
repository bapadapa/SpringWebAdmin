package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartnerStatus {
    REGISTERED(1 , "등록","협력사 등록 상태"),
    UNREGISTERED(2, "해지","협력사 해지 상태"),
    WAITING(3, "승인 대기","혐력사 승인 대기 상태")
    ;

    private Integer id;
    private String title;
    private String description;


}
