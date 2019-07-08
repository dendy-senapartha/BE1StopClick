package com.Be1StopClick.dao;

import com.Be1StopClick.model.Album;
import com.Be1StopClick.model.Orders;
import com.Be1StopClick.model.Product;

import java.util.List;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Product Dao
 */

public interface OrderDao extends Dao<Orders, Integer> {
    List<Orders> findOrderByUserId(int userId);
    List<Orders> getUserOrderNeedTooPay(int userId);
}
