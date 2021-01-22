package org.example.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@ToString(exclude = "cars")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Car> cars;


    public Category() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }
}
