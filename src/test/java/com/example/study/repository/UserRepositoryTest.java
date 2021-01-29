package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entitiy.Item;
import com.example.study.model.entitiy.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {
    //
    // Di ( dependency injection ) 객체를 Spring이 의존성을 주입시킴 ( 자동 ) .
    // -> Single Tone -> Repositoery는 1개만 생성하고, Autowired로 다같이 쓰겠다.
    // User은 생성시마다 값이 달라지고, 만드는 값이기 때문에, 객체는 생성해서 사용.
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        //String sql = insert into uesr( %s,%s,%d)  value (account, email, age);
        User user = new User();

        //ID 는 Auto incresement  이기 때문에 따로 할 필요가 없다.
        //user.setId();
        user.setAccount("testUser01"); //Not NULL
        user.setEmail("testUser01@gmail.com");
        user.setPhoneNumber("010-1111-2222");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("testUset01");

        //save ==  넣은 entity를 return 해줌. ( 재생성 ? 복사 ?)
        User newUser = userRepository.save(user);
        System.out.println("number = " + newUser);
    }


    @Test
    @Transactional
    public void read() {
        //리스트로 가져옴.
        //userRepository.findAll();
        //return 값이 Optional임
        //Optional 은 있을 수 도 있고, 없을 수 도 있다는 뜻임.

        // select * from user where id = ?
        //Optional<User> user = userRepository.findById(5L);
        Optional<User> user = userRepository.findByAccount("testUser01");
        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                        Item item = detail.getItem();
                        System.out.println(item);
                    }
            );
        });


//        user.ifPresent(selectUser -> {
//            System.out.println("user : " + selectUser);
//            System.out.println("email : " + selectUser.getEmail());
//        });
    }
//    아래와 같이 id를 받아서 조회 후 반환시킴.
//    public User read(@RequestParam Long id){
//        ...
//        Optional<User> user = userRepository.findById(id);
//        return user.get();
//    }

    @Test
    public void update() {
        // 해당 id가 존재여부 판단.
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser -> {
            //만약 아래와 같이 구현했다면, 3번이 바뀌게됨.
            // 즉 아래 코드가 sql문이라고 생각하면 편함.
            //selectUser.setId(3L);
            selectUser.setAccount("SeungHyun");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }

    // 아래와 같이 사용함.
//    @DeleteMapping("/api/user")
//    public void delete(@RequestParam Long id) {
//    }
    @Test
    // @Transactional // 실행 후 rollback 해줌.
    public void delet() {
        Optional<User> user = userRepository.findById(3L);

        //False가 return 하면, Error 발생
        Assertions.assertTrue(user.isPresent()); //

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });
        Optional<User> deleteUser = userRepository.findById(1L);
        Assertions.assertFalse(deleteUser.isPresent());

        /*if(deleteUser.isPresent()){
            //삭제 실패
            System.out.println("데이터 존재 : " +deleteUser.get());
        }
        else{
            //삭제 성공
            System.out.println("데이터 삭제됨.");
        }*/

    }


}
