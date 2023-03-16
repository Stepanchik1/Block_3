package pro.sky.recieptsapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.recieptsapp.model.Ingridient;
import pro.sky.recieptsapp.repositories.Repository;
import pro.sky.recieptsapp.services.IngridientServices;

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
        System.out.println(Repository.ingridientsMap.get(Repository.ingridientsMap.size()));
        return Repository.ingridientsMap.get(Repository.ingridientsMap.size()).toString();
    }

    @GetMapping("/change")
    public static String changeIng(int id, String name, double count, String unit) {
        if (name == null || count == 0 || unit == null) {
            return "Укажите все поля ингридиента";
        }
        if (Repository.ingridientsMap.get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        IngridientServices.changeIngridient(id, name, count, unit);
        System.out.println(Repository.ingridientsMap.get(id));
        return "Ингридиент изменен: " + Repository.ingridientsMap.get(id).toString();
    }

    @GetMapping("/delete")
    public static String deleteIng(int id) {
        if (Repository.ingridientsMap.get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        Ingridient ingridient = Repository.ingridientsMap.get(id);
        IngridientServices.deleteIngridient(id);
        System.out.println(ingridient + " = " + Repository.ingridientsMap.get(id));
        return "Ингридиент удален: " + ingridient;
    }

    @GetMapping("/search/id")
    public static String searchIngId(int id) {
        if (Repository.ingridientsMap.get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        IngridientServices.searchIngridient(id);
        return "Найден ингридиент: " + Repository.ingridientsMap.get(id);
    }

    @GetMapping("/search")
    public static String searchIngridient (String string) {
        ArrayList<Ingridient> al = new ArrayList<>();
        if (!Repository.ingridientsMap.isEmpty()) {
            for (Ingridient j : Repository.ingridientsMap.values()) {
                if (j.getName().trim().toLowerCase().equals(string.trim().toLowerCase())) {al.add(j);}
            }
            if (al.isEmpty()) {return "Ингридиенты не найдены";}
            return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
        } else {
            return "Список ингридиентов пуст";}
    }

    @GetMapping("/list")
    public static String listOfIng() {
        if (Repository.ingridientsMap.isEmpty()) {
            return "Ингридиентов нет";
        }
        return Repository.ingridientsMap.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }
}
