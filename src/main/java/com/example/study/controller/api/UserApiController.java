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
    public Header<UserApiResoponse> create(@RequestBody Header<UserApiRequest>  requst) {
        log.info("{}",requst); // request.toString()
        return userApiLogicService.create(requst);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResoponse> read(@PathVariable(name = "id") Long id) {

        return null;
    }

    @Override
    @PutMapping("")//api/user
    public Header<UserApiResoponse> update(@RequestBody Header<UserApiRequest>  request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable(name = "id") Long id) {
        return null;
    }
}
