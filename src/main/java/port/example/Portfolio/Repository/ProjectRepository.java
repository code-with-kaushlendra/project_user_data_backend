package port.example.Portfolio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import port.example.Portfolio.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findBySkillsContaining(String skill);
}
