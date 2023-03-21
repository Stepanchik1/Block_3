package pro.sky.block3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.block3.Block3Application;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.controllers.model.Reciept;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;


import java.util.ArrayList;


@RestController
@RequestMapping("/reciepts")
public class RecieptsController {

   private static RecieptsServices recieptsServices = Block3Application.recieptsServices; //Чтоб был список пробных рецептов

    @GetMapping("/create")
    public static String newReciept(@RequestParam String name, @RequestParam int time, @RequestParam int id, @RequestParam String inst) {
        recieptsServices.createReciept(name, time, id, inst, IngridientsController.getIngridientServices().getIngridientsMap());
        System.out.println(recieptsServices.getRecieptsMap().get(id).toString());
        return "Создан рецепт:\n" + recieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/change/name")
    public static String changeName(@RequestParam int id, @RequestParam String name) {
        if (recieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
       recieptsServices.changeRecieptName(id, name);
        System.out.println(recieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + recieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/change/time")
    public static String changeTime(@RequestParam int id, @RequestParam int time) {
        if (recieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        recieptsServices.changeRecieptCookingTime(id, time);
        System.out.println(recieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + recieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/add/ingr")
    public static String addIngridient(@RequestParam int id, @RequestParam int index) {
        if (recieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        recieptsServices.addIngridient(id, index, IngridientsController.getIngridientServices().getIngridientsMap());
        System.out.println(recieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + recieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/add/inst")
    public static String addInstruction(@RequestParam int id, @RequestParam String instr) {
        if (recieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        recieptsServices.addInstruction(id, instr);
        System.out.println(recieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + recieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/delete/ingr")
    public static String deleteIngridient(@RequestParam int id, @RequestParam int number) {
        if (recieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        if (recieptsServices.getRecieptsMap().get(id).getIngridients().size() < number) {
            return "Под таким номером ингридиента нет";
        }
        recieptsServices.deleteIngridient(id, number);
        System.out.println(recieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + recieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/delete/inst")
    public static String deleteInstruction(@RequestParam int id, @RequestParam int number) {
        if (recieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        if (recieptsServices.getRecieptsMap().get(id).getInstructions().length < number) {
            return "Под таким номером инструкции нет";
        }
        recieptsServices.deleteInstruction(id, number);
        System.out.println(recieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + recieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/search")
    public static String searchReciept(@RequestParam int id) {
        if (recieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        recieptsServices.searchReciept(id);
        System.out.println(recieptsServices.getRecieptsMap().get(id));
        return "Найден рецепт: " + recieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/list")
    public static String listOfReciepts() {
        if (recieptsServices.getRecieptsMap().isEmpty()) {
            return "Рецептов нет";
        }
        return recieptsServices.getRecieptsMap().toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    @GetMapping("/search/ing/id")
    public static String searchByIng(int id) {
        if (IngridientsController.getIngridientServices().getIngridientsMap().get(id) == null) {
            return "По такому id ингридиента нет";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= recieptsServices.getRecieptsMap().size(); i++) {
            for (Ingridient j : recieptsServices.getRecieptsMap().get(i).getIngridients())
                if (j.getId() == id) {
                    al.add(recieptsServices.getRecieptsMap().get(i));
                }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    @GetMapping("/search/ing/1")
    public static String searchByIng1(String s) {
        return recieptsServices.searchReciept(s);
    }

    @GetMapping("/search/ing/2")
    public static String searchByIng1(String s1, String s2) {
        return recieptsServices.searchReciept(s1, s2);
    }

    @GetMapping("/search/ing/3")
    public static String searchByIng3(String s1, String s2, String s3) {
        return recieptsServices.searchReciept(s1, s2, s3);
    }
}