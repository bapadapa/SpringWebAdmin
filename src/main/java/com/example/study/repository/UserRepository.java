package com.example.study.repository;

import com.example.study.model.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository< 타입 , KEY 타입>

@Repository
public interface  UserRepository extends JpaRepository<User,Long> {


}
