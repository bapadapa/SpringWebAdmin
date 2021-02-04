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
        return null;
    }

    @Override
    public Header<UserApiResoponse> update(Header<UserApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<UserApiResoponse> response(User user){
        // user -> userApiResponse
        UserApiResoponse userApiResoponse=UserApiResoponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                . status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unRegisteredAt(user.getUnregisteredAt())
                .build();
        //return Header + Data
        return Header.OK(userApiResoponse);
    }
}
