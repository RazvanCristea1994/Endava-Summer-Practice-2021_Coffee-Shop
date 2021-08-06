package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.data.ingredient.IngredientInStockResponse;
import org.fantasticcoffee.shop.facade.ingredient.IngredientFacade;
import org.fantasticcoffee.shop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    IngredientService ingredientService;
    @Autowired
    IngredientFacade ingredientFacade;

    @GetMapping("/all-stock")
    @ResponseBody
    public ResponseEntity<List<IngredientInStockResponse>> getStock() {

        List<IngredientInStockResponse> ingredientInStockListResponse =
                this.ingredientFacade.getIngredientInStockResponse(this.ingredientService.getAllIngredientsInStock());
        return ResponseEntity.ok(ingredientInStockListResponse);
    }
}