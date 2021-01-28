package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Method는 겹치면 안되지만, 클래스는 겹쳐도 된다.
public class PostController {

//    HTML < FORM > 테그사용,
//    AJAX 검색에 POST를 사용함. -> 검색 파라미터가 많다!
//    http post body -> data
//    Form == jsom, xml , mulipart-form / text-plain 형태들이 있다.
//
//
//
//    @RequestMapping(method = RequestMethod.POST, path  "/postMethod")
//    위 어노테이션과 아래 어노테이션은 동일한 기능을 수행함.
//    @PostMapping(value = "/postMethod" , produces = {"application/json"})
//    위와 같이 사용하여 지원하는 데이터 타입을 설정 할 수 있다. (Default = "application/json" )
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }
    @PutMapping("/pustMethod")
    public void put(){

    }
    @PatchMapping("/patchMethod")
    public void patch(){

    }


}
