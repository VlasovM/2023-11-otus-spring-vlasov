package ru.javlasov.sixthhomework.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
