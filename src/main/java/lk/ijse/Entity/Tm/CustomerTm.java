package lk.ijse.Entity.Tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerTm {
    private Long id;
    private String name;
    private String address;
    private String contact;
}
