package pro.sky.block3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.controllers.model.Reciept;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;


import java.util.ArrayList;


@RestController
@RequestMapping("/reciepts")
public class RecieptsController {


    @GetMapping("/create")
    public static String newReciept(@RequestParam String name, @RequestParam int time, @RequestParam int id, @RequestParam String inst) {
        RecieptsServices recieptsServices = new RecieptsServices();
        recieptsServices.createReciept(name, time, id, inst);
        System.out.println(RecieptsServices.getRecieptsMap().get(id).toString());
        return "Создан рецепт:\n" + RecieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/change/name")
    public static String changeName(@RequestParam int id, @RequestParam String name) {
        if (RecieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices recieptsServices = new RecieptsServices();
       recieptsServices.changeRecieptName(id, name);
        System.out.println(RecieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + RecieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/change/time")
    public static String changeTime(@RequestParam int id, @RequestParam int time) {
        if (RecieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices recieptsServices = new RecieptsServices();
        recieptsServices.changeRecieptCookingTime(id, time);
        System.out.println(RecieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + RecieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/add/ingr")
    public static String addIngridient(@RequestParam int id, @RequestParam int index) {
        if (RecieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices recieptsServices = new RecieptsServices();
        recieptsServices.addIngridient(id, index);
        System.out.println(RecieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + RecieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/add/inst")
    public static String addInstruction(@RequestParam int id, @RequestParam String instr) {
        if (RecieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices recieptsServices = new RecieptsServices();
        recieptsServices.addInstruction(id, instr);
        System.out.println(RecieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + RecieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/delete/ingr")
    public static String deleteIngridient(@RequestParam int id, @RequestParam int number) {
        if (RecieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        if (RecieptsServices.getRecieptsMap().get(id).getIngridients().size() < number) {
            return "Под таким номером ингридиента нет";
        }
        RecieptsServices recieptsServices = new RecieptsServices();
        recieptsServices.deleteIngridient(id, number);
        System.out.println(RecieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + RecieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/delete/inst")
    public static String deleteInstruction(@RequestParam int id, @RequestParam int number) {
        if (RecieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        if (RecieptsServices.getRecieptsMap().get(id).getInstructions().length < number) {
            return "Под таким номером инструкции нет";
        }
        RecieptsServices recieptsServices = new RecieptsServices();
        recieptsServices.deleteInstruction(id, number);
        System.out.println(RecieptsServices.getRecieptsMap().get(id));
        return "Рецепт изменен:\n" + RecieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/search")
    public static String searchReciept(@RequestParam int id) {
        if (RecieptsServices.getRecieptsMap().get(id) == null) {
            return "По такому id рецептов не найдено";
        }
        RecieptsServices recieptsServices = new RecieptsServices();
        recieptsServices.searchReciept(id);
        System.out.println(RecieptsServices.getRecieptsMap().get(id));
        return "Найден рецепт: " + RecieptsServices.getRecieptsMap().get(id).toString();
    }

    @GetMapping("/list")
    public static String listOfReciepts() {
        if (RecieptsServices.getRecieptsMap().isEmpty()) {
            return "Рецептов нет";
        }
        return RecieptsServices.getRecieptsMap().toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    @GetMapping("/search/ing/id")
    public static String searchByIng(int id) {
        if (IngridientServices.getIngridientsMap().get(id) == null) {
            return "По такому id ингридиента нет";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= RecieptsServices.getRecieptsMap().size(); i++) {
            for (Ingridient j : RecieptsServices.getRecieptsMap().get(i).getIngridients())
                if (j.getId() == id) {
                    al.add(RecieptsServices.getRecieptsMap().get(i));
                }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    @GetMapping("/search/ing/1")
    public static String searchByIng1(String s) {
        RecieptsServices recieptsServices = new RecieptsServices();
        return recieptsServices.searchReciept(s);
    }

    @GetMapping("/search/ing/2")
    public static String searchByIng1(String s1, String s2) {
        RecieptsServices recieptsServices = new RecieptsServices();
        return recieptsServices.searchReciept(s1, s2);
    }

    @GetMapping("/search/ing/3")
    public static String searchByIng3(String s1, String s2, String s3) {
        RecieptsServices recieptsServices = new RecieptsServices();
        return recieptsServices.searchReciept(s1, s2, s3);
    }
}