package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void testCreateOrderPage() throws Exception {
        mockMvc.perform(get("/order/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/createOrder"));
    }

    @Test
    void testOrderHistoryPage() throws Exception {
        mockMvc.perform(get("/order/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/orderHistory"));
    }

    @Test
    void testOrderHistoryPost() throws Exception {
        List<Order> mockOrders = new ArrayList<>();
        when(orderService.findAllByAuthor(anyString())).thenReturn(mockOrders);

        mockMvc.perform(post("/order/history")
                        .param("author", "Safira Sudrajat"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("orders"))
                .andExpect(view().name("order/orderList"));
    }

    @Test
    void testPaymentOrderPage() throws Exception {
        Order mockOrder = new Order("1", new ArrayList<>(), 123L, "Safira");
        when(orderService.findById("1")).thenReturn(mockOrder);

        mockMvc.perform(get("/order/pay/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name("order/payOrder"));
    }

    @Test
    void testPaymentOrderPost() throws Exception {
        mockMvc.perform(post("/order/pay/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/paymentIdPage"));
    }
}