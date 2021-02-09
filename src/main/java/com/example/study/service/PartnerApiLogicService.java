package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entitiy.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.repository.CategoryRepository;
import com.example.study.repository.PartnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse , Partner> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();
        Partner partner = Partner.builder()
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .partnerNumber(partnerApiRequest.getPartnerNumber())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .registeredAt(partnerApiRequest.getRegisteredAt())
                .unregisteredAt(partnerApiRequest.getUnregisteredAt())
                .category(categoryRepository.getOne(partnerApiRequest.getCategoryId()))
                .build();
        Partner newPartner = baseRepository.save(partner);
        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id).map(this::response)
                .orElseGet(()-> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest partnerApiRequest = request.getData();

        baseRepository.findById(partnerApiRequest.getId()).map(partner -> {
            partner
                    .setName(partnerApiRequest.getName())
                    .setStatus(partnerApiRequest.getStatus())
                    .setAddress(partnerApiRequest.getAddress())
                    .setCallCenter(partnerApiRequest.getCallCenter())
                    .setBusinessNumber(partnerApiRequest.getBusinessNumber())
                    .setCeoName(partnerApiRequest.getCeoName())
                    .setRegisteredAt(partnerApiRequest.getRegisteredAt())
                    .setUnregisteredAt(partnerApiRequest.getUnregisteredAt())
                    .setCategory(categoryRepository.getOne(partnerApiRequest.getCategoryId()));
            return partner;
        })
                .map(updatedPartner -> baseRepository.save(updatedPartner))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));

        return null;
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<PartnerApiResponse> response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();
        return Header.OK(partnerApiResponse);
    }
}
