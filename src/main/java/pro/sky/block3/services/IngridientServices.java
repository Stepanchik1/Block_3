package pro.sky.block3.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.controllers.model.Reciept;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class IngridientServices {

    private static int icount = 0;

    private LinkedHashMap<Integer, Ingridient> ingridientsMap = new LinkedHashMap<>();

    private final FileService fileService;

    public IngridientServices(FileService fileService) {
        this.fileService = fileService;
    }
@PostConstruct
    void first() {
        readFromFile();
    }

    public void createIngridient(String name, double count, String unit) {
        Ingridient ingridient = new Ingridient(count, name, unit);
        ingridient.setId(++icount);
        ingridientsMap.put(ingridient.getId(), ingridient);
    }

    public String createInController(String name, double count, String unit) {
        if (name == null || count == 0 || unit == null) {
            return "Укажите все поля ингридиента";
        }
        createIngridient(name, count, unit);
        System.out.println(ingridientsMap.get(ingridientsMap.size()));
        saveToFile();
        return ingridientsMap.get(ingridientsMap.size()).toString();
    }

    public void changeIngridient(int id, String name, double count, String unit) {
        if (ingridientsMap.get(id) != null) {
            Ingridient ingridient = new Ingridient(count, name, unit, id);
            ingridientsMap.put(id, ingridient);
        } else System.out.println("Под таким номером ингридиента нет");
    }

    public String changeInController(int id, String name, double count, String unit) {
        if (name == null || count == 0 || unit == null) {
            return "Укажите все поля ингридиента";
        }
        if (ingridientsMap.get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        changeIngridient(id, name, count, unit);
        System.out.println(ingridientsMap.get(id));
        saveToFile();
        return "Ингридиент изменен: " + ingridientsMap.get(id).toString();
    }

    public void deleteIngridient(int id) {
        if (ingridientsMap.get(id) != null) {
            ingridientsMap.remove(id);
        } else {
            System.out.println("Под таким номером нет ингридиента");
        }
    }

    public String deleteInController(int id) {
        if (ingridientsMap.get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        Ingridient ingridient = ingridientsMap.get(id);
        deleteIngridient(id);
        System.out.println(ingridient + " = " + ingridientsMap.get(id));
        saveToFile();
        return "Ингридиент удален: " + ingridient;
    }

    public String searchIngridient(int id) {
        if (ingridientsMap.get(id) != null) {
            return ingridientsMap.get(id).toString();
        } else {
            return "Под таким номером нет ингридиента";
        }
    }
    public Ingridient getIngridient (int id) {
        if (ingridientsMap.get(id) != null) {
            return ingridientsMap.get(id);
        } else {
            System.out.println("Под таким id нет ингридиента");
            return null;
        }
    }


    public String searchInController(int id) {
        if (ingridientsMap.get(id) == null) {
            return "Ингридиента по данному id нет";
        }
        searchIngridient(id);
        return "Найден ингридиент: " + ingridientsMap.get(id);
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

    public String list() {
        if (ingridientsMap.isEmpty()) {
            return "Ингридиентов нет";
        }
        return ingridientsMap.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    private void saveToFile() {
        try {
            fileService.classType = 0;
            String string = new ObjectMapper().writeValueAsString(ingridientsMap);
            boolean b = fileService.saveToFile(string);
            if (b) {System.out.println("Карта ингридиентов успешно сохранена");}
            else {
                System.out.println("Не удалось сохранить карту ингридиентов в файл");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            fileService.classType = 0;
            String string = fileService.readFile();
            ingridientsMap = new ObjectMapper().readValue(string, new TypeReference<LinkedHashMap < Integer, Ingridient>>(){});
        } catch (IOException e) {throw new RuntimeException(e);}
    }

}
