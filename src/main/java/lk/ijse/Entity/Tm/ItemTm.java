package lk.ijse.Entity.Tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemTm {
    private Long code;
    private String name;
    private double price;
    private int qty;
}
