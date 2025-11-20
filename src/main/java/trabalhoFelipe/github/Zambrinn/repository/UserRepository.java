package trabalhoFelipe.github.Zambrinn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalhoFelipe.github.Zambrinn.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
