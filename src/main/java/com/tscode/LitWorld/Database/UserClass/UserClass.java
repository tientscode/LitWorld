package com.tscode.LitWorld.Database.UserClass;

import com.tscode.LitWorld.Database.RoleClass.RoleClass;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account")
    private String account;

    @Column(name = "Name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="image")
    private String image;

    @Transient  // không lưu trữ trong database
    private MultipartFile imageFile;

    @Column(nullable = false)
    private Boolean active;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<RoleClass> roles;

}
