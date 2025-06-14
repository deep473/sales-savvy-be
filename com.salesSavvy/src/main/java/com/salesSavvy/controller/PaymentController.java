package com.salesSavvy.controller;

import java.util.Map;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.salesSavvy.dto.*;
import com.salesSavvy.entity.*;
import com.salesSavvy.repository.OrderRepository;
import com.salesSavvy.service.*;

@RestController
@CrossOrigin("*")
public class PaymentController {

    @Autowired PaymentService  payService;
    @Autowired UsersService    usersService;
    @Autowired CartService     cartService;
    @Autowired OrderRepository orderRepo;

    /* ---------- 1. create Razorpay order ---------- */
    @PostMapping("/payment/create")
    public ResponseEntity<?> create(@RequestBody PaymentRequest req) throws RazorpayException {

        Users u = usersService.getUser(req.username);
        if (u == null) return ResponseEntity.badRequest().body("user not found");

        Order rzp = payService.createRzpOrder(req.amount);

        Orders o  = new Orders();
        o.setId(rzp.get("id"));
        o.setAmount(req.amount);
        o.setCurrency("INR");
        o.setStatus("CREATED");
        o.setUser(u);
        o.setItems(cartService.cloneItems(req.username));
        orderRepo.save(o);

        return ResponseEntity.ok(Map.of(
            "key",     payService.getKeyId(),
            "orderId", rzp.get("id"),
            "amount",  req.amount
        ));
    }

    /* ---------- 2. verify after payment ---------- */
    @PostMapping("/payment/verify")
    public ResponseEntity<?> verify(@RequestBody PaymentVerifyRequest req) throws RazorpayException {

        if (!payService.verifySignature(req.orderId, req.paymentId, req.signature))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("signature mismatch");

        Orders ord = orderRepo.findById(req.orderId).orElseThrow();
        ord.setStatus("PAID");
        ord.setPaymentId(req.paymentId);
        orderRepo.save(ord);

        cartService.clearCart(req.username);   // empty cart

        return ResponseEntity.ok(ord.getId()); // for React redirect
    }

    /* ---------- 3a. raw entity (optional) ---------- */
    @GetMapping("/order/{orderId}")
    public Orders getOrder(@PathVariable String orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }

    /* ---------- 3b. lean summary for React ---------- */
    @GetMapping("/order/summary/{orderId}")
    public Map<String, Object> getOrderSummary(@PathVariable String orderId) {

        Orders o = orderRepo.findById(orderId).orElse(null);
        if (o == null) return Map.of("error", "not-found");

        int totalRupees = o.getAmount() / 100;

        return Map.of(
            "orderId", o.getId(),
            "status",  o.getStatus(),
            "total",   totalRupees,
            "items",   o.getItems().stream().map(ci -> Map.of(
                           "name",  ci.getProduct().getName(),
                           "qty",   ci.getQuantity(),
                           "price", ci.getProduct().getPrice()
                        )).toList()
        );
    }
}
