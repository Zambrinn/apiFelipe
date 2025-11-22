package trabalhoFelipe.github.Zambrinn.model.DTOs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import trabalhoFelipe.github.Zambrinn.model.Car;

public record UserResponse(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt,
        List<Car> cars
) {
}
