package pro.sky.recieptsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.recieptsapp.model.Ingridient;
import pro.sky.recieptsapp.model.Reciept;
import pro.sky.recieptsapp.repositories.Repository;
import pro.sky.recieptsapp.services.RecieptsServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class RecieptsAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(RecieptsAppApplication.class, args);
Ingridient ingridient1 = new Ingridient(2, "яйца", "шт.");
        Ingridient ingridient2 = new Ingridient(1, "молоко", "л.");
        Ingridient ingridient3 = new Ingridient(1, "соль", "по вкусу");
        Ingridient [] ingridients = new Ingridient[] {ingridient1, ingridient2, ingridient3};
        String [] jjj = new String[0];
        ArrayList<Ingridient> arrayList = new ArrayList<>();
        ArrayList <Ingridient> ing = new ArrayList<>();
        ing.add(ingridient1);
        ing.add(ingridient2);
        ing.add(ingridient3);
        String [] strings = {"a", "b", "c"};
        RecieptsServices.createReciept("gdsdd", 23, ing, strings);

    }

}
