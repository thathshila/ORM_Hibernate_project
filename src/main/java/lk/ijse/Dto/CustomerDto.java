package lk.ijse.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lk.ijse.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerDto {
    private  String name;
    private  String address;
    private  String contact;
}
