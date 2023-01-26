package com.example.BookStore.service;

import com.example.BookStore.dto.OrderDTO;
import com.example.BookStore.exception.BookStoreException;
import com.example.BookStore.model.BookModel;
import com.example.BookStore.model.OrderModel;
import com.example.BookStore.model.UserModel;
import com.example.BookStore.repository.BookRepo;
import com.example.BookStore.repository.OrderRepo;
import com.example.BookStore.repository.UserRepo;
import com.example.BookStore.util.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrder {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailSenderService mailSender;


    @Override
    public OrderModel insertOrder(OrderDTO orderdto) {
        Optional<BookModel> book = bookRepo.findById(orderdto.getBookID());
        Optional<UserModel> user = userRepo.findById(orderdto.getUserID());
        if (book.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity() < book.get().getQuantity()) {
                OrderModel newOrder = new OrderModel(book.get().getPrice(), orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get(), orderdto.isCancel());
                orderRepo.save(newOrder);
                book.get().setQuantity(book.get().getQuantity() - orderdto.getQuantity());
                bookRepo.save(book.get());
                log.info("Order record inserted successfully");
                mailSender.sendEmail(user.get().getEmail(), "Your Order Placed successfully", "Hello, Your order ID is " + newOrder.getOrderID() + " & it  is placed successfully on " + newOrder.getDate() + " and will be delivered to you shortly.");
                return newOrder;
            } else {
                throw new BookStoreException("Requested quantity is not available");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }
    @Override
    public List<OrderModel> getAllOrderRecords() {
        List<OrderModel> orderList = orderRepo.findAll();
        log.info("ALL order records retrieved successfully");
        return orderList;
    }

    @Override
    public OrderModel getOrderRecord(Integer id) {
        Optional<OrderModel> order = orderRepo.findById(id);
        if(order.isEmpty()) {
            throw new BookStoreException("Order Record doesn't exists");
        }
        else {
            log.info("Order record retrieved successfully for id "+id);
            return order.get();
        }
    }

    @Override
    public OrderModel updateOrderRecord(Integer id, OrderDTO orderdto) {
        Optional<OrderModel> order = orderRepo.findById(id);
        Optional<BookModel>  book = bookRepo.findById(orderdto.getBookID());
        Optional<UserModel> user = userRepo.findById(orderdto.getUserID());
        if(order.isEmpty()) {
            throw new BookStoreException("Order Record doesn't exists");
        }
        else {
            if(book.isPresent() && user.isPresent()) {
                if(orderdto.getQuantity() < book.get().getQuantity()) {
                    OrderModel newOrder = new OrderModel(id,orderdto.getQuantity(),orderdto.getAddress(),book.get(),user.get(),orderdto.isCancel());
                    orderRepo.save(newOrder);
                    log.info("Order record updated successfully for id "+id);
                    book.get().setQuantity(book.get().getQuantity() -(orderdto.getQuantity() -order.get().getQuantity()));
                    bookRepo.save(book.get());
                    return newOrder;
                }else {
                    throw new BookStoreException("Requested quantity is not available");
                }
            }
            else {
                throw new BookStoreException("Book or User doesn't exists");

            }
        }
    }

    @Override
    public OrderModel deleteOrderRecord(Integer id) {
        Optional<OrderModel> order = orderRepo.findById(id);
        Optional<BookModel>  book = bookRepo.findById(order.get().getBook().getBookID());
        if(order.isEmpty()) {
            throw new BookStoreException("Order Record doesn't exists");
        }
        else {
            book.get().setQuantity(book.get().getQuantity() + order.get().getQuantity());
            bookRepo.save(book.get());
            orderRepo.deleteById(id);
            log.info("Order record deleted successfully for id "+id);
            return order.get();
        }
    }
    }

