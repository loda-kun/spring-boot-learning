package me.loda.spring.openapi3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-06-06
 * Github: https://github.com/loda-kun
 */
@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "User UUID in  the database")
    @JsonProperty("id")
    private Long id;

    private @NotBlank @Size(min = 0, max = 20) String firstName;
    private String lastName;

    private @NotBlank @Size(min = 0, max = 50) String email;
}
