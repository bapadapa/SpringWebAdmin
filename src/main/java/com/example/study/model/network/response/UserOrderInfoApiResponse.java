package com.example.study.model.network.response;


import com.example.study.model.entitiy.OrderGroup;
import com.example.study.model.entitiy.User;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderInfoApiResponse {

    private UserApiResponse userApiResponse;

    //User하위에 있기 떄문에 List로 생성
    //private List<OrderGroupApiResponse> orderGroupApiResponseList;

    //private List<ItemApiResponse> itemApiResponsesList;




}
