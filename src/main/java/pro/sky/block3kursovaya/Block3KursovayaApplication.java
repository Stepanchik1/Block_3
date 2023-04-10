package pro.sky.block3kursovaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.block3kursovaya.model.Sock;

import static pro.sky.block3kursovaya.model.Sock.Color.Black;
import static pro.sky.block3kursovaya.model.Sock.Size.XL;

@SpringBootApplication
public class Block3KursovayaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block3KursovayaApplication.class, args);
        System.out.println("запуск выполнен");
        Sock sock = new Sock(1, 1, XL, Black);
    }

}
