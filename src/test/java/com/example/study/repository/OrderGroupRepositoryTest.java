package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entitiy.OrderGroup;
import com.example.study.model.enumclass.OrderDetailStatus;
import com.example.study.model.enumclass.OrderGroupPaymentType;
import com.example.study.model.enumclass.OrderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends StudyApplicationTests {
    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup  = new OrderGroup();
        orderGroup.setStatus(OrderDetailStatus.COMPLETE);
        orderGroup.setOrderType(OrderType.ALL);
        orderGroup.setRevAddress("서울시 강남구");
        orderGroup.setRevName("김철수");
        orderGroup.setPaymentType(OrderGroupPaymentType.CARD);
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("AdminServer");
        //orderGroup.setUserId(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assertions.assertNotNull(newOrderGroup);


    }
}
