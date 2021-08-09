package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.data.ResponseWithList;
import org.fantasticcoffee.shop.data.ingredient.IngredientDetailedResponse;
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
    public ResponseEntity<ResponseWithList<IngredientDetailedResponse>> getStock() {

        List<IngredientDetailedResponse> ingredientListResponse =
                this.ingredientFacade.getIngredientResponse(this.ingredientService.getAllIngredients());
        ResponseWithList<IngredientDetailedResponse> response = new ResponseWithList<>(ingredientListResponse);

        return ResponseEntity.ok(response);
    }
}