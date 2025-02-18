package com.order.ms2;


import com.order.ms2.dto.OrderDTO;
import com.order.ms2.entity.ItemEntity;
import com.order.ms2.entity.OrderStatus;

public class TestUtils {
    public OrderDTO createSampleOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setClientId("99999212-d4fa-9659-32c7-3d7c65e2a70a");
        orderDTO.setStatus(OrderStatus.PROCESSING);
        ItemEntity item = new ItemEntity();
        item.setName("Product a");
        item.setQuantity(13);
        item.setPrice(10.00);
        orderDTO.getItems().add(item);
        item = new ItemEntity();
        item.setName("Product b");
        item.setQuantity(3);
        item.setPrice(20.00);
        orderDTO.getItems().add(item);
        item = new ItemEntity();
        item.setName("Product c");
        item.setQuantity(5);
        item.setPrice(30.00);
        orderDTO.getItems().add(item);
        return orderDTO;
    }
}
