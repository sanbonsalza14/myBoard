package com.my.board.controller;

import com.my.board.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApiTestController {
    @GetMapping("/api")
    public String apiMain() {
        return "test/api";
    }
    @PostMapping("/apiTest")
    @ResponseBody
    //ResponseBody는 클래스 테이터를 -> json르로 변경새서
    //보내주는 역활
    public LoginDto getTest() {

        return new LoginDto("chsys14", "1111");
    }

    @GetMapping("/stringTest")
    @ResponseBody
    public String postString() {
        return "스트링데이터 보냄";
    }

    @PostMapping("apiEntityTest")
    //ResponseEntity<String> -> @ResponseBody
    //@ReqyestBidt dotj wlrwqj qkedmfuaus  json타입으로 전달해야 함.
    public ResponseEntity<String> apiEntityTest(@RequestBody LoginDto dto) {
        String info = dto.getUserId() + "/" + dto.getPassword();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(info);
    }

    @PostMapping("/mapTest")
    @ResponseBody
    public Map<String, String> apiMapTest(@RequestBody LoginDto dto) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", dto.getUserId());
        map.put("비번", dto.getPassword());
        return map;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delete() {
        return "삭제가되었습니다.";
    }
}