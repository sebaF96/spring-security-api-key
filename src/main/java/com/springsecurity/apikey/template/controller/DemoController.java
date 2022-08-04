package com.springsecurity.apikey.template.controller;

import com.springsecurity.apikey.template.dto.DemoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/test")
    ResponseEntity<DemoResponseDto> demo() {
        return ResponseEntity.ok(new DemoResponseDto("Authorized!"));
    }
}
