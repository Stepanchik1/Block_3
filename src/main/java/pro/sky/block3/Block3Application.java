package pro.sky.block3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.block3.controllers.RecieptsController;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class Block3Application {

    public static void main(String[] args) {
        SpringApplication.run(Block3Application.class, args);
        System.out.println(123);
    }
}
