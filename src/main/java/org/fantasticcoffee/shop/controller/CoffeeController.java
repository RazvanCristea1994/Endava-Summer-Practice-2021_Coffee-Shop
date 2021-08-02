package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.model.coffee.StandardCoffee;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Enum>> getAll() {

        List<Enum> standardCoffeeList = new ArrayList<>(EnumSet.allOf(StandardCoffee.class));
        return ResponseEntity.ok(standardCoffeeList);
    }
}