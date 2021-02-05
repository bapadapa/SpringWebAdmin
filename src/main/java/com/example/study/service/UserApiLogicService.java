package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entitiy.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResoponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResoponse> {

    @Autowired
    private UserRepository userRepository;

    //1. request Data 가져오기.
    // 2. User 생성
    //3. 생성된 데이터 -> UserApiResponse
    @Override
    public Header<UserApiResoponse> create(Header<UserApiRequest> request) {
        //1. request Data 가져오기.
        UserApiRequest userApiRequest = request.getData();
        // 2. User 생성
        User user = User.builder().
                account(userApiRequest.getAccount()).
                password(userApiRequest.getPassword()).
                status("REGISTERED").
                phoneNumber(userApiRequest.getPhoneNumber()).
                email(userApiRequest.getEmail()).
                registeredAt(LocalDateTime.now()).
                build();
        User newUser = userRepository.save(user);
        //3. 생성된 데이터 -> UserApiResponse


        return response(newUser);
    }

    @Override
    public Header<UserApiResoponse> read(Long id) {
        //id -> repository getOne , getById
        /*Optional<User> optional = userRepository.findById(id);
        return optional
                .map(user -> response(user))
                .orElseGet(()-> Header.ERROR("데이터 없음"));*/
//------------------------------------  위 -> 객체 생성 -> 람다식
        //아래 -> 전체 람다식
        //user -< useraApiResponse return
        return userRepository.findById(id).map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));


        //값이 있다면 map 실행 , 없다면 orElseGet 실행


    }

    @Override
    public Header<UserApiResoponse> update(Header<UserApiRequest> request) {
        //1. data 가져오기
        UserApiRequest userApiRequest = request.getData();
        //2. id ->User 검색
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            //3. update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
            //4. userApiResponse

        })
                .map(user -> userRepository.save(user))
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));


    }

    @Override
    public Header delete(Long id) {
        //1. id -> repository -> user
        Optional<User> optional = userRepository.findById(id);

        //2. reposiory -> delete
        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
        //3. response return

    }

    private Header<UserApiResoponse> response(User user) {
        // user -> userApiResponse
        UserApiResoponse userApiResoponse = UserApiResoponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
        //return Header + Data
        return Header.OK(userApiResoponse);
    }
}
