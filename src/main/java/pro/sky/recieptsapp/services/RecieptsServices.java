package pro.sky.recieptsapp.services;

import org.springframework.stereotype.Service;
import pro.sky.recieptsapp.model.Ingridient;
import pro.sky.recieptsapp.model.Reciept;
import pro.sky.recieptsapp.repositories.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
@Service
public class RecieptsServices {
    public static void createReciept(String name, int time, ArrayList<Ingridient> list, String[] instructions) {
        if (name == null || list == null || instructions == null) {
            System.out.println("Все поля рецепта должны быть полностью заполнены");
            return;
        }
        Reciept reciept = new Reciept(name, time, list, instructions);
        Repository.recieptsMap.put(++Repository.count, reciept);
    }

    public static void createReciept(String name, int time, ArrayList<Ingridient> list, String instruction) {
        if (name == null || list == null || instruction == null) {
            System.out.println("Все поля рецепта должны быть полностью заполнены");
            return;
        }
        String[] instructions = new String[]{instruction};
        Reciept reciept = new Reciept(name, time, list, instructions);
        Repository.recieptsMap.put(++Repository.count, reciept);
    }

    public static void changeRecieptName(int id, String name) {
        if (name == null) {
            System.out.println("Поле имя должно быть заполнено");
            return;
        }
        try {
            Repository.recieptsMap.get(id).setName(name);
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
            Repository.recieptsMap.get(id).setCookingTime(time);
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
            Repository.recieptsMap.get(id).getIngridients().set(index, ingridient);
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
            Repository.recieptsMap.get(id).setIngridients(list);
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
            Repository.recieptsMap.get(id).getIngridients().add(ingridient);
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
            for (int i = 0; i < Repository.recieptsMap.get(id).getIngridients().size(); i++) {
                a++;
                if (Repository.recieptsMap.get(id).getIngridients().get(i).getName().trim().equals(ingridient.trim())) {
                    Repository.recieptsMap.get(id).getIngridients().remove(i);
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
        if (index<=0) {
            System.out.println("Некорректно задан номер ингридиента") ; return;
        }
        if (Repository.recieptsMap.get(id).getIngridients() == null) {
            System.out.println("Список ингридиентов пуст");
            return;
        }

        if (Repository.recieptsMap.get(id).getIngridients().get(index) == null) {
            System.out.println("Под таким номером ингридиента нет");
            return;
        }

        try {
            Repository.recieptsMap.get(id).getIngridients().remove(index - 1);
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
            Repository.recieptsMap.get(id).setInstructions(instructions);
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
                    Arrays.stream(Repository.recieptsMap.get(id).getInstructions()).collect(Collectors.toList());
            list.add(index, instruction);
            String[] newInstructions = list.toArray(new String[list.size()]);
            Repository.recieptsMap.get(id).setInstructions(newInstructions);
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
            String[] newInstructions = new String[Repository.recieptsMap.get(id).getInstructions().length + 1];
            for (int i = 0; i < Repository.recieptsMap.get(id).getInstructions().length; i++) {
                newInstructions[i] = Repository.recieptsMap.get(id).getInstructions()[i];
            }
            newInstructions[Repository.recieptsMap.get(id).getInstructions().length] = instruction;
            Repository.recieptsMap.get(id).setInstructions(newInstructions);
        } catch (NullPointerException n) {
            System.out.println("По такому номеру рецептов нет");
        }
    }

    public static void deleteInstruction(int id, int index) {
        if (index<=0) {
            System.out.println("Некорректно задан номер инструкции"); return;}
        if (Repository.recieptsMap.get(id) == null) {
            System.out.println("По такому номеру рецептов нет");
            return;
        }
        if (Repository.recieptsMap.get(id).getInstructions() == null) {
            System.out.println("Список инструкций пуст");
            return;
        }
        if (Repository.recieptsMap.get(id).getInstructions().length < index) {
            System.out.println("Инструкции под данным номером нет");
            return;
        }
        String[] strings = new String[Repository.recieptsMap.get(id).getInstructions().length - 1];
        Repository.recieptsMap.get(id).getInstructions()[index - 1] = null;
        int a = 0;
        for (int i = 0; i < Repository.recieptsMap.get(id).getInstructions().length; i++) {
            if (Repository.recieptsMap.get(id).getInstructions()[i] != null) {
                strings[a] = Repository.recieptsMap.get(id).getInstructions()[i];
                a++;
            }
        }
        Repository.recieptsMap.get(id).setInstructions(strings);
    }
}


