package com.Be1StopClick.controllers;

import com.Be1StopClick.model.OrderItem;
import com.Be1StopClick.model.Orders;
import com.Be1StopClick.model.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * Created by dendy-prtha on 04/06/2019.
 * Controller for order
 */

@RestController
public class OrderController {

    @PostMapping(value = "/order/create-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Orders> createOrder(@RequestBody Map<String, List<OrderItem>> body) {
        List<OrderItem> orderItems = body.get("orderItem");
        System.out.println(orderItems);
        return null;
    }
}
