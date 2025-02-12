package com.order.ms2.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.ms2.dto.OrderDTO;
import com.order.ms2.entity.ItemEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper = new ObjectMapper();
    OrderDTO orderDTO = null;

    public OrderDTO receiveMessage(String message) throws JsonProcessingException, InterruptedException {
        orderDTO = objectMapper.readValue(message, OrderDTO.class);
        System.out.println("Calculando o total do pedido (15 segundos)");
        Thread.sleep(5000);
        double total = 0.0;
        for(ItemEntity item: orderDTO.getItems()){
            total += item.getPrice() + item.getQuantity();
        }
        orderDTO.setTotal(total);
        rabbitTemplate.convertAndSend("spring-boot-a", "a.b.c", orderDTO.toString());
        return orderDTO;
    }
}