package ru.job4j.many;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class ModelCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandCar brand;

    public static ModelCar of(String name, BrandCar brand) {
        ModelCar model = new ModelCar();
        model.name = name;
        model.brand = brand;
        return model;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelCar modelCar = (ModelCar) o;
        return id == modelCar.id &&
                Objects.equals(name, modelCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
