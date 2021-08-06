package org.fantasticcoffee.shop.controller;

import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.order.OrderFacade;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderFacade orderFacade;
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<OrderResponse> makeOrderPayment(
            @Valid @RequestBody OrderRequest orderRequest,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(errorField ->
                    stringBuilder.append(errorField.getDefaultMessage()));

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    stringBuilder.toString(),
                    new IllegalArgumentException());
        } else {
            try {
                Order order =
                        this.orderService.placeOrder(this.orderFacade.getOrder(orderRequest));

                return ResponseEntity.ok(this.orderFacade.getOrderResponse(order));
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            }
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<OrderResponse>> getAll() {

        List<OrderResponse> list = this.orderFacade.getAll();
        return ResponseEntity.ok(list);
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