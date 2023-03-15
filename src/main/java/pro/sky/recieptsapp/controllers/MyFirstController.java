package pro.sky.recieptsapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recieptsapp.RecieptsAppApplication;
import pro.sky.recieptsapp.model.Ingridient;
import pro.sky.recieptsapp.repositories.Repository;
import pro.sky.recieptsapp.services.RecieptsServices;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

@RestController
public class MyFirstController {

   @GetMapping("/")
           public static String reciept (@RequestParam String name, @RequestParam int time) {
       Ingridient ingridient1 = new Ingridient(2, "яйца", "шт.");
       Ingridient ingridient2 = new Ingridient(1, "молоко", "л.");
       Ingridient ingridient3 = new Ingridient(1, "соль", "по вкусу");
       ArrayList <Ingridient> list = new ArrayList<>();
       list.add(ingridient1);
       list.add(ingridient2);
       list.add(ingridient3);
       String [] strings = {"a", "b", "c"};
       RecieptsServices.createReciept(name,time, list, strings);
return Repository.recieptsMap.get(2).toString();
   }
}
