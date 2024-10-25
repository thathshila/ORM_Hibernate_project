package lk.ijse.Entity;



import jakarta.persistence.*;
import lombok.*;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String contact;
}
