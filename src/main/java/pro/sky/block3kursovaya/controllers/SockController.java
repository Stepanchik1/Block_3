package pro.sky.block3kursovaya.controllers;


import pro.sky.block3kursovaya.model.Sock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.block3kursovaya.services.FileService;
import pro.sky.block3kursovaya.services.SockService;

@RestController
@RequestMapping("/socks")
public class SockController {
    SockService sockService;

    FileService fileService;

    public SockController(SockService sockService, FileService fileService) {
        this.sockService = sockService;
        this.fileService = fileService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSocks(@RequestBody Sock sock) {
        if (sock.getQuantity() > 0) {
            String string = sockService.addSocks(sock);
            System.out.println(string);
            return ResponseEntity.ok().body(string);
        } else return ResponseEntity.badRequest().body("Количество носков должно быть больше 0");
    }

    @GetMapping("/show")
    public ResponseEntity<String> showSocks() {
        System.out.println(sockService.showSocks());
        return ResponseEntity.ok().body(sockService.showSocks());
    }

    @GetMapping("/search/{string}")
    public ResponseEntity<String> getByColor(@PathVariable String string) {
        return ResponseEntity.ok().body(sockService.findSocks(string));
    }

    @PutMapping("/release")
    public ResponseEntity<Sock> putSocks(@RequestParam int cotton, @RequestParam String color, @RequestParam String size, @RequestParam int count) {
        Sock sock = sockService.checkSock(color, size, cotton);
        if (sockService.canPut(sock, count)) {
            System.out.println(sockService.putSocks(sock, count));
            return ResponseEntity.ok().body(new Sock(cotton, count, sock.getSize(), sock.getColor()));
        } else {
            System.out.println(sockService.putSocks(sockService.checkSock(color, size, cotton), count));
            return ResponseEntity.noContent().build();
        }
    }
}


