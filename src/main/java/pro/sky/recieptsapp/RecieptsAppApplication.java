package pro.sky.recieptsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.recieptsapp.model.Ingridient;
import pro.sky.recieptsapp.model.Reciept;
import pro.sky.recieptsapp.repositories.Repository;
import pro.sky.recieptsapp.services.IngridientServices;
import pro.sky.recieptsapp.services.RecieptsServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class RecieptsAppApplication {
    public static ArrayList<Ingridient> quickCast(Ingridient[] ingridients) {
        return (ArrayList<Ingridient>) Arrays.stream(ingridients).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SpringApplication.run(RecieptsAppApplication.class, args);
        IngridientServices.createIngridient("яйца", 2, "шт.");
        IngridientServices.createIngridient("молоко", 0.5, "л.");
        IngridientServices.createIngridient("курица", 200, "гр.");
        IngridientServices.createIngridient("соль", 1, "по вкусу");
        Ingridient[] ingridients = new Ingridient[]{Repository.ingridientsMap.get(3), Repository.ingridientsMap.get(4)};
        ArrayList<Ingridient> ing = new ArrayList<>();
        ing.add(Repository.ingridientsMap.get(1));
        ing.add(Repository.ingridientsMap.get(2));
        ing.add(Repository.ingridientsMap.get(3));
        String[] strings = {"a", "b", "c"};
        RecieptsServices.createReciept("пробный рецепт", 23, ing, strings);
        RecieptsServices.createReciept("пробный рецепт №2", 5, 3, "Приготовить и съесть");
        RecieptsServices.createReciept("пробный рецепт №3", 11, quickCast(ingridients), "Подавиться");
        System.out.println(Repository.recieptsMap.get(1));
        System.out.println(Repository.recieptsMap.get(2));
        System.out.println(Repository.recieptsMap.get(3));
    }


}
