package pro.sky.recieptsapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Reciept {
    private String name;
    private int cookingTime;
    private ArrayList <Ingridient> ingridients;
    private String[] instructions;
}
