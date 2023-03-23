package pro.sky.block3.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.block3.Block3Application;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.controllers.model.Reciept;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;


import java.util.ArrayList;


@RestController
@RequestMapping("/reciepts")
public class RecieptsController {

    private final static RecieptsServices recieptsServices = Block3Application.recieptsServices; //Чтоб был список пробных рецептов

    @GetMapping("/create")
    public static String newReciept(@RequestParam String name, @RequestParam int time, @RequestParam int id, @RequestParam String inst) {
        return recieptsServices.createRecieptInController(name, time, id, inst, IngridientsController.getIngridientServices().getIngridientsMap());
    }

    @GetMapping("/change/name")
    public static String changeName(@RequestParam int id, @RequestParam String name) {
        return recieptsServices.changeNameToController(id, name);
    }

    @GetMapping("/change/time")
    public static String changeTime(@RequestParam int id, @RequestParam int time) {
        return recieptsServices.changeTimeToController(id, time);
    }

    @GetMapping("/add/ingr")
    public static String addIngridient(@RequestParam int id, @RequestParam int index) {
        return recieptsServices.addIngridientToController(id, index, IngridientsController.getIngridientServices().getIngridientsMap());
    }

    @GetMapping("/add/inst")
    public static String addInstruction(@RequestParam int id, @RequestParam String instr) {
        return recieptsServices.addInstructionToController(id, instr);
    }

    @GetMapping("/delete/ingr")
    public static String deleteIngridient(@RequestParam int id, @RequestParam int number) {
        return recieptsServices.deleteIngridientInController(id, number);
    }

    @GetMapping("/delete/inst")
    public static String deleteInstruction(@RequestParam int id, @RequestParam int number) {
        return recieptsServices.deleteInstructionInController(id, number);
    }

    @GetMapping("/search")
    public static String searchReciept(@RequestParam int id) {
        return recieptsServices.searchInController(id);
    }

    @GetMapping("/list")
    public static String listOfReciepts() {
        return recieptsServices.list();
    }

    @GetMapping("/search/ing/id")
    public static String searchByIng(int id) {
        return recieptsServices.searchingIngByIdToController(id, IngridientsController.getIngridientServices().getIngridientsMap());
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