package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository extends AbstractInMemoryRepository<Car> {

    @Override
    protected String getEntityId(Car entity) {
        return entity.getCarId();
    }

    @Override
    protected void setEntityId(Car entity, String id) {
        entity.setCarId(id);
    }
}