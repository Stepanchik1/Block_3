package pro.sky.recieptsapp.services;

import org.springframework.stereotype.Service;
import pro.sky.recieptsapp.model.Ingridient;
import pro.sky.recieptsapp.repositories.Repository;

import java.util.ArrayList;

@Service
public class IngridientServices {
    public static void createIngridient (String name, double count, String unit) {
        Ingridient ingridient = new Ingridient(count, name, unit) ;
        ingridient.setId(++Repository.icount);
        Repository.ingridientsMap.put(Repository.icount, ingridient);
    }

    public static void changeIngridient (int id, String name, double count, String unit) {
        if (Repository.ingridientsMap.get(id) != null) {
        Ingridient ingridient = new Ingridient(count, name, unit, id) ;
        Repository.ingridientsMap.remove(id);
        Repository.ingridientsMap.put(id, ingridient);}
        else System.out.println("Под таким номером ингридиента нет");
    }

public static void deleteIngridient (int id) {
       if (Repository.ingridientsMap.get(id) != null) {
           Repository.ingridientsMap.remove(id);
       } else {
           System.out.println("Под таким номером нет ингридиента");}
}

public static String searchIngridient (int id) {
    if (Repository.ingridientsMap.get(id) != null) {
        return Repository.ingridientsMap.get(id).toString();
    } else {
    return "Под таким номером нет ингридиента";}
}



}
