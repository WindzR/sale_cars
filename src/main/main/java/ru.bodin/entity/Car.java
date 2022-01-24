package ru.bodin.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "year_of_issue")
    private int yearOfIssue;

    @ManyToOne(cascade = {CascadeType.PERSIST,
                            CascadeType.MERGE,
                            CascadeType.DETACH,
                            CascadeType.REFRESH})
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToOne(cascade = {CascadeType.PERSIST,
                            CascadeType.MERGE,
                            CascadeType.DETACH,
                            CascadeType.REFRESH})
    @JoinColumn(name = "car_body_type_id", foreignKey = @ForeignKey(name = "CAR_BODY_TYPE_ID_FK"))
    private CarBodyType carBodyType;

    @OneToMany(cascade = {CascadeType.PERSIST,
                            CascadeType.MERGE,
                            CascadeType.DETACH,
                            CascadeType.REFRESH})
    @JoinTable(
            name = "car_announcements",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "announcement_id")
    )
    private Set<Announcement> announcements = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,
                            CascadeType.MERGE,
                            CascadeType.DETACH,
                            CascadeType.REFRESH})
    @JoinTable(
            name = "history_owner",
            joinColumns = @JoinColumn(name = "car_id", nullable = false, updatable = false),
            inverseJoinColumns =
            @JoinColumn(name = "driver_id", nullable = false, updatable = false)
    )
    private Set<Driver> drivers = new HashSet<>();

    public Car() {
    }

    public Car(String model, String manufacturer, int yearOfIssue) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.yearOfIssue = yearOfIssue;
    }

    public static Car of(String model, String manufacturer, int yearOfIssue) {
        Car car = new Car();
        car.model = model;
        car.manufacturer = manufacturer;
        car.yearOfIssue = yearOfIssue;
        return car;
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarBodyType getCarBodyType() {
        return carBodyType;
    }

    public void setCarBodyType(CarBodyType carBodyType) {
        this.carBodyType = carBodyType;
    }

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && yearOfIssue == car.yearOfIssue
                && Objects.equals(model, car.model)
                && Objects.equals(manufacturer, car.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", model='" + model + '\''
                + ", manufacturer='" + manufacturer + '\''
                + ", yearOfIssue=" + yearOfIssue
                + ", engine=" + engine
                + ", carBodyType=" + carBodyType
                + ", announcements=" + announcements
                + ", drivers=" + drivers
                + '}';
    }
}
