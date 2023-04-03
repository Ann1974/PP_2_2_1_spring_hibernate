package hiber.dao;

import hiber.model.Car;

import java.util.List;

public interface CarDao {  // слой для машин

    void add(Car car);
    List<Car> listCars();
}
