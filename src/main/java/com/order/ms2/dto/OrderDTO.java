package com.order.ms2.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.ms2.entity.ItemEntity;
import com.order.ms2.entity.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private String clientId;
    private String orderId;
    private List<ItemEntity> items = new ArrayList<>();
    private double total;
    private OrderStatus status;
    private ObjectMapper objectMapper = new ObjectMapper();

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert OrderDTO to JSON", e);
        }
    }
}
