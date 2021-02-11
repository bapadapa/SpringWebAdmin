package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entitiy.OrderGroup;
import com.example.study.model.entitiy.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    //1. request Data 가져오기.
    // 2. User 생성
    //3. 생성된 데이터 -> UserApiResponse
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        //1. request Data 가져오기.
        UserApiRequest userApiRequest = request.getData();
        // 2. User 생성
        User user = User.builder().
                account(userApiRequest.getAccount()).
                password(userApiRequest.getPassword()).
                status(UserStatus.REGISTERED).
                phoneNumber(userApiRequest.getPhoneNumber()).
                email(userApiRequest.getEmail()).
                registeredAt(LocalDateTime.now()).
                build();
        User newUser = userRepository.save(user);
        //3. 생성된 데이터 -> UserApiResponse


        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //id -> repository getOne , getById
        /*Optional<User> optional = userRepository.findById(id);
        return optional
                .map(user -> response(user))
                .orElseGet(()-> Header.ERROR("데이터 없음"));*/
//------------------------------------  위 -> 객체 생성 -> 람다식
        //아래 -> 전체 람다식
        //user -< useraApiResponse return
        return userRepository.findById(id)
                .map(user -> response(user))
                //.map(userApiResponse -> Header.OK(userApiResponse))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));


        //값이 있다면 map 실행 , 없다면 orElseGet 실행


    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
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
                .map(Header::OK)
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

    private UserApiResponse response(User user) {
        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
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
        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        List<UserApiResponse> userApiResponses = users.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPage(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();


        return Header.OK(userApiResponses,pagination);
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        //user
        User user = userRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);



        //orderGroup  주문내역 가져오기
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse =  orderGroupApiLogicService.response(orderGroup).getData();

                    //item api response  상품가져오기
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item ->  itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());
                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;

                })
                .collect(Collectors.toList());

        // 주문내용을 토대로 사용자정보 가져오기
        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return  Header.OK(userOrderInfoApiResponse);
    }
}
