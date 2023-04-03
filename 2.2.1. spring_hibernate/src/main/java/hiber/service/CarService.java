package hiber.service;

import hiber.model.Car;

import java.util.List;

public interface CarService { // бизнес логика

    void add(Car car);

    List<Car> listCars();
}
