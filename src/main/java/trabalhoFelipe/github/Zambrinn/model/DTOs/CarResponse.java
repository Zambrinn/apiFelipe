package trabalhoFelipe.github.Zambrinn.model.DTOs;

import java.util.UUID;



public record CarResponse(
    UUID id,
    String brand,
    String model,
    int year,
    CarStatus status,
    UUID userId
) {

}
