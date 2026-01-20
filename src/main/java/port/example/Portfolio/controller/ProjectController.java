package port.example.Portfolio.controller;


import org.springframework.web.bind.annotation.*;
import port.example.Portfolio.Dto.ProjectDTO;
import port.example.Portfolio.Service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects(@RequestParam(required = false) String skill) {
        if (skill != null) {
            return service.getProjectsBySkill(skill);
        }
        return service.getAllProjects();
    }

    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectDTO dto) {
        return service.saveProject(dto);
    }
}
