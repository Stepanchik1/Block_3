package pro.sky.recieptsapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RestController
public class MyFirstController {

    static Project project1 = new Project("Степа","Кулинария");
    static String description = "Вот тут я буду заниматься всякими рецептами";


    @GetMapping("/")
    public static String string1 () {
        return "Приложение запущено";
    }


    @GetMapping("/info")
    public static String string2 (@RequestParam String string) {
        project1.setDescription(description);
        if (string==null||string.trim().isEmpty()) {
        return project1.toString();} else {return project1.toString()+",\nДополнительная информация:\n"+string;}
    }

}
