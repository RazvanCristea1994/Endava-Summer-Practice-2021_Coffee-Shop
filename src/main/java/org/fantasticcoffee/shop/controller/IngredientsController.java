package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.fantasticcoffee.shop.service.IngredientService;
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
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Enum>> getAll() {

        List<Enum> ingredientList = new ArrayList<>(EnumSet.allOf(BaseIngredient.class));
        ingredientList.addAll(new ArrayList<>(EnumSet.allOf(ExtraIngredient.class)));

        return ResponseEntity.ok(ingredientList);
    }
}