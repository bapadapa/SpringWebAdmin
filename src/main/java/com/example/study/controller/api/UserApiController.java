package com.example.study.controller.api;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResoponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest , UserApiResoponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") //api/user
    public Header<UserApiResoponse> create(@RequestBody Header<UserApiRequest>  request) {
        log.info("{}",request); // request.toString()
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResoponse> read(@PathVariable(name = "id") Long id) {
        log.info("read id : {}" ,id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")//api/user
    public Header<UserApiResoponse> update(@RequestBody Header<UserApiRequest>  request) {
        log.info("{}" ,request);
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable(name = "id") Long id) {
        log.info("delete id : {}" ,id);
        return userApiLogicService.delete(id);
    }
}
