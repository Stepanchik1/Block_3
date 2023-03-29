package pro.sky.block3.controllers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingridient {
    private double count;
    private String name;
    private String unit;
    private int id = 0;

    public Ingridient(double count, String name, String unit) {
        this.count = count;
        this.name = name;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return name + " - " +
                count + " " + unit + " (id - " + id + ")";
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
