package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;

import java.util.List;

public interface CarDao {
    void add(Car car);
    List<Car> listCar();

}
