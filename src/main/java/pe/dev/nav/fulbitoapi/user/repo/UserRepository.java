package pe.dev.nav.fulbitoapi.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dev.nav.fulbitoapi.user.domain.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
