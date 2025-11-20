package trabalhoFelipe.github.Zambrinn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import trabalhoFelipe.github.Zambrinn.model.DTOs.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity // é uma entitdade
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    /**
     * toda classe precisa dos atributos iniciais
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // UUID é um id gerado automaticamete, melhor que LONG

    @NotBlank(message = "The name must not be null")
    @Column(nullable = false)
    private String name; // nome do usuário
    @NotBlank(message = "The last name must not be null")
    @Column(nullable = false)
    private String email; // email do usuário
    @NotBlank(message = "The email must not be null")
    @Column(nullable = false)
    private String password; // senha do usuário

    @Column(name = "created_at", nullable = false, updatable = true)
    private LocalDateTime createdAt; // eu criei uma variável para sabermos quando foi criado

    @Enumerated(EnumType.STRING)
    private UserRole role; // aqui eu criei um atributo que vem da classe UserEnum
    // esse role só pode ser: ADMIN ou USER

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();
}
