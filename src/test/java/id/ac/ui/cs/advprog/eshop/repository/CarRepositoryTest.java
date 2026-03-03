package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        // Menggunakan implementasi konkrit
        carRepository = new CarRepository();
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("car-123");
        car.setCarName("Pajero Sport");
        car.setCarColor("Hitam");
        car.setCarQuantity(5);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();

        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testCreateCarWithoutId() {
        Car car = new Car();
        car.setCarName("Mobil Gaib");
        car.setCarColor("Transparan");
        car.setCarQuantity(1);

        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();

        // Memastikan AbstractRepository meng-generate ID secara otomatis
        assertNotNull(savedCar.getCarId());
        assertEquals("Mobil Gaib", savedCar.getCarName());
    }

    @Test
    void testFindCarById() {
        Car car = new Car();
        car.setCarId("car-456");
        car.setCarName("Avanza");
        carRepository.create(car);

        Car foundCar = carRepository.findById(car.getCarId());
        assertNotNull(foundCar);
        assertEquals(car.getCarName(), foundCar.getCarName());
    }

    @Test
    void testFindCarByIdIfNotFound() {
        Car foundCar = carRepository.findById("ID-NGASAL");
        assertNull(foundCar);
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarId("car-update");
        car.setCarName("Brio Lama");
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarId("car-update"); // ID harus sama
        updatedCar.setCarName("Brio Baru");
        updatedCar.setCarColor("Kuning");

        Car result = carRepository.update(updatedCar.getCarId(), updatedCar);

        assertNotNull(result);
        assertEquals("Brio Baru", result.getCarName());

        Car foundCar = carRepository.findById("car-update");
        assertEquals("Brio Baru", foundCar.getCarName());
        assertEquals("Kuning", foundCar.getCarColor());
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarId("car-delete");
        car.setCarName("Xpander");
        carRepository.create(car);

        carRepository.delete("car-delete");

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
}