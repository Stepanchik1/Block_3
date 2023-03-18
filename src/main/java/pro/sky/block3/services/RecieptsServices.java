package pro.sky.block3.services;

import org.springframework.stereotype.Service;
import pro.sky.block3.controllers.model.Ingridient;
import pro.sky.block3.controllers.model.Reciept;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Service
public class RecieptsServices {

    private static int rcount = 0;

    private static LinkedHashMap<Integer, Reciept> recieptsMap = new LinkedHashMap<>();

    public static LinkedHashMap<Integer, Reciept> getRecieptsMap() {
        return recieptsMap;
    }

    public static void createReciept(String name, int time, ArrayList<Ingridient> list, String[] instructions) {
        if (name == null || list == null || instructions == null) {
            System.out.println("Все поля рецепта должны быть полностью заполнены");
            return;
        }
        Reciept reciept = new Reciept(name, time, list, instructions, ++rcount);
        recieptsMap.put(reciept.getId(), reciept);
    }

    public static void createReciept(String name, int time, ArrayList<Ingridient> list, String instruction) {
        if (name == null || list == null || instruction == null) {
            System.out.println("Все поля рецепта должны быть полностью заполнены");
            return;
        }
        String[] instructions = new String[]{instruction};
        Reciept reciept = new Reciept(name, time, list, instructions, ++rcount);
        recieptsMap.put(reciept.getId(), reciept);
    }

    public static void createReciept(String name, int time, int iding, String instruction) {
        if (name == null || instruction == null) {
            System.out.println("Все поля рецепта должны быть полностью заполнены");
            return;
        }
        if (IngridientServices.getIngridientsMap().get(iding) == null) {
            System.out.println("Такого ингридиента в списке нет");
            return;
        }
        String[] instructions = new String[]{instruction};
        ArrayList<Ingridient> ingridients = new ArrayList<>();
        ingridients.add(IngridientServices.getIngridientsMap().get(iding));
        Reciept reciept = new Reciept(name, time, ingridients, instructions, ++rcount);
        recieptsMap.put(reciept.getId(), reciept);
    }

    public static void changeRecieptName(int id, String name) {
        if (name == null) {
            System.out.println("Поле имя должно быть заполнено");
            return;
        }
        try {
            recieptsMap.get(id).setName(name);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void changeRecieptCookingTime(int id, int time) {
        if (time < 0) {
            System.out.println("Неверно задан формат времени готовки");
            return;
        }
        try {
            recieptsMap.get(id).setCookingTime(time);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void changeRecieptIngridients(int id, int index, Ingridient ingridient) {
        if (ingridient == null) {
            System.out.println("Поле ингридиенты должно быть заполнено");
            return;
        }
        try {
            recieptsMap.get(id).getIngridients().set(index, ingridient);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        } catch (IndexOutOfBoundsException h) {
            System.out.println("Неверно указан номер ингридиента");
        }
    }

    public static void changeRecieptIngridients(int id, Ingridient[] ingridients) {
        if (ingridients == null) {
            System.out.println("Поле ингридиенты должно быть заполнено");
            return;
        }
        try {
            ArrayList<Ingridient> list = (ArrayList<Ingridient>) Arrays.stream(ingridients).collect(Collectors.toList());
            recieptsMap.get(id).setIngridients(list);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void addIngridient(int id, Ingridient ingridient) {
        if (ingridient == null) {
            System.out.println("Поле ингридиенты должно быть заполнено");
            return;
        }
        try {
            recieptsMap.get(id).getIngridients().add(ingridient);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void addIngridient(int id, int idIngridient) {
        if (IngridientServices.getIngridientsMap().get(idIngridient) == null) {
            System.out.println("Такого ингридиента в списке ингридиентов нет");
            return;
        }
        try {
            recieptsMap.get(id).getIngridients().add(IngridientServices.getIngridientsMap().get(idIngridient));
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }


    public static void deleteIngridient(int id, String ingridient) {
        if (ingridient == null) {
            System.out.println("Поле ингридиенты должно быть заполнено");
            return;
        }
        int a = 0;
        try {
            for (int i = 0; i < recieptsMap.get(id).getIngridients().size(); i++) {
                a++;
                if (recieptsMap.get(id).getIngridients().get(i).getName().trim().equals(ingridient.trim())) {
                    recieptsMap.get(id).getIngridients().remove(i);
                }
                if (a == 0) {
                    System.out.println("Список ингридиентов пуст");
                }
            }
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void deleteIngridient(int id, int index) {
        if (index <= 0) {
            System.out.println("Некорректно задан номер ингридиента");
            return;
        }
        if (recieptsMap.get(id).getIngridients() == null) {
            System.out.println("Список ингридиентов пуст");
            return;
        }

        if (recieptsMap.get(id).getIngridients().size() < index) {
            System.out.println("Под таким номером ингридиента нет");
            return;
        }

        try {
            recieptsMap.get(id).getIngridients().remove(index - 1);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void changeInstructions(int id, String[] instructions) {
        if (instructions == null) {
            System.out.println("Поле инструкции должно быть заполнено");
            return;
        }
        try {
            recieptsMap.get(id).setInstructions(instructions);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void addInstruction(int id, int index, String instruction) {
        if (instruction == null) {
            System.out.println("Поле инструкция должно быть заполнено");
            return;
        }
        try {
            ArrayList<String> list = (ArrayList<String>)
                    Arrays.stream(recieptsMap.get(id).getInstructions()).collect(Collectors.toList());
            list.add(index, instruction);
            String[] newInstructions = list.toArray(new String[list.size()]);
            recieptsMap.get(id).setInstructions(newInstructions);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void addInstruction(int id, String instruction) {
        if (instruction == null) {
            System.out.println("Поле инструкция должно быть заполнено");
            return;
        }
        try {
            String[] newInstructions = new String[recieptsMap.get(id).getInstructions().length + 1];
            for (int i = 0; i < recieptsMap.get(id).getInstructions().length; i++) {
                newInstructions[i] = recieptsMap.get(id).getInstructions()[i];
            }
            newInstructions[recieptsMap.get(id).getInstructions().length] = instruction;
            recieptsMap.get(id).setInstructions(newInstructions);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void deleteInstruction(int id, int index) {
        if (index <= 0) {
            System.out.println("Некорректно задан номер инструкции");
            return;
        }
        if (recieptsMap.get(id) == null) {
            System.out.println("По такому номеру рецептов нет");
            return;
        }
        if (recieptsMap.get(id).getInstructions() == null) {
            System.out.println("Список инструкций пуст");
            return;
        }
        if (recieptsMap.get(id).getInstructions().length < index) {
            System.out.println("Инструкции под данным номером нет");
            return;
        }
        String[] strings = new String[recieptsMap.get(id).getInstructions().length - 1];
        recieptsMap.get(id).getInstructions()[index - 1] = null;
        int a = 0;
        for (int i = 0; i < recieptsMap.get(id).getInstructions().length; i++) {
            if (recieptsMap.get(id).getInstructions()[i] != null) {
                strings[a] = recieptsMap.get(id).getInstructions()[i];
                a++;
            }
        }
        recieptsMap.get(id).setInstructions(strings);
    }

    public static String searchReciept(int id) {
        if (recieptsMap.get(id) != null) {
            return recieptsMap.get(id).toString();
        } else return "Под таким номером рецепта нет";
    }

    public static String searchReciept(String s) {
        if (RecieptsServices.getRecieptsMap() == null || RecieptsServices.getRecieptsMap().isEmpty()) {
            return "Список рецептов пуст";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= RecieptsServices.getRecieptsMap().size(); i++) {
            for (Ingridient j : RecieptsServices.getRecieptsMap().get(i).getIngridients()) {
                if (j.getName().trim().toLowerCase().equals(s.trim().toLowerCase())) {
                    al.add(RecieptsServices.getRecieptsMap().get(i));
                    break;
                }
            }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    public static String searchReciept(String s1, String s2) {
        if (RecieptsServices.getRecieptsMap() == null || RecieptsServices.getRecieptsMap().isEmpty()) {
            return "Список рецептов пуст";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= RecieptsServices.getRecieptsMap().size(); i++) {
            for (Ingridient j : RecieptsServices.getRecieptsMap().get(i).getIngridients()) {
                if (j.getName().trim().toLowerCase().equals(s1.trim().toLowerCase()) || j.getName().trim().toLowerCase().equals(s2.trim().toLowerCase())) {
                    al.add(RecieptsServices.getRecieptsMap().get(i));
                    break;
                }
            }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }

    public static String searchReciept(String s1, String s2, String s3) {
        if (RecieptsServices.getRecieptsMap() == null || RecieptsServices.getRecieptsMap().isEmpty()) {
            return "Список рецептов пуст";
        }
        ArrayList<Reciept> al = new ArrayList<>();
        for (int i = 1; i <= RecieptsServices.getRecieptsMap().size(); i++) {
            for (Ingridient j : RecieptsServices.getRecieptsMap().get(i).getIngridients()) {
                if (j.getName().trim().toLowerCase().equals(s1.trim().toLowerCase())||j.getName().trim().toLowerCase().equals(s2.trim().toLowerCase())||j.getName().trim().toLowerCase().equals(s3.trim().toLowerCase())) {
                    al.add(RecieptsServices.getRecieptsMap().get(i));
                    break;
                }
            }
        }
        if (al.isEmpty()) {
            return "Рецепты не найдены";
        }
        return al.toString().replace("{", "").replace("}", "").replace("=", ") ");
    }
}





