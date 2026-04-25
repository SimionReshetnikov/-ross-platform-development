package com.example.moviehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @Setter
    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязательный")
    @Pattern(regexp = ".+@.+\\..+", message = "Email должен содержать домен")
    private String email;

    @Setter
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;
}
