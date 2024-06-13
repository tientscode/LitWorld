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

    @Column(name = "name", unique = true,nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    private  Double price;

}
