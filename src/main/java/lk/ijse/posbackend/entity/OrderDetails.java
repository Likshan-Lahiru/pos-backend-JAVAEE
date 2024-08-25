package lk.ijse.posbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails implements Serializable {
    private String order_id;
    private String item_id;
    private int qty;
    private double unit_price;
    private double total;

}