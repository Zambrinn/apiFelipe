package trabalhoFelipe.github.Zambrinn.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trabalhoFelipe.github.Zambrinn.model.Car;

public interface CarRepository extends JpaRepository<Car, UUID> {

}
