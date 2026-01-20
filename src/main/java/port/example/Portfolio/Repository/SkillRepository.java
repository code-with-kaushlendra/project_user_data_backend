package port.example.Portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import port.example.Portfolio.entity.Skill;

import java.util.Optional;

public  interface SkillRepository extends JpaRepository<Skill,Long> {
    Optional<Skill> findByName(String name);
}
