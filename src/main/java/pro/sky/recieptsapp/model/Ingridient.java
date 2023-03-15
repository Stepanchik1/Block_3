package pro.sky.recieptsapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingridient {
    private int count;
    private String name;
    private String unit;
}
