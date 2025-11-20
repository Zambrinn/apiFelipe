package trabalhoFelipe.github.Zambrinn.model.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        LocalDateTime createdAt
) {
}
