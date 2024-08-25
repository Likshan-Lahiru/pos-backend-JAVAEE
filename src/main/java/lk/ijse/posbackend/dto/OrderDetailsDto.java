package lk.ijse.posbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDto implements Serializable {
    private String order_id;
    private String item_id;
    private int qty;
    private double unit_price;
    private double total;

}