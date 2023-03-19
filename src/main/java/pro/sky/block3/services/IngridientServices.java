package pro.sky.block3.services;

import org.springframework.stereotype.Service;
import pro.sky.block3.controllers.model.Ingridient;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class IngridientServices {

    private static int icount = 0;

    private LinkedHashMap<Integer, Ingridient> ingridientsMap = new LinkedHashMap<>();

    public LinkedHashMap<Integer, Ingridient> getIngridientsMap() {
        return ingridientsMap;
    }

    public void createIngridient(String name, double count, String unit) {
        Ingridient ingridient = new Ingridient(count, name, unit);
        ingridient.setId(++icount);
        ingridientsMap.put(ingridient.getId(), ingridient);
    }

    public void changeIngridient(int id, String name, double count, String unit) {
        if (ingridientsMap.get(id) != null) {
            Ingridient ingridient = new Ingridient(count, name, unit, id);
            ingridientsMap.put(id, ingridient);
        } else System.out.println("Под таким номером ингридиента нет");
    }

    public void deleteIngridient(int id) {
        if (ingridientsMap.get(id) != null) {
            ingridientsMap.remove(id);
        } else {
            System.out.println("Под таким номером нет ингридиента");
        }
    }

    public String searchIngridient(int id) {
        if (ingridientsMap.get(id) != null) {
            return ingridientsMap.get(id).toString();
        } else {
            return "Под таким номером нет ингридиента";
        }
    }

    public String searchIngridient(String string) {
        ArrayList<Ingridient> al = new ArrayList<>();
        if (this.ingridientsMap.isEmpty()) {
            for (Ingridient j : this.ingridientsMap.values()) {
                if (j.getName().trim().toLowerCase().equals(string.trim().toLowerCase())) {
                    al.add(j);
                }
            }
            if (al.isEmpty()) {
                return "Ингридиенты не найдены";
            }
            return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
        } else {
            return "Список ингридиентов пуст";
        }
    }
}
