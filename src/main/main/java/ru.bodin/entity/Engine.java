package ru.bodin.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "engines")
public class Engine {

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

    public Engine() {
    }

    public Engine(String model, String manufacturer, int yearOfIssue) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.yearOfIssue = yearOfIssue;
    }

    public static Engine of(String model, String manufacturer, int yearOfIssue) {
        Engine engine = new Engine();
        engine.model = model;
        engine.manufacturer = manufacturer;
        engine.yearOfIssue = yearOfIssue;
        return engine;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id && yearOfIssue == engine.yearOfIssue
                && Objects.equals(model, engine.model)
                && Objects.equals(manufacturer, engine.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    @Override
    public String toString() {
        return "Engine{"
                + "id=" + id
                + ", model='" + model + '\''
                + ", manufacturer='" + manufacturer + '\''
                + ", yearOfIssue='" + yearOfIssue + '\''
                + '}';
    }
}
