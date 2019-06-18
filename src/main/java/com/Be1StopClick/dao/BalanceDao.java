package com.Be1StopClick.dao;

import com.Be1StopClick.model.Balance;

import java.util.List;

/*
 * Created by dendy-prtha on 10/06/2019.
 * Dao for balance
 */

public interface BalanceDao extends Dao<Balance, Integer> {
    List<Balance> findBalanceByUserId(String userId);
}
