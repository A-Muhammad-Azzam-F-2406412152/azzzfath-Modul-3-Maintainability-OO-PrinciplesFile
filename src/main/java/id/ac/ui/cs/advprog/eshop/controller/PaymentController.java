package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/detail")
    public String paymentDetailForm() {
        return null; // Sengaja null buat fase RED
    }

    @GetMapping("/detail/{paymentId}")
    public String paymentDetail(@PathVariable String paymentId, Model model) {
        return null; // Sengaja null buat fase RED
    }

    @GetMapping("/admin/list")
    public String adminPaymentList(Model model) {
        return null; // Sengaja null buat fase RED
    }

    @GetMapping("/admin/detail/{paymentId}")
    public String adminPaymentDetail(@PathVariable String paymentId, Model model) {
        return null; // Sengaja null buat fase RED
    }

    @PostMapping("/admin/set-status/{paymentId}")
    public String adminSetPaymentStatus(@PathVariable String paymentId, @RequestParam("status") String status, Model model) {
        return null; // Sengaja null buat fase RED
    }
}