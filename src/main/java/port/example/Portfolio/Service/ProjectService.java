package port.example.Portfolio.Service;


import org.springframework.stereotype.Service;
import port.example.Portfolio.Dto.ProjectDTO;
import port.example.Portfolio.Repository.ProjectRepository;
import port.example.Portfolio.entity.Project;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository repository;


    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<ProjectDTO> getAllProjects() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsBySkill(String skill) {
        return repository.findBySkillsContaining(skill).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProjectDTO saveProject(ProjectDTO dto) {
        Project project;
        if (dto.id != null) {
            project = repository.findById(dto.id).orElse(new Project());
        } else {
            project = new Project();
        }

        project.setName(dto.name);
        project.setDescription(dto.description);
        project.setLink(dto.link);
        project.setUrl(dto.url);
        project.setSkills(dto.skills);

        repository.save(project);
        dto.id = project.getId();
        return dto;
    }

    private ProjectDTO toDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.id = project.getId();
        dto.name = project.getName();
        dto.description = project.getDescription();
        dto.link = project.getLink();
        dto.url = project.getUrl();
        dto.skills = project.getSkills();
        return dto;
    }
}
