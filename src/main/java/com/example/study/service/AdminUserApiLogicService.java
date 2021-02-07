package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entitiy.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserApiRequest;
import com.example.study.model.network.response.AdminUserApiResponse;
import com.example.study.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest adminUserApiRequest = request.getData();
        AdminUser adminUser = AdminUser.builder()
                .account(adminUserApiRequest.getAccount())
                .password(adminUserApiRequest.getPassword())
                .status(adminUserApiRequest.getStatus())
                .role(adminUserApiRequest.getRole())
                .lastLoginAt(adminUserApiRequest.getLastLoginAt())
                .passwordUpdatedAt(adminUserApiRequest.getPasswordUpdatedAt())
                .loginFailCount(adminUserApiRequest.getLoginFailCount())
                .registeredAt(adminUserApiRequest.getRegisteredAt())
                .unregisteredAt(adminUserApiRequest.getUnregisteredAt())
                .build();
        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return adminUserRepository.findById(id).map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest adminUserApiRequest = request.getData();

        return adminUserRepository.findById(adminUserApiRequest.getId()).map(adminUser -> {
            adminUser
                    .setAccount(adminUser.getAccount())
                    .setPassword(adminUser.getPassword())
                    .setStatus(adminUser.getStatus())
                    .setRole(adminUser.getRole())
                    .setLastLoginAt(adminUser.getLastLoginAt())
                    .setPasswordUpdatedAt(adminUser.getPasswordUpdatedAt())
                    .setLoginFailCount(adminUser.getLoginFailCount())
                    .setRegisteredAt(adminUser.getRegisteredAt())
                    .setUnregisteredAt(adminUser.getUnregisteredAt());
            return adminUser;
        })
                .map(newAdminUser -> adminUserRepository.save(newAdminUser))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return adminUserRepository.findById(id).map(adminUser -> {
            adminUserRepository.delete(adminUser);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private Header<AdminUserApiResponse> response(AdminUser adminUser) {
        AdminUserApiResponse response = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();
        return Header.OK(response);
    }
}