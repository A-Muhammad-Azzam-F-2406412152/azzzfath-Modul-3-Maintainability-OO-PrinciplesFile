package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testPaymentDetailForm() throws Exception {
        mockMvc.perform(get("/payment/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment/paymentDetailForm"));
    }

    @Test
    void testPaymentDetail() throws Exception {
        Order order = new Order("1", new ArrayList<>(), 123L, "Safira");
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", new HashMap<>(), order);
        when(paymentService.getPayment(anyString())).thenReturn(payment);

        mockMvc.perform(get("/payment/detail/pay-1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payment"))
                .andExpect(view().name("payment/paymentDetail"));
    }

    @Test
    void testAdminPaymentList() throws Exception {
        when(paymentService.getAllPayments()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/payment/admin/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payments"))
                .andExpect(view().name("payment/adminPaymentList"));
    }

    @Test
    void testAdminPaymentDetail() throws Exception {
        Order order = new Order("1", new ArrayList<>(), 123L, "Safira");
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", new HashMap<>(), order);
        when(paymentService.getPayment(anyString())).thenReturn(payment);

        mockMvc.perform(get("/payment/admin/detail/pay-1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payment"))
                .andExpect(view().name("payment/adminPaymentDetail"));
    }

    @Test
    void testAdminSetPaymentStatus() throws Exception {
        mockMvc.perform(post("/payment/admin/set-status/pay-1")
                        .param("status", "SUCCESS"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/payment/admin/list"));
    }
}