package ru.job4j.many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class BrandCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "brand")
    private List<ModelCar> models = new ArrayList<>();

    public static BrandCar of(String name) {
        BrandCar car = new BrandCar();
        car.name = name;
        return car;
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

    public void addModel(ModelCar model) {
        this.models.add(model);
    }

    public List<ModelCar> getModels() {
        return models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandCar brandCar = (BrandCar) o;
        return id == brandCar.id &&
                Objects.equals(name, brandCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
