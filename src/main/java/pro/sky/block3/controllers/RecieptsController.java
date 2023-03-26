package pro.sky.block3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Рецепты", description = "Создание, изменение, поиск и удаление рецептов")
public class RecieptsController {

    private final RecieptsServices recieptsServices;
    private final IngridientServices ingridientServices;

    public RecieptsController(RecieptsServices recieptsServices, IngridientServices ingridientServices) {
        this.recieptsServices = recieptsServices;
        this.ingridientServices = ingridientServices;
    }

    @PostConstruct
    void first() {Ingridient[] ingridients = new Ingridient[]{ingridientServices.getIngridient(3), ingridientServices.getIngridient(4)};
        ArrayList<Ingridient> ing = new ArrayList<>();
        ing.add(ingridientServices.getIngridient(1));
        ing.add(ingridientServices.getIngridient(2));
        ing.add(ingridientServices.getIngridient(3));
        String[] strings = {"a", "b", "c"};
        recieptsServices.createReciept("пробный рецепт", 23, ing, strings);
        recieptsServices.createReciept("пробный рецепт №2", 5, 3, "Приготовить и съесть");
        recieptsServices.createReciept("пробный рецепт №3", 11, quickCast(ingridients), "Подавиться");}

    @GetMapping("/create")
    @Operation (summary = "Создание рецепта", description = "Создается по названию, времени приготовления, одному ингридиенту и одной инструкции. Остальные ингридиентыи инструкции надо добавлять потом")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "рецепт создан"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "рецепт не создан"
            )
    })
    public String newReciept(@RequestParam String name, @RequestParam int time, @RequestParam int id, @RequestParam String inst) {
        return recieptsServices.createRecieptInController(name, time, id, inst);
    }

    @GetMapping("/change/name")
    @Operation (summary = "Изменение имени рецепта", description = "Меняет имя на новое")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "имя изменено"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "имя не измененео"
            )})
    public String changeName(@RequestParam int id, @RequestParam String name) {
        return recieptsServices.changeNameToController(id, name);
    }

    @GetMapping("/change/time")
    @Operation (summary = "Изменение времени приготовления", description = "Меняет время на новое")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "время изменено"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "время не изменено"
            )})
    public String changeTime(@RequestParam int id, @RequestParam int time) {
        return recieptsServices.changeTimeToController(id, time);
    }

    @GetMapping("/add/ingr")
    @Operation (summary = "Добавление ингридиента", description = "Добавляет в рецепт еще один ингридиент")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "ингридиент добавлен"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "ингридиент не добавлен"
            )})
    public String addIngridient(@RequestParam int id, @RequestParam int index) {
        return recieptsServices.addIngridientToController(id, index);
    }

    @GetMapping("/add/inst")
    @Operation (summary = "Добавление инструкции", description = "Добавляет еще один шаг в инструкции по приготовлению рецепта")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "инструкция добавлена"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "инструкция не добавлена"
            )})
    public String addInstruction(@RequestParam int id, @RequestParam String instr) {
        return recieptsServices.addInstructionToController(id, instr);
    }

    @GetMapping("/delete/ingr")
    @Operation (summary = "Удаление ингридиента", description = "удаляет ингридиент из рецепта")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "ингридиент удален"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "ингридиент не удален"
            )})
    public String deleteIngridient(@RequestParam int id, @RequestParam int number) {
        return recieptsServices.deleteIngridientInController(id, number);
    }

    @GetMapping("/delete/inst")
    @Operation (summary = "Удаление инструкции", description = "Удаляет шаг из инструкции по приготовлению рецепта")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "инструкция удалена"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "инструкция не удалена"
            )})
    public String deleteInstruction(@RequestParam int id, @RequestParam int number) {
        return recieptsServices.deleteInstructionInController(id, number);
    }

    @GetMapping("/search")
    @Operation (summary = "Поиск рецепта", description = "Находит рецепт всписке рецептов по id")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "поиск осуществлен успешно"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "ошибка поиска"
            )})
    public String searchReciept(@RequestParam int id) {
        return recieptsServices.searchInController(id);
    }

    @GetMapping("/list")
    @Operation (summary = "Список рецептов", description = "Выводит все рецепты, которые есть в списке")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "прошло успешно"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "прошло неуспешно"
            )})
    public String listOfReciepts() {
        return recieptsServices.list();
    }

    @GetMapping("/search/ing/id")
    @Operation (summary = "Поиск рецепта по id ингридиента", description = "Выводит все рецепты, содержащие данный ингридиент заданного id")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "прошло успешно"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "прошло неуспешно"
            )})
    public String searchByIng(int id) {
        return recieptsServices.searchingIngByIdToController(id);
    }

    @GetMapping("/search/ing/1")
    @Operation (summary = "Поиск рецепта по одной строчке ингридиента", description = "Выводит все рецепты, названия ингридиентов которых содержат данную строку")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "прошло"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "не прошло"
            )})
    public String searchByIng1(String s) {
        return recieptsServices.searchReciept(s);
    }

    @GetMapping("/search/ing/2")
    @Operation (summary = "Поиск рецепта по двум строчкам ингридиента", description = "Выводит все рецепты, названия ингридиентов которых содержат данные две строки")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "прошло"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "не прошло"
            )})
    public String searchByIng1(String s1, String s2) {
        return recieptsServices.searchReciept(s1, s2);
    }

    @GetMapping("/search/ing/3")
    @Operation (summary = "Поиск рецепта по трем строчкам ингридиента", description = "Выводит все рецепты, названия ингридиентов которых содержат данные три строки")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "прошло"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "не прошло"
            )})
    public String searchByIng3(String s1, String s2, String s3) {
        return recieptsServices.searchReciept(s1, s2, s3);
    }

    public static ArrayList<Ingridient> quickCast(Ingridient[] ingridients) {
        return (ArrayList<Ingridient>) Arrays.stream(ingridients).collect(Collectors.toList());
    }

}