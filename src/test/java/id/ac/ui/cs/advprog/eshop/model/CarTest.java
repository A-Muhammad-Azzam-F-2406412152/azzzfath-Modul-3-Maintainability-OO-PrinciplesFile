package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("car-12345");
        this.car.setCarName("Toyota Yaris");
        this.car.setCarColor("Hitam");
        this.car.setCarQuantity(5);
    }

    @Test
    void testGetCarId() {
        assertEquals("car-12345", this.car.getCarId());
    }

    @Test
    void testGetCarName() {
        assertEquals("Toyota Yaris", this.car.getCarName());
    }

    @Test
    void testGetCarColor() {
        assertEquals("Hitam", this.car.getCarColor());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(5, this.car.getCarQuantity());
    }
}