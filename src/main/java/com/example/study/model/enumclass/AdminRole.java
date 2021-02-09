package com.example.study.model.enumclass;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRole {

    PARTNER(1, "파트너","파트너사 관리자"),
    MANAGER(2,"관리자","서버 관리자");




    private Integer id;
    private String title;
    private String description;
}
