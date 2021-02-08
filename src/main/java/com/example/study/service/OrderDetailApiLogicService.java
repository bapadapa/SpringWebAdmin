package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entitiy.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.OrderDetailRepository;
import com.example.study.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest orderDetailApiRequest = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(orderDetailApiRequest.getStatus())
                .arrivalDate(orderDetailApiRequest.getArrivalDate())
                .quantity(orderDetailApiRequest.getQuantity())
                .totalPrice(orderDetailApiRequest.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()))
                .item(itemRepository.getOne(orderDetailApiRequest.getItemId()))
                .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return orderDetailRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest orderDetailApiRequest = request.getData();

        return orderDetailRepository.findById(orderDetailApiRequest.getId())
                .map(orderDetail -> {
                            orderDetail
                                    .setStatus(orderDetailApiRequest.getStatus())
                                    .setArrivalDate(orderDetailApiRequest.getArrivalDate())
                                    .setQuantity(orderDetailApiRequest.getQuantity())
                                    .setTotalPrice(orderDetailApiRequest.getTotalPrice())
                                    .setOrderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()))
                                    .setItem(itemRepository.getOne(orderDetailApiRequest.getItemId()));
                            return orderDetail;
                        }
                )
                .map(updatedOrderDetail -> orderDetailRepository.save(updatedOrderDetail))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));


    }

    @Override
    public Header delete(Long id) {
        return orderDetailRepository.findById(id)
                .map(orderDetail -> {
                    orderDetailRepository.delete(orderDetail);
                    return Header.OK();
                }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {
        OrderDetailApiResponse response = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();
        return Header.OK(response);
    }
}
