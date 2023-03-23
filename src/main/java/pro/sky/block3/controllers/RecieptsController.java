package pro.sky.block3.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.block3.Block3Application;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.controllers.model.Reciept;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/reciepts")
public class RecieptsController {

    private final RecieptsServices recieptsServices;
    private final IngridientServices ingridientServices;

    public RecieptsController(RecieptsServices recieptsServices, IngridientServices ingridientServices) {
        this.recieptsServices = recieptsServices;
        this.ingridientServices = ingridientServices;
    }

    @PostConstruct
    void first() {Ingridient[] ingridients = new Ingridient[]{ingridientServices.getIngridientsMap().get(3), ingridientServices.getIngridientsMap().get(4)};
        ArrayList<Ingridient> ing = new ArrayList<>();
        ing.add(ingridientServices.getIngridientsMap().get(1));
        ing.add(ingridientServices.getIngridientsMap().get(2));
        ing.add(ingridientServices.getIngridientsMap().get(3));
        String[] strings = {"a", "b", "c"};
        recieptsServices.createReciept("пробный рецепт", 23, ing, strings);
        recieptsServices.createReciept("пробный рецепт №2", 5, 3, "Приготовить и съесть", ingridientServices.getIngridientsMap());
        recieptsServices.createReciept("пробный рецепт №3", 11, quickCast(ingridients), "Подавиться");}

    @GetMapping("/create")
    public String newReciept(@RequestParam String name, @RequestParam int time, @RequestParam int id, @RequestParam String inst) {
        return recieptsServices.createRecieptInController(name, time, id, inst, ingridientServices.getIngridientsMap());
    }

    @GetMapping("/change/name")
    public String changeName(@RequestParam int id, @RequestParam String name) {
        return recieptsServices.changeNameToController(id, name);
    }

    @GetMapping("/change/time")
    public String changeTime(@RequestParam int id, @RequestParam int time) {
        return recieptsServices.changeTimeToController(id, time);
    }

    @GetMapping("/add/ingr")
    public String addIngridient(@RequestParam int id, @RequestParam int index) {
        return recieptsServices.addIngridientToController(id, index, ingridientServices.getIngridientsMap());
    }

    @GetMapping("/add/inst")
    public String addInstruction(@RequestParam int id, @RequestParam String instr) {
        return recieptsServices.addInstructionToController(id, instr);
    }

    @GetMapping("/delete/ingr")
    public String deleteIngridient(@RequestParam int id, @RequestParam int number) {
        return recieptsServices.deleteIngridientInController(id, number);
    }

    @GetMapping("/delete/inst")
    public String deleteInstruction(@RequestParam int id, @RequestParam int number) {
        return recieptsServices.deleteInstructionInController(id, number);
    }

    @GetMapping("/search")
    public String searchReciept(@RequestParam int id) {
        return recieptsServices.searchInController(id);
    }

    @GetMapping("/list")
    public String listOfReciepts() {
        return recieptsServices.list();
    }

    @GetMapping("/search/ing/id")
    public String searchByIng(int id) {
        return recieptsServices.searchingIngByIdToController(id, ingridientServices.getIngridientsMap());
    }

    @GetMapping("/search/ing/1")
    public String searchByIng1(String s) {
        return recieptsServices.searchReciept(s);
    }

    @GetMapping("/search/ing/2")
    public String searchByIng1(String s1, String s2) {
        return recieptsServices.searchReciept(s1, s2);
    }

    @GetMapping("/search/ing/3")
    public String searchByIng3(String s1, String s2, String s3) {
        return recieptsServices.searchReciept(s1, s2, s3);
    }

    public static ArrayList<Ingridient> quickCast(Ingridient[] ingridients) {
        return (ArrayList<Ingridient>) Arrays.stream(ingridients).collect(Collectors.toList());
    }

}