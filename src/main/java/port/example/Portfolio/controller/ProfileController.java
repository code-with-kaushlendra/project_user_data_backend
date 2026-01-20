package port.example.Portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import port.example.Portfolio.Dto.ProfileDTO;
import port.example.Portfolio.Service.ProfileService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {
    


    private final ProfileService service;

    public ProfileController(ProfileService service) {

        this.service = service;
    }

    @GetMapping
    public ProfileDTO getProfile() {

        return service.getProfile();
    }

    @PutMapping
    public ProfileDTO updateProfile(@RequestBody ProfileDTO dto) {

        return service.saveProfile(dto);
    }
}
