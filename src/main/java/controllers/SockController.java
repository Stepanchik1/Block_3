package controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.SockService;

@RestController
@RequestMapping("/socks")
public class SockController {
    SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }


}
