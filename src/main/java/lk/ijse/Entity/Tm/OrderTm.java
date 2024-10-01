package lk.ijse.Entity.Tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderTm {
    private String itemName;
    private int quantity;
    private double price;
    private double total;
    private Button btnRemove;
}
