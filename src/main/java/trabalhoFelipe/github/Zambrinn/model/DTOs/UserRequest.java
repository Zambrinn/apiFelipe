package trabalhoFelipe.github.Zambrinn.model.DTOs;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "The name must not be null")
        String name,
        @NotBlank(message = "The email must not be null")
        String email,
        @NotBlank(message = "The password must not be null")
        String password,
        UserRole role
) {
}
