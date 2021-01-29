package com.example.study.repository;

import com.example.study.model.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JpaRepository< 타입 , KEY 타입>

@Repository
public interface  UserRepository extends JpaRepository<User,Long> {
    //findBy... 만 매칭된다면, 뒤에 인자 이름은 어떻게 적어도 상관이 없다. 하지만, 동일하게 만드는 것이 가독성에 좋으니 추천함.
    //select * from user where  account = ?
    Optional<User> findByAccount(String account);

    //select * from user where  email = ?
    Optional<User> findByEmail(String email);
    //select * from user where  account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account,String email);

}
