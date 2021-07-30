package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.order.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderFacade orderFacade;

    @PostMapping("/pay")
    @ResponseBody
    public ResponseEntity<Object> placeOrder(@RequestBody OrderRequest orderRequest) {

        try {
            return ResponseEntity.ok(this.orderFacade.placeOrder(orderRequest));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<OrderResponse>> getAll() {

        return ResponseEntity.ok(this.orderFacade.getAll());
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<OrderResponse> update(@PathVariable Integer id, @RequestBody OrderRequest orderRequest) {

        try {
            return ResponseEntity.ok(this.orderFacade.update(id, orderRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        try {
            this.orderFacade.delete(id);
            return ResponseEntity.ok("Order Deleted");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}