package com.example.study.controller;


import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")// locallhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") //http://localhost:8080/api/getMethod
    public String getRequest() {
        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        //권장을 하지 않지만, 이렇게 사용할 경우가 있음.
        //그러면 @RequestParam(name = "password") String pwd 와 같이 사용
        // password 라는 명칭으로 들어오는 값을 pwd에 삽입함.
        String password = "bbbb";

        System.out.println("id : " + id);
        System.out.println("password : " + pwd);

        return id + password;
    }

    //여러 parameter 받기.
    //http://localhost:8080/api/getMultiParameter?account=painclown&email=painclown@gmail.com&page=10
//    @GetMapping("/getMultiParameter")
//    public String getmunltiParameter(SearchParam searchParam){
//        System.out.println(searchParam.getAccount());
//        System.out.println(searchParam.getEmail());
//        System.out.println(searchParam.getPage());
//
//        return "OK";
//    }
    // json 파일을 주로 받음
    // 위 코드를 아래코드로 변경해서 하면 처리해줌
    @GetMapping("/getMultiParameter")
    public SearchParam getmunltiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //이렇게 구현하면,Json형태로 묶여서 넘어감
        //이유는 Json이 표준이 되어서, 따로 처리하지 않으면 Json으로 return 함
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){
        //{
        //"transactionTime": null,
        //"resultCode": "OK",
        //"description": "OK"
        //}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
