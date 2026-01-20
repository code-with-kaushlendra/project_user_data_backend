package port.example.Portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import port.example.Portfolio.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
