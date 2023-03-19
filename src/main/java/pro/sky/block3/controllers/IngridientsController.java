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
        IngridientServices ingridientServices = new IngridientServices();
        ingridientServices.createIngridient(name, count, unit);
        System.out.println(RecieptsController.ingridientServices.getIngridientsMap().get(RecieptsController.ingridientServices.getIngridientsMap().size()));
        return RecieptsController.ingridientServices.getIngridientsMap().get(RecieptsController.ingridientServices.getIngridientsMap().size()).toString();
    }

    @GetMapping("/change")
    public static String changeIng(int id, String name, double count, String unit) {
        if (name == null || count == 0 || unit == null) {
            return "Укажите все поля ингридиента";
        }
        if (RecieptsController.ingridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        IngridientServices ingridientServices = new IngridientServices();
        ingridientServices.changeIngridient(id, name, count, unit);
        System.out.println(RecieptsController.ingridientServices.getIngridientsMap().get(id));
        return "Ингридиент изменен: " + RecieptsController.ingridientServices.getIngridientsMap().get(id).toString();
    }

    @GetMapping("/delete")
    public static String deleteIng(int id) {
        if (RecieptsController.ingridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        Ingridient ingridient = RecieptsController.ingridientServices.getIngridientsMap().get(id);
        IngridientServices ingridientServices = new IngridientServices();
        ingridientServices.deleteIngridient(id);
        System.out.println(ingridient + " = " + RecieptsController.ingridientServices.getIngridientsMap().get(id));
        return "Ингридиент удален: " + ingridient;
    }

    @GetMapping("/search/id")
    public static String searchIngId(int id) {
        if (RecieptsController.ingridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        IngridientServices ingridientServices = new IngridientServices();
        ingridientServices.searchIngridient(id);
        return "Найден ингридиент: " + RecieptsController.ingridientServices.getIngridientsMap().get(id);
    }

    @GetMapping("/search")
    public static String searchIngridient (String string) {
        IngridientServices ingridientServices = new IngridientServices();
        return ingridientServices.searchIngridient(string);
    }

    @GetMapping("/list")
    public static String listOfIng() {
        if (RecieptsController.ingridientServices.getIngridientsMap().isEmpty()) {
            return "Ингридиентов нет";
        }
        return RecieptsController.ingridientServices.getIngridientsMap().toString().replace("{", "").replace("}", "").replace("=", ") ");
    }
}
