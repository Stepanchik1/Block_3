package pro.sky.block3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.block3.Block3Application;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;


import java.util.ArrayList;

@RestController
@RequestMapping("/ingridients")

public class IngridientsController {

    private static final IngridientServices ingridientServices = Block3Application.ingridientServices; //Чтоб был пробный список ингридиентов

    static IngridientServices getIngridientServices() {
        return ingridientServices;
    }

    @GetMapping("/create")
    public String createIng(String name, double count, String unit) {
        if (name == null || count == 0 || unit == null) {
            return "Укажите все поля ингридиента";
        }
        this.ingridientServices.createIngridient(name, count, unit);
        System.out.println(ingridientServices.getIngridientsMap().get(ingridientServices.getIngridientsMap().size()));
        return ingridientServices.getIngridientsMap().get(ingridientServices.getIngridientsMap().size()).toString();
    }

    @GetMapping("/change")
    public String changeIng(int id, String name, double count, String unit) {
        if (name == null || count == 0 || unit == null) {
            return "Укажите все поля ингридиента";
        }
        if (this.ingridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        ingridientServices.changeIngridient(id, name, count, unit);
        System.out.println(ingridientServices.getIngridientsMap().get(id));
        return "Ингридиент изменен: " + ingridientServices.getIngridientsMap().get(id).toString();
    }

    @GetMapping("/delete")
    public String deleteIng(int id) {
        if (this.ingridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        Ingridient ingridient = ingridientServices.getIngridientsMap().get(id);
        ingridientServices.deleteIngridient(id);
        System.out.println(ingridient + " = " + ingridientServices.getIngridientsMap().get(id));
        return "Ингридиент удален: " + ingridient;
    }

    @GetMapping("/search/id")
    public String searchIngId(int id) {
        if (this.ingridientServices.getIngridientsMap().get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        ingridientServices.searchIngridient(id);
        return "Найден ингридиент: " + ingridientServices.getIngridientsMap().get(id);
    }

    @GetMapping("/search")
    public String searchIngridient(String string) {
        return this.ingridientServices.searchIngridient(string);
    }

    @GetMapping("/list")
    public String listOfIng() {
        if (this.ingridientServices.getIngridientsMap().isEmpty()) {
            return "Ингридиентов нет";
        }
        return ingridientServices.getIngridientsMap().toString().replace("{", "").replace("}", "").replace("=", ") ");
    }
}
