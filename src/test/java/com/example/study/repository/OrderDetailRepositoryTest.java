package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entitiy.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();


       orderDetail.setOrderAt(LocalDateTime.now());
        //orderDetail.setUserId(5L);
        //orderDetail.setItemId(1L);

        OrderDetail newOrder = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrder);


    }
}
