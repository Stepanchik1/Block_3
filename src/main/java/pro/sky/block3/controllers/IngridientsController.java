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

    private final IngridientServices ingridientServices;

    public IngridientsController(IngridientServices ingridientServices) {
        this.ingridientServices = ingridientServices;
    }

    @GetMapping("/create")
    public String createIng(String name, double count, String unit) {
        return ingridientServices.createInController(name, count, unit);
    }

    @GetMapping("/change")
    public String changeIng(int id, String name, double count, String unit) {
        return ingridientServices.changeInController(id, name, count, unit);
    }

    @GetMapping("/delete")
    public String deleteIng(int id) {
        return ingridientServices.deleteInController(id);
    }

    @GetMapping("/search/id")
    public String searchIngId(int id) {
        return ingridientServices.searchInController(id);
    }

    @GetMapping("/search")
    public String searchIngridient(String string) {
        return this.ingridientServices.searchIngridient(string);
    }

    @GetMapping("/list")
    public String listOfIng() {
        return ingridientServices.list();
    }
}
