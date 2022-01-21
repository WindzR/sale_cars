package ru.bodin.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_body_types")
public class CarBodyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    public CarBodyType() {
    }

    public CarBodyType(String model) {
        this.model = model;
    }

    public static CarBodyType of(String model) {
        CarBodyType carBodyType = new CarBodyType();
        carBodyType.model = model;
        return carBodyType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBodyType that = (CarBodyType) o;
        return id == that.id && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CarBodyType{"
                + "id=" + id
                + ", model='" + model + '\''
                + '}';
    }
}
