package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockResponse;
import org.fantasticcoffee.shop.facade.standardrecipe.StandardRecipeFacade;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;
    @Autowired
    StandardRecipeFacade standardRecipeFacade;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<StandardRecipeInStockResponse>> getAll() {
        List<StandardRecipeInStockResponse> standardRecipeList
                = this.standardRecipeFacade.getStandardRecipeResponse(this.coffeeService.getStandardRecipeList());
        return ResponseEntity.ok(standardRecipeList);
    }
}