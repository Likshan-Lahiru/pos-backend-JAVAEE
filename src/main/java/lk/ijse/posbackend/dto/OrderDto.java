package lk.ijse.posbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto implements Serializable {
    private String id;
    private String date;
    private double discount_value;
    private double sub_total;
    private String customer_id;
}