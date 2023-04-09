package controllers;



import model.Sock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.FileService;
import services.SockService;

import java.io.*;

import static model.Sock.Color.White;
import static model.Sock.Size.M;

@RestController
@RequestMapping("/socks")
public class SockController {
    SockService sockService;

    FileService fileService;

    public SockController(SockService sockService, FileService fileService) {
        this.sockService = sockService;
        this.fileService = fileService;
    }

@PostMapping ("/add")
    public ResponseEntity <String> addSocks (@RequestBody Sock sock) {
String string = sockService.addSocks(sock);
        return ResponseEntity.ok().body(string);
    }

    @GetMapping ("/show")
    public ResponseEntity <String> showSocks () {
        return ResponseEntity.ok().body(sockService.showSocks());
    }
}
