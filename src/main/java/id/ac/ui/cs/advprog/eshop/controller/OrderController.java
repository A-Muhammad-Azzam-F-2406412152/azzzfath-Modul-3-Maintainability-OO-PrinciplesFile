package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/create")
    public String createOrderPage() {
        return null; // Sengaja null buat fase RED
    }

    @GetMapping("/history")
    public String orderHistoryPage() {
        return null; // Sengaja null buat fase RED
    }

    @PostMapping("/history")
    public String orderHistoryPost(@RequestParam("author") String author, Model model) {
        return null; // Sengaja null buat fase RED
    }

    @GetMapping("/pay/{orderId}")
    public String payOrderPage(@PathVariable String orderId, Model model) {
        return null; // Sengaja null buat fase RED
    }

    @PostMapping("/pay/{orderId}")
    public String payOrderPost(@PathVariable String orderId, Model model) {
        return null; // Sengaja null buat fase RED
    }
}