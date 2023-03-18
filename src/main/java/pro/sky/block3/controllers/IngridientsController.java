package pro.sky.block3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;


import java.util.ArrayList;

@RestController
@RequestMapping("/ingridients")

public class IngridientsController {
    @GetMapping("/create")
    public static String createIng(String name, double count, String unit) {
        if (name == null || count == 0 || unit == null) {
            return "Укажите все поля ингридиента";
        }
        IngridientServices.createIngridient(name, count, unit);
        System.out.println(IngridientServices.getIngridientsMap().get(IngridientServices.getIngridientsMap().size()));
        return IngridientServices.getIngridientsMap().get(IngridientServices.getIngridientsMap().size()).toString();
    }

    @GetMapping("/change")
    public static String changeIng(int id, String name, double count, String unit) {
        if (name == null || count == 0 || unit == null) {
            return "Укажите все поля ингридиента";
        }
        if (IngridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        IngridientServices.changeIngridient(id, name, count, unit);
        System.out.println(IngridientServices.getIngridientsMap().get(id));
        return "Ингридиент изменен: " + IngridientServices.getIngridientsMap().get(id).toString();
    }

    @GetMapping("/delete")
    public static String deleteIng(int id) {
        if (IngridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        Ingridient ingridient = IngridientServices.getIngridientsMap().get(id);
        IngridientServices.deleteIngridient(id);
        System.out.println(ingridient + " = " + IngridientServices.getIngridientsMap().get(id));
        return "Ингридиент удален: " + ingridient;
    }

    @GetMapping("/search/id")
    public static String searchIngId(int id) {
        if (IngridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        IngridientServices.searchIngridient(id);
        return "Найден ингридиент: " + IngridientServices.getIngridientsMap().get(id);
    }

    @GetMapping("/search")
    public static String searchIngridient (String string) {
        return IngridientServices.searchIngridient(string);
    }

    @GetMapping("/list")
    public static String listOfIng() {
        if (IngridientServices.getIngridientsMap().isEmpty()) {
            return "Ингридиентов нет";
        }
        return IngridientServices.getIngridientsMap().toString().replace("{", "").replace("}", "").replace("=", ") ");
    }
}
