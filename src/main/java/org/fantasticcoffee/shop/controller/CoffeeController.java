package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.data.ResponseWithList;
import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeResponse;
import org.fantasticcoffee.shop.facade.standardrecipe.StandardRecipeFacade;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/all")
    @ResponseBody
    public ResponseEntity<ResponseWithList<StandardRecipeResponse>> getAll() {
        List<StandardRecipeResponse> standardRecipeResponseList
                = this.standardRecipeFacade.getStandardRecipeResponse(this.coffeeService.getStandardRecipeList());
        ResponseWithList<StandardRecipeResponse> response = new ResponseWithList<>(standardRecipeResponseList);

        return ResponseEntity.ok(response);
    }
}