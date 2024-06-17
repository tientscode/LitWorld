package com.tscode.LitWorld.Database.StoryClass;


import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "story")
public class StoryClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;
    private Double price;
    private long quantity;
    private String author;
    private boolean status;
    private String description;
    private String image;
    private String category;

}
