package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entitiy.Item;
import com.example.study.model.enumclass.ItemStatus;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRpositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;


    @Test
    public void create(){
        Item item = new Item();
        item.setStatus(ItemStatus.REGISTERED);
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2021년형 노트북입니다.");
        item.setPrice(BigDecimal.valueOf(900000));
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
//        item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);

    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        //아이템 유무 판단, 실패시 Error
        Assertions.assertTrue(item.isPresent());
        //출력
//        item.ifPresent(i-> {
//            System.out.println(i);
//        });
    }

    //연습----------------------------------------------------------------------------
/*    @Test
    public void create() {
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(10000);
        item.setContent("삼성 노트북");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);

    }
    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        //아이템 유무 판단, 실패시 Error
        Assertions.assertTrue(item.isPresent());
        //출력
//        item.ifPresent(i-> {
//            System.out.println(i);
//        });
    }*/
}
