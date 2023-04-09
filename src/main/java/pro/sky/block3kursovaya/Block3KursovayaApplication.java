package pro.sky.block3kursovaya;

import model.Sock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.FileService;
import services.SockService;

import static model.Sock.Color.White;
import static model.Sock.Size.*;

@SpringBootApplication
public class Block3KursovayaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block3KursovayaApplication.class, args);
        System.out.println("запуск выполнен");
    }

}
