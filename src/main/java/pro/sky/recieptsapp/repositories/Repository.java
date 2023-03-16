package pro.sky.recieptsapp.repositories;

import pro.sky.recieptsapp.model.Ingridient;
import pro.sky.recieptsapp.model.Reciept;

import java.util.LinkedHashMap;

public class Repository {
    public static int rcount = 0;

    public static int icount = 0;
    public static LinkedHashMap<Integer, Reciept> recieptsMap = new LinkedHashMap<>();
    public static LinkedHashMap<Integer, Ingridient> ingridientsMap = new LinkedHashMap<>();


}
