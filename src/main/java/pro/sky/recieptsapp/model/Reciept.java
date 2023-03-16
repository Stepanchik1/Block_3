package pro.sky.recieptsapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@Data
@AllArgsConstructor
public class Reciept {
    private String name;
    private int cookingTime;
    private ArrayList<Ingridient> ingridients;
    private String[] instructions;
    private int id = 0;

    public Reciept(String name, int cookingTime, ArrayList<Ingridient> ingridients, String[] instructions) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingridients = ingridients;
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Рецепт № " + id +
                " \"" + name + "\" " +
                "(время приготовления - " + cookingTime +
                " мин.):\n ингридиенты:\n " + ingToString(ingridients.toArray((new Ingridient[ingridients.size()]))) +
                "\n инструкции:\n " + stringsToString(instructions) + ".";
    }

    public static String ingToString(Ingridient[] ingridients) {
        String s1 = Arrays.toString(ingridients);
        String s2 = s1.replace("[", "").replace("]", "");
        String s3 = s2.replace(",", ",\n");
        return s3;
    }

    public static String stringsToString(String[] strings) {
        String s1 = Arrays.toString(strings);
        String s2 = s1.replace("[", "").replace("]", "");
        String s3 = s2.replace(",", ",\n");
        return s3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public ArrayList<Ingridient> getIngridients() {
        return ingridients;
    }

    public void setIngridients(ArrayList<Ingridient> ingridients) {
        this.ingridients = ingridients;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
