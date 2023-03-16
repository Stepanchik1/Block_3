package pro.sky.recieptsapp.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recieptsapp.RecieptsAppApplication;
import pro.sky.recieptsapp.model.Ingridient;
import pro.sky.recieptsapp.model.Reciept;
import pro.sky.recieptsapp.repositories.Repository;
import pro.sky.recieptsapp.services.RecieptsServices;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/reciepts")
public class RecieptsController {


    @GetMapping("/create")
    public static String newReciept(@RequestParam String name, @RequestParam int time, @RequestParam int id, @RequestParam String inst) {
        RecieptsServices.createReciept(name, time, id, inst);
        System.out.println(Repository.recieptsMap.get(Repository.recieptsMap.size()).toString());
        return "Создан рецепт:\n" + Repository.recieptsMap.get(Repository.recieptsMap.size()).toString();
    }

    @GetMapping("/change/name")
    public static String changeName(@RequestParam int id, @RequestParam String name) {
        if (Repository.recieptsMap.get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices.changeRecieptName(id, name);
        System.out.println(Repository.recieptsMap.get(id));
        return "Рецепт изменен:\n" + Repository.recieptsMap.get(id).toString();
    }

    @GetMapping("/change/time")
    public static String changeTime(@RequestParam int id, @RequestParam int time) {
        if (Repository.recieptsMap.get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices.changeRecieptCookingTime(id, time);
        System.out.println(Repository.recieptsMap.get(id));
        return "Рецепт изменен:\n" + Repository.recieptsMap.get(id).toString();
    }

    @GetMapping("/add/ingr")
    public static String addIngridient(@RequestParam int id, @RequestParam int index) {
        if (Repository.recieptsMap.get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices.addIngridient(id, index);
        System.out.println(Repository.recieptsMap.get(id));
        return "Рецепт изменен:\n" + Repository.recieptsMap.get(id).toString();
    }

    @GetMapping("/add/inst")
    public static String addInstruction(@RequestParam int id, @RequestParam String instr) {
        if (Repository.recieptsMap.get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices.addInstruction(id, instr);
        System.out.println(Repository.recieptsMap.get(id));
        return "Рецепт изменен:\n" + Repository.recieptsMap.get(id).toString();
    }

    @GetMapping("/delete/ingr")
    public static String deleteIngridient(@RequestParam int id, @RequestParam int number) {
        if (Repository.recieptsMap.get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        if (Repository.recieptsMap.get(id).getIngridients().size() < number) {
            return "Под таким номером ингридиента нет";
        }
        RecieptsServices.deleteIngridient(id, number);
        System.out.println(Repository.recieptsMap.get(id));
        return "Рецепт изменен:\n" + Repository.recieptsMap.get(id).toString();
    }

    @GetMapping("/delete/inst")
    public static String deleteInstruction(@RequestParam int id, @RequestParam int number) {
        if (Repository.recieptsMap.get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        if (Repository.recieptsMap.get(id).getInstructions().length < number) {
            return "Под таким номером инструкции нет";
        }
        RecieptsServices.deleteInstruction(id, number);
        System.out.println(Repository.recieptsMap.get(id));
        return "Рецепт изменен:\n" + Repository.recieptsMap.get(id).toString();
    }

    @GetMapping("/search")
    public static String searchReciept(@RequestParam int id) {
        if (Repository.recieptsMap.get(id) == null) {
            return "По такому id рецептов не найдено";
        }

        RecieptsServices.searchReciept(id);
        System.out.println(Repository.recieptsMap.get(id));
        return "Найден рецепт: " + Repository.recieptsMap.get(id).toString();
    }

    @GetMapping("/list")
    public static String listOfReciepts() {
        if (Repository.recieptsMap.isEmpty()) {
            return "Рецептов нет";
        }
        return Repository.recieptsMap.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    @GetMapping("/search/ing/id")
    public static String searchByIng(int id) {
        if (Repository.ingridientsMap.get(id) == null) {
            return "По такому id ингридиента нет";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= Repository.recieptsMap.size(); i++) {
            for (Ingridient j : Repository.recieptsMap.get(i).getIngridients())
                if (j.getId() == id) {
                    al.add(Repository.recieptsMap.get(i));
                }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    @GetMapping("/search/ing/1")
    public static String searchByIng1(String s) {
        if (Repository.recieptsMap == null || Repository.recieptsMap.isEmpty()) {
            return "Список рецептов пуст";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= Repository.recieptsMap.size(); i++) {
            for (Ingridient j : Repository.recieptsMap.get(i).getIngridients()) {
                if (j.getName().trim().toLowerCase().equals(s.trim().toLowerCase())) {
                    al.add(Repository.recieptsMap.get(i));
                    break;
                }
            }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    @GetMapping("/search/ing/2")
    public static String searchByIng1(String s1, String s2) {
        if (Repository.recieptsMap == null || Repository.recieptsMap.isEmpty()) {
            return "Список рецептов пуст";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= Repository.recieptsMap.size(); i++) {
            for (Ingridient j : Repository.recieptsMap.get(i).getIngridients()) {
                if (j.getName().trim().toLowerCase().equals(s1.trim().toLowerCase())||j.getName().trim().toLowerCase().equals(s2.trim().toLowerCase())) {
                    al.add(Repository.recieptsMap.get(i));
                    break;
                }
            }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    @GetMapping("/search/ing/3")
    public static String searchByIng3(String s1, String s2, String s3) {
        if (Repository.recieptsMap == null || Repository.recieptsMap.isEmpty()) {
            return "Список рецептов пуст";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= Repository.recieptsMap.size(); i++) {
            for (Ingridient j : Repository.recieptsMap.get(i).getIngridients()) {
                if (j.getName().trim().toLowerCase().equals(s1.trim().toLowerCase())||j.getName().trim().toLowerCase().equals(s2.trim().toLowerCase())||j.getName().trim().toLowerCase().equals(s3.trim().toLowerCase())) {
                    al.add(Repository.recieptsMap.get(i));
                    break;
                }
            }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }
}