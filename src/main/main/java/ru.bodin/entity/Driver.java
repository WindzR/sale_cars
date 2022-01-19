package ru.bodin.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "driver_license")
    private int driverLicense;

    @ManyToMany(cascade = {CascadeType.PERSIST,
                            CascadeType.MERGE,
                            CascadeType.DETACH,
                            CascadeType.REFRESH})
    @JoinTable(
            name = "history_owner",
            joinColumns = @JoinColumn(name = "driver_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "car_id", nullable = false, updatable = false)
    )
    private Set<Car> cars = new HashSet<>();

    public Driver() {
    }

    public Driver(String name, int driverLicense) {
        this.name = name;
        this.driverLicense = driverLicense;
    }

    public static Driver of(String name, int driverLicense) {
        Driver driver = new Driver();
        driver.name = name;
        driver.driverLicense = driverLicense;
        return driver;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(int driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Driver driver = (Driver) o;
        return id == driver.id && driverLicense == driver.driverLicense
                && Objects.equals(name, driver.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Driver{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", driverLicense=" + driverLicense
                + ", cars=" + cars
                + '}';
    }
}
