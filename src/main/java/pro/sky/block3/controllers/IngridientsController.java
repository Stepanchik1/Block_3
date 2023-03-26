package pro.sky.block3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.block3.Block3Application;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.services.IngridientServices;
import pro.sky.block3.services.RecieptsServices;


import javax.annotation.PostConstruct;
import java.util.ArrayList;

@RestController
@RequestMapping("/ingridients")
@Tag(name = "Ингридиенты", description = "Операции со списком ингридиентов")

public class IngridientsController {

    private final IngridientServices ingridientServices;

    public IngridientsController(IngridientServices ingridientServices) {
        this.ingridientServices = ingridientServices;
    }

    @PostConstruct
            void first() {
        ingridientServices.createIngridient("яйца", 2, "шт.");
        ingridientServices.createIngridient("молоко", 0.5, "л.");
        ingridientServices.createIngridient("курица", 200, "гр.");
        ingridientServices.createIngridient("соль", 1, "по вкусу");
    }
    @GetMapping("/create")
    @Operation(summary = "Создание ингридиента", description = "Создается по наименованию продукта, количеству и единице измерения")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ингридиент создан"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "ингридиент не создан"
            )
    })
    public String createIng(String name, double count, String unit) {
        return ingridientServices.createInController(name, count, unit);
    }

    @GetMapping("/change")
    @Operation (summary = "Замена ингридиента", description = "Полностью заменяет продукт, количество и единицу измерения ингридиента")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "ингридиент изменен"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "ингридиент не изменен"
            )
    })
    public String changeIng(int id, String name, double count, String unit) {
        return ingridientServices.changeInController(id, name, count, unit);
    }

    @GetMapping("/delete")
    @Operation (summary = "Удаление ингридиента", description = "Удаляет ингридиент из списка ингридиентов")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "ингридиент удален"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "ингридиент не удален"
            )
    })
    public String deleteIng(int id) {
        return ingridientServices.deleteInController(id);
    }

    @GetMapping("/search/id")
    @Operation (summary = "Поиск ингридиента по id", description = "Выводит ингридиент из списка ингридиентов с указанным id")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "прошло успешно"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "не прошло"
            )
    })
    public String searchIngId(int id) {
        return ingridientServices.searchInController(id);
    }

    @GetMapping("/search")
    @Operation (summary = "Поиск ингридиента по названию", description = "Выводит все ингридиенты из списка, содержащие в названии данную строку")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "прошло успешно"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "не прошло"
            )
    })
    public String searchIngridient(String string) {
        return this.ingridientServices.searchIngridient(string);
    }

    @GetMapping("/list")
    @Operation (summary = "Список ингридиентов", description = "Выводит весь список ингридиентов")
    @ApiResponses (value = {
            @ApiResponse (
                    responseCode = "200",
                    description = "прошло"
            ),
            @ApiResponse (
                    responseCode = "404",
                    description = "не прошло"
            )
    })
    public String listOfIng() {
        return ingridientServices.list();
    }
}
