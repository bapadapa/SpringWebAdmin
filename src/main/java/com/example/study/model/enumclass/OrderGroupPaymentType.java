package com.example.study.model.enumclass;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupPaymentType {

    CARD(1,"카드","카드 결제"),
    BANK_TRANSFER(2,"현금 / 무통장","현금 / 무통장 결제"),
    CHECK_CARD(3,"체크카드" ,"체크카드 결제");

    private Integer id;
    private String title;
    private String description;
}
