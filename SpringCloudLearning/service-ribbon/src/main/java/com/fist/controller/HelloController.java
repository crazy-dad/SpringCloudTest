package com.fist.controller;

import com.fist.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    //SpringMVC以前版本的@RequestMapping，到了新版本被下面新注释替代
    //@GetMapping
    //@PostMapping
    //@PutMapping
    //@DeleteMapping
    //@PatchMapping
    @GetMapping(value = "/hi")
    public String hiController(@RequestParam String name) {
        return helloService.hiService(name);
    }
}
