package pro.sky.block3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class Block3Application {

    public static ArrayList<Ingridient> quickCast(Ingridient[] ingridients) {
        return (ArrayList<Ingridient>) Arrays.stream(ingridients).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SpringApplication.run(Block3Application.class, args);
        System.out.println(123);
        IngridientServices.createIngridient("яйца", 2, "шт.");
        IngridientServices.createIngridient("молоко", 0.5, "л.");
        IngridientServices.createIngridient("курица", 200, "гр.");
        IngridientServices.createIngridient("соль", 1, "по вкусу");
        Ingridient[] ingridients = new Ingridient[]{IngridientServices.getIngridientsMap().get(3), IngridientServices.getIngridientsMap().get(4)};
        ArrayList<Ingridient> ing = new ArrayList<>();
        ing.add(IngridientServices.getIngridientsMap().get(1));
        ing.add(IngridientServices.getIngridientsMap().get(2));
        ing.add(IngridientServices.getIngridientsMap().get(3));
        String[] strings = {"a", "b", "c"};
        RecieptsServices.createReciept("пробный рецепт", 23, ing, strings);
        RecieptsServices.createReciept("пробный рецепт №2", 5, 3, "Приготовить и съесть");
        RecieptsServices.createReciept("пробный рецепт №3", 11, quickCast(ingridients), "Подавиться");
    }

}
