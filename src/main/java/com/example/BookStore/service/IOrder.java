package com.example.BookStore.service;

import com.example.BookStore.dto.OrderDTO;
import com.example.BookStore.model.OrderModel;

import java.util.List;

public interface IOrder {
    public OrderModel insertOrder(OrderDTO orderdto);

    public List<OrderModel> getAllOrderRecords();

      public OrderModel getOrderRecord(Integer id);

    public OrderModel updateOrderRecord(Integer id, OrderDTO orderdto);

    public OrderModel deleteOrderRecord(Integer id);
}
