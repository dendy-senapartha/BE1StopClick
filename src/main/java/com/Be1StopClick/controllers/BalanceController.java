package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.BalanceDao;
import com.Be1StopClick.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/*
 * Created by dendy-prtha on 10/06/2019.
 * controller for balance
 */

@RestController
public class BalanceController {

    @Autowired
    private BalanceDao balanceRepository;

    @PostMapping(value = "/balance/get-balance-by-user",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public Map<String, List<Balance>> getBalanceByUser(@RequestBody Map<String, Object> body) {
        Map<String, List<Balance>> map = new HashMap<>();
        Optional<Object> userId = Optional.ofNullable(body.get("userId"));

        List<Balance> balanceList = new ArrayList<>();
        if (userId.isPresent()) {
            balanceList = balanceRepository.findBalanceByUserId(userId.get().toString());
        }
        map.put("result", balanceList);
        return map;
    }
}
