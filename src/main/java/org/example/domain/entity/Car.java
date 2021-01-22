package org.example.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "cars")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String model;


    @Column(precision = 10, scale = 3)
    @NonNull
    private BigDecimal price;


    @NonNull
    private boolean sold;

    @ManyToOne
    private Category category;
}
