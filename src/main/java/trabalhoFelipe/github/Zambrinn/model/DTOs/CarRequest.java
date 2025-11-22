package trabalhoFelipe.github.Zambrinn.model.DTOs;

import java.util.UUID;

public record CarRequest(
    String brand,
    String model,
    int year,
    CarStatus status,
    UUID userId
) {

}
